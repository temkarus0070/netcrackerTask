package temkarus0070.firstTask.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.JUnit4;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;
import temkarus0070.firstTask.repository.ArrayListImpl;
import temkarus0070.firstTask.repository.Repository;
import temkarus0070.firstTask.repository.RepositoryImpl;

import java.util.*;

public class RepositoryTest {
    Random random=new Random(1999);
    private Collection<Contract> contracts;
    @Test
    public void testAdding(){
        Long num= random.nextLong();
        RepositoryImpl repository=new RepositoryImpl();
        Contract contract=new DigitalTelevisionContract();
        contract.setId(num);
        repository.add(contract);
        Assert.assertSame(repository.get(num).get(),contract);
        repository.remove(num);
    }


    @Test
    public void testGetting(){
        Long num= random.nextLong();
        RepositoryImpl repository=new RepositoryImpl();
        Contract contract=new DigitalTelevisionContract();
        contract.setId(num);
        repository.add(contract);
        repository=new RepositoryImpl();
        Assert.assertSame(repository.get(num).get(),contract);
        repository.remove(num);
    }

    @Test
    public void testRemove(){
        int num= random.nextInt(3000);
        RepositoryImpl repository=new RepositoryImpl();
        Contract contract=new DigitalTelevisionContract();
        contract.setId(num);
        repository.add(contract);
        repository.remove((long)num);
        Assert.assertTrue(repository.get((long)num).isEmpty());
    }

    @Test
    public void addManyContracts(){
        RepositoryImpl repository=new RepositoryImpl();
        Collection<Contract> contractCollection=generateBigCollectionOfContracts();
        contractCollection.forEach(contract -> repository.add(contract));
        for (Contract contract:contractCollection){
            Assert.assertTrue(repository.get(contract.getId()).isPresent());
        }
        for(Contract contract:contractCollection){
            repository.remove(contract.getId());
        }


    }

    @Test
    public void removeManyContracts(){
        RepositoryImpl repository=new RepositoryImpl();
        Collection<Contract> contractCollection=generateBigCollectionOfContracts();
        contractCollection.forEach(contract -> repository.add(contract));
        for (Contract contract:contractCollection){
            repository.remove(contract.getId());
            boolean b=repository.get(contract.getId()).isEmpty();
            Assert.assertTrue(repository.get(contract.getId()).isEmpty());
        }
    }

    private Collection<Contract> generateBigCollectionOfContracts(){
        if(contracts==null) {
            int n = random.nextInt(20000);
            HashSet<Integer> usedIds = new HashSet<>();
            Collection<Contract> contractCollection = new ArrayListImpl();
            for (int i = 0; i < n; i++) {
                int choice = random.nextInt(3);
                Contract contract = null;
                int id = 0;
                do {
                    id = random.nextInt(100000000);
                }
                while (usedIds.contains(id));
                usedIds.add(id);
                switch (choice) {
                    case 0:
                        contract = new DigitalTelevisionContract();
                        contract.setId(id);
                        contractCollection.add(contract);
                        break;
                    case 1:
                        contract = new MobileConnectionContract();
                        contract.setId(id);
                        contractCollection.add(contract);
                        break;
                    case 2:
                        contract = new WireInternetContract();
                        contract.setId(id);
                        contractCollection.add(contract);
                        break;

                }
            }
            contracts=contractCollection;
            return contractCollection;
        }
        else return contracts;
    }
}

