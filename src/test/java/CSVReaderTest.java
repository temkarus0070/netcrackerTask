import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import temkarus0070.firstTask.csvLoader.CsvLoader;
import temkarus0070.firstTask.exceptions.CsvReadException;
import temkarus0070.firstTask.models.Gender;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;

import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;


public class CSVReaderTest {
    @Test
    public void testRead() {
        try {
            Path path = Path.of("src/test/resources/file.csv");
            CsvLoader csvLoader = new CsvLoader();
            String full = path.toAbsolutePath().toString();
            csvLoader.CsvLoad(new ContractRepositoryImpl(), path.toAbsolutePath().toString());
            Assertions.assertTrue(true);
        } catch (CsvReadException csvReadException) {
            csvReadException.printStackTrace();
        }
    }


    @Test
    public void testReadingCorrect() {
        try {
            ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
            Path path = Path.of("src/test/resources/file.csv");
            CsvLoader csvLoader = new CsvLoader();
            String full = path.toAbsolutePath().toString();
            csvLoader.CsvLoad(contractRepository, path.toAbsolutePath().toString());
            MobileConnectionContract mobileConnectionContract = (MobileConnectionContract) contractRepository.getByIndex(0);
            LocalDate beginContractDate = LocalDate.of(2020, 10, 12);
            LocalDate endContractDate = LocalDate.of(2022, 10, 12);

            Assert.assertEquals(mobileConnectionContract.getBeginDate(), beginContractDate);
            Assert.assertEquals(mobileConnectionContract.getEndDate(), endContractDate);
            Person contractOwner = mobileConnectionContract.getContractOwner();
            Assert.assertEquals(contractOwner.getLastName() + contractOwner.getFirstName() + contractOwner.getSurname(), "ПупкинВасилийИванович");
            Assert.assertEquals(contractOwner.getGender(), Gender.MALE);
            Assert.assertEquals(contractOwner.getBirthDate(), LocalDate.of(2000, 2, 4));
            Assert.assertEquals(contractOwner.getPassportNum(), 599480);
            Assert.assertEquals(contractOwner.getPassportChapter(), 2000);
            Assert.assertEquals(mobileConnectionContract.getSmsCount(), 100);
            Assert.assertEquals(mobileConnectionContract.getMinutesCount(), 300);
            Assert.assertEquals(mobileConnectionContract.getGigabytesTraffic(), 10);

            WireInternetContract wireInternetContract = (WireInternetContract) contractRepository.getByIndex(1);
            Assert.assertEquals(wireInternetContract.getBeginDate(), LocalDate.of(2017, 9, 10));
            Assert.assertEquals(wireInternetContract.getEndDate(), LocalDate.of(2022, 12, 20));
            contractOwner = wireInternetContract.getContractOwner();
            Assert.assertEquals(contractOwner.getLastName() + contractOwner.getFirstName() + contractOwner.getSurname(), "ИвановИванИванович");
            Assert.assertEquals(contractOwner.getGender(), Gender.MALE);
            Assert.assertEquals(contractOwner.getBirthDate(), LocalDate.of(1990, 4, 8));
            Assert.assertEquals(contractOwner.getPassportChapter(), 2014);
            Assert.assertEquals(contractOwner.getPassportNum(), 594999);
            Assert.assertEquals(wireInternetContract.getConnectionSpeed(), 100);

            DigitalTelevisionContract digitalTelevisionContract = (DigitalTelevisionContract) contractRepository.getByIndex(2);
            Assert.assertEquals(digitalTelevisionContract.getBeginDate(), LocalDate.of(2016, 3, 2));
            Assert.assertEquals(digitalTelevisionContract.getEndDate(), LocalDate.of(2022, 4, 1));
            contractOwner = digitalTelevisionContract.getContractOwner();
            Assert.assertEquals(contractOwner.getLastName() + contractOwner.getFirstName() + contractOwner.getSurname(), "ПупкинаВикторияВикторовна");
            Assert.assertEquals(contractOwner.getGender(), Gender.FEMALE);
            Assert.assertEquals(contractOwner.getBirthDate(), LocalDate.of(1970, 1, 3));
            Assert.assertEquals(contractOwner.getPassportChapter(), 2012);
            Assert.assertEquals(contractOwner.getPassportNum(), 344444);
            Assert.assertEquals(digitalTelevisionContract.getChannelsPackage(), "Premium");
            return;

        } catch (CsvReadException csvReadException) {
            csvReadException.printStackTrace();
        }
        Assert.assertTrue(false);
    }


}
