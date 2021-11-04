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
import temkarus0070.firstTask.sort.BubbleSort;
import temkarus0070.firstTask.sort.QuickSort;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

public class SortTest {
    @Test
    public void testSort() {
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        Person ivan = new Person(5, "Ivan", "Ivanov", "Ivanovich", Gender.MALE, 2224, 2393, Date.valueOf("2005-09-03"));
        MobileConnectionContract youngestPersonMobileConnectionContract = new MobileConnectionContract(555, 666, 777, 8, Date.valueOf("2021-01-02"), Date.valueOf("2023-01-02"), 5000, ivan);
        Person pupkin = new Person(1, "Vasya", "Pupkin", "Ivanovich", Gender.MALE, 111, 1111, java.sql.Date.valueOf("2000-04-09"));
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract(444, 0, 88, 1, java.sql.Date.valueOf("2020-03-09"), Date.valueOf("2023-04-09"),
                77, pupkin);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract("big", 2, java.sql.Date.valueOf("2020-04-09"), java.sql.Date.valueOf("2022-04-09"), 333, pupkin);
        Person notPupkin = new Person(2, "Elizabeth", "Peterson", "Ivanovna", Gender.FEMALE, 888, 2222, Date.valueOf("1980-04-09"));
        WireInternetContract wireInternetContract = new WireInternetContract(100, 4, Date.valueOf("2018-03-09"), Date.valueOf("2024-08-09"), 444, notPupkin);
        contractRepository.add(wireInternetContract, mobileConnectionContract, digitalTelevisionContract,youngestPersonMobileConnectionContract);
        List<Contract> list = contractRepository.sort(new QuickSort(), new Comparator<Contract>() {
            @Override
            public int compare(Contract o1, Contract o2) {
                if (o1.getContractOwner().getAge() > o2.getContractOwner().getAge())
                    return 1;
                else if (o1.getContractOwner().getAge() == o2.getContractOwner().getAge())
                    return 0;
                return -1;
            }
        });
        for(int i=1;i<list.size();i++){
            Assert.assertTrue(list.get(i-1).getContractOwner().getAge()<=list.get(i).getContractOwner().getAge());
        }
        List<Contract> list1= contractRepository.sort(new BubbleSort(), new Comparator<Contract>() {
            @Override
            public int compare(Contract o1, Contract o2) {
                if (o1.getContractOwner().getAge() > o2.getContractOwner().getAge())
                    return -1;
                else if (o1.getContractOwner().getAge() == o2.getContractOwner().getAge())
                    return 0;
                return 1;
            }
        });
        for(int i=1;i<list1.size();i++){
            Assert.assertTrue(list1.get(i-1).getContractOwner().getAge()>=list1.get(i).getContractOwner().getAge());
        }
    }
}
