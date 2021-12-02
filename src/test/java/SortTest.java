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
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class SortTest {
    @Test
    public void testSort() {
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        Person ivan = new Person(5, "Ivan", "Ivanov", "Ivanovich", Gender.MALE, 2224, 2393, LocalDate.of(2005,9,3));
        MobileConnectionContract youngestPersonMobileConnectionContract = new MobileConnectionContract(555, 666, 777, 8, LocalDate.of(2021,1,2), LocalDate.of(2023,1,2), 5000, ivan);
        Person pupkin = new Person(1, "Vasya", "Pupkin", "Ivanovich", Gender.MALE, 111, 1111, LocalDate.of(2000,4,9));
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract(444, 0, 88, 1, LocalDate.of(2020,3,9), LocalDate.of(2023,4,9),
                77, pupkin);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract("big", 2, LocalDate.of(2020,4,9), LocalDate.of(2022,4,9), 333, pupkin);
        Person veryOldPerson = new Person(2, "Elizabeth", "Peterson", "Ivanovna", Gender.FEMALE, 888, 2222, LocalDate.of(1980,4,9));
        WireInternetContract wireInternetContract = new WireInternetContract(100, 4, LocalDate.of(2018,3,9), LocalDate.of(2024,8,9), 444, veryOldPerson);
        contractRepository.add(wireInternetContract, mobileConnectionContract, digitalTelevisionContract,youngestPersonMobileConnectionContract);

        contractRepository.sort(new QuickSort(), new Comparator<Contract>() {
            @Override
            public int compare(Contract o1, Contract o2) {
                if( o1.getContractOwner().getAge()>o2.getContractOwner().getAge())
                    return 1;
                else if( o1.getContractOwner().getAge()==o2.getContractOwner().getAge())
                    return 0;
                else return -1;
            }
        });

        for(int i=1;i<4;i++){
            Assert.assertTrue(contractRepository.getByIndex(i-1).getContractOwner().getAge()<=contractRepository.getByIndex(i).getContractOwner().getAge());
        }
    }
}
