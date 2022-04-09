import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import temkarus0070.firstTask.JDBCLoaders.JDBCReader;
import temkarus0070.firstTask.JDBCLoaders.JDBCWritter;
import temkarus0070.firstTask.models.Gender;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;

import java.time.LocalDate;

public class JDBCTest {
    private ContractRepositoryImpl contractRepository;
    private JDBCWritter jdbcWritter;
    private JDBCReader jdbcReader;

    @BeforeEach
    void init() {
        jdbcWritter = new JDBCWritter("jdbc:h2:~/test2", "sa", "");
        jdbcReader = new JDBCReader("jdbc:h2:~/test2", "sa", "");
        contractRepository = new ContractRepositoryImpl();
        Person person = new Person(1, "Vasya", "Pupkin", "Pupkinovich", Gender.MALE, 1999, 2444444, LocalDate.of(1999, 3, 1));
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract(333, 66, 13, 1, LocalDate.now(), LocalDate.of(2030, 1, 1), 333, person);
        WireInternetContract wireInternetContract = new WireInternetContract(300, 2, LocalDate.now(), LocalDate.of(2030, 1, 1), 3333, person);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract("premium", 11111, LocalDate.now(), LocalDate.of(2030, 1, 1), 666, person);
        contractRepository.add(mobileConnectionContract, wireInternetContract, digitalTelevisionContract);
    }

    @Test
    void testWriteRead() {
        jdbcWritter.save(contractRepository);
        ContractRepositoryImpl repository = jdbcReader.read();
        Assertions.assertEquals(contractRepository, repository);
    }


}
