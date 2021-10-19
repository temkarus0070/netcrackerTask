package temkarus0070.firstTask.repository;

import temkarus0070.firstTask.models.contract.Contract;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RepositoryImpl implements Repository<Contract,Long> {
    private static long id=0;
    private static List<Contract> contracts;

    public RepositoryImpl(){
        if(contracts==null){
            contracts=new ArrayListImpl();
        }
    }

    @Override
    public Contract get(Long aLong) {
        Optional<Contract> contractOptional=contracts.stream().filter(contract -> contract.getId()==aLong)
                .findFirst();
        return contractOptional.get();
    }

    @Override
    public void add(Contract... entity) {
        for(Contract contract:entity){
            if(contract.getId()==0)
                contract.setId(id++);
            contracts.add(contract);
        }
    }

    @Override
    public void remove(Long aLong) {
        int index=-1;
        for(int i=0;i<contracts.size();i++){
            if(contracts.get(i).getId()==aLong){
                index=i;
            }
        }
        if(index!=-1){
            contracts.remove(index);
        }
    }

}
