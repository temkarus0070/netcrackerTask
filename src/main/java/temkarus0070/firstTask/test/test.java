package temkarus0070.firstTask.test;

import org.junit.Assert;
import org.junit.Test;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.repository.ArrayListImpl;
import temkarus0070.firstTask.repository.Repository;
import temkarus0070.firstTask.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class test {

    @Test
   public void testAdding(){
        Repository<Contract,Long> repository=new RepositoryImpl();
        Contract contract=new DigitalTelevisionContract();
        repository.add(contract);
        Contract contract1=repository.get(0L);
        Assert.assertEquals(contract1,contract);
    }
}
