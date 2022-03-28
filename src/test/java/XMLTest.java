import org.junit.Test;
import temkarus0070.firstTask.models.Gender;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;
import temkarus0070.firstTask.xmlLoader.XmlRepositoryWritter;

import java.time.LocalDate;


public class XMLTest {
    @Test
    public void testWrite() {
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        Person person = new Person(1, "Vasya", "Pupkin", "Pupkinovich", Gender.MALE, 1999, 2444444, LocalDate.of(1999, 3, 1));

        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract(333, 66, 13, 1, LocalDate.now(), LocalDate.MAX, 333, person);
        WireInternetContract wireInternetContract = new WireInternetContract(300, 1, LocalDate.now(), LocalDate.MAX, 3333, person);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract("premium", 11111, LocalDate.MIN, LocalDate.MAX, 666, person);

        contractRepository.add(mobileConnectionContract, wireInternetContract, digitalTelevisionContract);

        XmlRepositoryWritter xmlRepositoryWritter = new XmlRepositoryWritter();
        xmlRepositoryWritter.write("file.txt", contractRepository);
    }
}
