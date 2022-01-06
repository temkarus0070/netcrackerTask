package temkarus0070.firstTask.csvLoader;

import au.com.bytecode.opencsv.CSVReader;
import temkarus0070.firstTask.di.AutoInjectable;
import temkarus0070.firstTask.exceptions.ContractValidationException;
import temkarus0070.firstTask.exceptions.CsvReadException;
import temkarus0070.firstTask.models.Gender;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;
import temkarus0070.firstTask.validation.Status;
import temkarus0070.firstTask.validation.ValidationResult;
import temkarus0070.firstTask.validation.Validator;
import temkarus0070.firstTask.validation.validators.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CsvLoader {

    @AutoInjectable
    private List<Validator<? extends Contract>> validators;

    public void setValidators(List<Validator<? extends Contract>> validators) {
        this.validators = validators;
    }

    public void CsvLoad(ContractRepositoryImpl contractRepository, String filePath) throws CsvReadException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath), ';', '"', 1)) {
            String[] lines;
            while ((lines = csvReader.readNext()) != null) {
                String contractType = lines[6];
                Contract contract = null;
                Map<String, String> additionalParams = getAdditionalParams(lines[7].split(","));
                switch (contractType) {
                    case "Mobile":
                        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract();
                        mobileConnectionContract.setSmsCount(Integer.parseInt(additionalParams.get("smsCount")));
                        mobileConnectionContract.setMinutesCount(Integer.parseInt(additionalParams.get("minutes")));
                        mobileConnectionContract.setGigabytesTraffic(Integer.parseInt(additionalParams.get("gigabytesTraffic")));

                        contract = mobileConnectionContract;
                        break;
                    case "TV":
                        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract();
                        digitalTelevisionContract.setChannelsPackage(additionalParams.get("channelsPackage"));
                        contract = digitalTelevisionContract;
                        break;
                    case "WireInternet":
                        WireInternetContract wireInternetContract = new WireInternetContract();
                        wireInternetContract.setConnectionSpeed(Integer.parseInt(additionalParams.get("connectionSpeed")));
                        contract = wireInternetContract;
                        break;
                }
                if (contract == null) {
                    throw new CsvReadException("contract type not found exception");
                } else {
                    contract.setBeginDate(lines[0].equals("")?null:LocalDate.parse(lines[0], dateTimeFormatter));
                    contract.setEndDate(lines[1].equals("")?null:LocalDate.parse(lines[1], dateTimeFormatter));
                    String[] fullname =new String[3];
                    String[] nameLines=lines[2].split(" ");
                    for(int i=0;i<nameLines.length;i++){
                        fullname[i]=nameLines[i];
                    }
                    String[] passportDate =lines[5].split(" ");
                    Person person = new Person(-1, fullname[1], fullname[0], fullname[2], Gender.valueOf(lines[3]), Integer.parseInt(passportDate[0]), Integer.parseInt(passportDate[1]), LocalDate.parse(lines[4], dateTimeFormatter));
                    contract.setContractOwner(person);
                }
                for (Validator validator:validators){
                    if(validator.isFitToType(contract)) {
                        ValidationResult validationResult = validator.validate(contract);
                        if (validationResult.getStatus() == Status.ERROR) {
                            String error = String.format("error:%s \n field:%s", validationResult.getText(), validationResult.getFirstErrorField());
                            throw new CsvReadException(new ContractValidationException(error));
                        }
                        else if(validationResult.getStatus()==Status.WARNING){
                            System.out.println(validationResult.getText());
                        }
                    }
                }
                contractRepository.add(contract);
            }
        } catch (IOException | NumberFormatException fileReadError) {
            throw new CsvReadException(fileReadError);
        }

    }

    private HashMap<String, String> getAdditionalParams(String[] additionalParams) {
        return Arrays.stream(additionalParams)
                .filter(e->e.length()>0)
                .map(line -> {
                            String[] lines = line.split("=");
                            return Map.entry(lines[0], lines[1]);
                        }
                )
                .reduce(new HashMap<String, String>(), (map, entry) -> {
                            map.put(entry.getKey(), entry.getValue());
                            return map;
                        },
                        (map1, map2) -> {
                            map1.putAll(map2);
                            return map1;

                        });
    }
}
