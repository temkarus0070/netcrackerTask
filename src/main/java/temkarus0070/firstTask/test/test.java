package temkarus0070.firstTask.test;

import org.junit.Assert;
import org.junit.Test;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.repository.ArrayListImpl;
import temkarus0070.firstTask.repository.Repository;
import temkarus0070.firstTask.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class test {

    @Test
   public void testAdding(){
        RepositoryImpl repository=new RepositoryImpl();
        Contract contract=new DigitalTelevisionContract();
        contract.setId(2);

    }
}
