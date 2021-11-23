package secondTask;

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

public class TestForCriteriasSearch {
    @Test
    public void testSearchByAge() {
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        Person pupkin = new Person(1, "Vasya", "Pupkin", "Ivanovich", Gender.MALE, 111, 1111, java.sql.Date.valueOf("2000-04-09"));
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract(444, 0, 88, 1, java.sql.Date.valueOf("2020-03-09"), Date.valueOf("2023-04-09"),
                77, pupkin);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract("big", 2, java.sql.Date.valueOf("2020-04-09"), java.sql.Date.valueOf("2022-04-09"), 333, pupkin);
        Person notPupkin = new Person(2, "Elizabeth", "Peterson", "Ivanovna", Gender.FEMALE, 888, 2222, Date.valueOf("1980-04-09"));
        WireInternetContract wireInternetContract = new WireInternetContract(100, 4, Date.valueOf("2018-03-09"), Date.valueOf("2024-08-09"), 444, notPupkin);
        contractRepository.add(mobileConnectionContract, digitalTelevisionContract, wireInternetContract);

        Repository<Contract,Long> contractRepositoryWithElders = contractRepository.getByCriterias(e -> e.getContractOwner().getAge() > 25);
        Contract contract = contractRepositoryWithElders.getByIndex(0);
        Assert.assertTrue(contract.getContractOwner().getAge() > 25);
        Assert.assertNull(contractRepositoryWithElders.getByIndex(1));

    }

    @Test
    public void testSearchByPerson() {
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        Person pupkin = new Person(1, "Vasya", "Pupkin", "Ivanovich", Gender.MALE, 111, 1111, java.sql.Date.valueOf("2000-04-09"));
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract(444, 0, 88, 1, java.sql.Date.valueOf("2020-03-09"), Date.valueOf("2023-04-09"),
                77, pupkin);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract("big", 2, java.sql.Date.valueOf("2020-04-09"), java.sql.Date.valueOf("2022-04-09"), 333, pupkin);
        Person notPupkin = new Person(2, "Elizabeth", "Peterson", "Ivanovna", Gender.FEMALE, 888, 2222, Date.valueOf("1980-04-09"));
        WireInternetContract wireInternetContract = new WireInternetContract(100, 4, Date.valueOf("2018-03-09"), Date.valueOf("2024-08-09"), 444, notPupkin);
        contractRepository.add(mobileConnectionContract, digitalTelevisionContract, wireInternetContract);

        Repository<Contract,Long> contractRepository1 = contractRepository.getByCriterias(e -> e.getContractOwner().equals(pupkin));
        for (int i = 0; i < 2; i++) {
            Contract contract = contractRepository1.getByIndex(i);
            if (contract != null) {
                Assert.assertEquals("Pupkin", contract.getContractOwner().getLastName());
            }
        }
    }

    @Test
    public void testSearchByPersonAndAge() {
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        Person pupkin = new Person(1, "Vasya", "Pupkin", "Ivanovich", Gender.MALE, 111, 1111, java.sql.Date.valueOf("2000-04-09"));
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract(444, 0, 88, 1, java.sql.Date.valueOf("2020-03-09"), Date.valueOf("2023-04-09"),
                77, pupkin);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract("big", 2, java.sql.Date.valueOf("2020-04-09"), java.sql.Date.valueOf("2022-04-09"), 333, pupkin);
        Person notPupkin = new Person(2, "Elizabeth", "Peterson", "Ivanovna", Gender.FEMALE, 888, 2222, Date.valueOf("1980-04-09"));
        WireInternetContract wireInternetContract = new WireInternetContract(100, 4, Date.valueOf("2018-03-09"), Date.valueOf("2024-08-09"), 444, notPupkin);
        contractRepository.add(mobileConnectionContract, digitalTelevisionContract, wireInternetContract);

        Repository<Contract,Long> contractRepository1 = contractRepository.getByCriterias(e -> e.getContractOwner().equals(pupkin),e->e.getContractOwner().getAge()>30);
        Assert.assertNull(contractRepository1.getByIndex(0));
    }


}
