import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import temkarus0070.firstTask.csvLoader.CsvLoader;
import temkarus0070.firstTask.di.Injector;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;

import java.util.Collection;
import java.util.Comparator;

public class DITest {
    @Test
    public void repositoryTest(){
        ContractRepositoryImpl contractRepository=new ContractRepositoryImpl();

            Assertions.assertDoesNotThrow(new Executable() {
                @Override
                public void execute() throws Throwable {
                    Injector.inject(contractRepository);
                    contractRepository.sort((o1, o2) -> 0);
                }
            });
    }

    @Test
    public void csvReaderTest(){
        CsvLoader csvLoader=new CsvLoader();
        Assertions.assertDoesNotThrow(()->{
            Injector.inject(csvLoader);
            csvLoader.CsvLoad(new ContractRepositoryImpl(),"src/test/resources/file.csv");
        });




    }
}
