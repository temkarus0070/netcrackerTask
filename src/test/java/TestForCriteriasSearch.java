import org.junit.Assert;
import org.junit.Test;
import temkarus0070.firstTask.models.Gender;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;
import temkarus0070.firstTask.repository.Repository;

import java.sql.Date;
import java.time.LocalDate;

public class TestForCriteriasSearch {
    @Test
    public void testSearchByAge() {
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        Person pupkin = new Person(1, "Vasya", "Pupkin", "Ivanovich", Gender.MALE, 111, 1111, LocalDate.of(2000,4,9));
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract(444, 0, 88, 1, LocalDate.of(2020,3,9), LocalDate.of(2023,4,9),
                77, pupkin);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract("big", 2, LocalDate.of(2020,4,9), LocalDate.of(2022,4,9), 333, pupkin);
        Person notPupkin = new Person(2, "Elizabeth", "Peterson", "Ivanovna", Gender.FEMALE, 888, 2222, LocalDate.of(1980,4,9));
        WireInternetContract wireInternetContract = new WireInternetContract(100, 4, LocalDate.of(2018,3,9), LocalDate.of(2024,8,9), 444, notPupkin);
        contractRepository.add(mobileConnectionContract, digitalTelevisionContract, wireInternetContract);

        Repository<Contract,Long> contractRepositoryWithElders = contractRepository.getByCriterias(e -> e.getContractOwner().getAge() > 25);
        Contract contract = contractRepositoryWithElders.getByIndex(0);
        Assert.assertTrue(contract.getContractOwner().getAge() > 25);
        Assert.assertNull(contractRepositoryWithElders.getByIndex(1));

    }

    @Test
    public void testSearchByPerson() {
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        Person pupkin = new Person(1, "Vasya", "Pupkin", "Ivanovich", Gender.MALE, 111, 1111, LocalDate.of(2000,4,9));
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract(444, 0, 88, 1, LocalDate.of(2020,3,9), LocalDate.of(2023,4,9),
                77, pupkin);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract("big", 2, LocalDate.of(2020,4,9), LocalDate.of(2022,4,9), 333, pupkin);
        Person notPupkin = new Person(2, "Elizabeth", "Peterson", "Ivanovna", Gender.FEMALE, 888, 2222, LocalDate.of(1980,4,9));
        WireInternetContract wireInternetContract = new WireInternetContract(100, 4, LocalDate.of(2018,3,9), LocalDate.of(2024,8,9), 444, notPupkin);
        contractRepository.add(mobileConnectionContract, digitalTelevisionContract, wireInternetContract);

        Repository<Contract,Long> contractRepository1 = contractRepository.getByCriterias(e -> e.getContractOwner().equals(pupkin));
        for (int i = 0; i < 2; i++) {
            Contract contract = contractRepository1.getByIndex(i);
            if (contract != null) {
                Assert.assertEquals("Pupkin", contract.getContractOwner().getLastName());
            }
        }
    }


}
