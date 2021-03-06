package temkarus0070.firstTask.repository;

import temkarus0070.firstTask.ISorter;
import temkarus0070.firstTask.models.contract.Contract;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ContractRepositoryImpl implements Repository<Contract, Long> {
    /**
     * variable to save info about last used identifier
     */
    private long id = 0;

    private int contractNum=100;

    private long personId = 0;

    /**
     * contracts list
     */
    private List<Contract> contracts = new ArrayListImpl();


    /**
     * Get contract by id
     *
     * @param id - unique contract identifier
     * @return Optional with needed contract
     */
    @Override
    public Optional<Contract> get(Long id) {
        Optional<Contract> contractOptional = contracts.stream()
                .filter(contract -> id.equals(contract.getId()))
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
            if (contract1.getContractOwner().getId() == 0) {
                contract1.getContractOwner().setId(personId++);
            }
            contract1.setContractNum(contractNum++);
            contracts.add(contract1);
        }
    }

    @Override
    public Contract getByIndex(int index) {
        return contracts.get(index);
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

    @Override
    public Repository<Contract, Long> getByCriterias(Predicate<Contract>... predicates) {
        boolean hasAllCriterias;
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        List<Contract> filteredContacts = new ArrayListImpl();
        for (Contract contract : contracts) {
            hasAllCriterias = true;
            for (Predicate<Contract> predicate : predicates) {
                if (!predicate.test(contract)) {
                    hasAllCriterias = false;
                    break;
                }
            }
            if (hasAllCriterias) {
                filteredContacts.add(contract);
            }

        }
        contractRepository.contracts = filteredContacts;
        return contractRepository;
    }

    @Override
    public void sort(ISorter<Contract> sorter, Comparator<Contract> comparator) {
        sorter.setList(contracts);
        sorter.setComparator(comparator);
        sorter.sort();
    }

}
