package temkarus0070.firstTask.csvLoader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import temkarus0070.firstTask.exceptions.CsvReadException;
import temkarus0070.firstTask.models.Gender;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;

import javax.swing.text.DateFormatter;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CsvLoader {
    public CsvLoader(ContractRepositoryImpl contractRepository, String filePath) throws CsvReadException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
        try {
            CSVReader csvReader = new CSVReader(new FileReader(filePath));
            csvReader.skip(1);
            String[] lines;
            while ((lines = csvReader.readNext()) != null) {
                String contractType = lines[6];
                Contract contract = null;
                Map<String, String> additionalParams = getAdditionalParams(lines[8].split(","));
                switch (contractType) {
                    case "Mobile":
                        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract();
                        mobileConnectionContract.setSmsCount(Integer.parseInt(additionalParams.get("smsCount")));
                        mobileConnectionContract.setGigabytesTraffic(Integer.parseInt(additionalParams.get("minutes")));
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
                    throw new CsvReadException("chat type not found exception");
                } else {
                    contract.setBeginDate(new Date(LocalDate.parse(lines[0], dateTimeFormatter).toEpochDay()));
                    contract.setEndDate(new Date(LocalDate.parse(lines[1], dateTimeFormatter).toEpochDay()));
                    String[] fullname = lines[2].split(" ");
                    String[] passportDate = lines[5].split(" ");
                    Person person = new Person(-1, fullname[1], fullname[0], fullname[2], Gender.valueOf(lines[3]), Integer.parseInt(passportDate[0]), Integer.parseInt(passportDate[1]), new Date(LocalDate.parse(lines[4], dateTimeFormatter).toEpochDay()));
                    contract.setContractOwner(person);
                    contractRepository.add(contract);
                }
            }
        } catch (CsvValidationException | IOException | NumberFormatException fileReadError) {
            throw new CsvReadException(fileReadError);
        }

    }

    private HashMap<String, String> getAdditionalParams(String[] additionalParams) {
        return Arrays.stream(additionalParams)
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
