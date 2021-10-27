package temkarus0070.firstTask.repository;

import temkarus0070.firstTask.models.contract.Contract;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ContractRepositoryImpl implements Repository<Contract, Long> {
    /**
     * static variable to save info about last used identifier
     */
    private static long id = 0;

    /**
     * static variable to save all ContractRepositories to simulate real database
     */
    private static List<Contract> contracts;

    public ContractRepositoryImpl() {
        if (contracts == null) {
            contracts = new ArrayListImpl();
        }
    }

    /**
     * Get contract by id
     *
     * @param id - unique contract identifier
     * @return Optional with needed contract
     */
    @Override
    public Optional<Contract> get(Long id) {
        Optional<Contract> contractOptional = contracts.stream().filter(contract -> id.equals(contract.getId()))
                .findFirst();
        return contractOptional;
    }

    /**
     * Add one or more contracts in repository
     *
     * @param contract - one or more contracts
     */
    @Override
    public void add(Contract... contract) {
        for (Contract contract1 : contract) {
            if (contract1.getId() == 0)
                contract1.setId(id++);
            contracts.add(contract1);
        }
    }

    /**
     * Remove contract from repository by contract id
     *
     * @param id - unique contract identifier
     */
    @Override
    public void remove(Long id) {
        int index = -1;
        for (int i = 0; i < contracts.size(); i++) {
            if (contracts.get(i) != null && id.equals(contracts.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            contracts.remove(index);
        }
    }

}
