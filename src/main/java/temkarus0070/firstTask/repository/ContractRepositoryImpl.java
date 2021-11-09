package temkarus0070.firstTask.repository;

import temkarus0070.firstTask.ISorter;
import temkarus0070.firstTask.models.contract.Contract;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContractRepositoryImpl implements Repository<Contract, Long> {
    /**
     * static variable to save info about last used identifier
     */
    private static long id = 0;

    private Predicate<Contract>[] predicates;
    private List<Contract> cachedContracts;
    private boolean isSorted;
    private ISorter<Contract> sorter;

    /**
     * static variable to save all ContractRepositories to simulate real database
     */
    private static List<Contract> contracts;

    public ContractRepositoryImpl() {
        if (contracts == null) {
            contracts = new ArrayListImpl();
        }
        cachedContracts = contracts;
    }

    /**
     * Get contract by id
     *
     * @param id - unique contract identifier
     * @return Optional with needed contract
     */
    @Override
    public Optional<Contract> get(Long id) {
        if (predicates != null) {
            filter();
        }
        Optional<Contract> contractOptional = cachedContracts.stream().filter(contract -> id.equals(contract.getId()))
                .findFirst();
        return contractOptional;
    }

    private void filter() {
        Stream<Contract> contractStream = contracts.stream();
        for (Predicate<Contract> predicate : predicates) {
            contractStream = contractStream.filter(predicate);
        }
        List<Contract> contractList = contractStream.collect(Collectors.toList());
        if (!contractList.equals(cachedContracts)) {
            cachedContracts = contractList;
            isSorted = false;
        }

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

    @Override
    public Contract getByIndex(int index) {
        if (predicates != null)
            filter();
        if (!isSorted) {
            sorter.sort();
            isSorted = true;
        }
        return cachedContracts.get(index);
    }

    /**
     * Remove contract from repository by contract id
     *
     * @param id - unique contract identifier
     */
    @Override
    public void remove(Long id) {
        removeFromList(contracts, contract -> id.equals(contract.getId()));
    }

    private void removeFromList(List<Contract> contracts, Predicate<Contract> predicate) {
        for (int i = 0; i < contracts.size(); i++) {
            Contract contract = contracts.get(i);
            if (contract != null && predicate.test(contract)) {
                contracts.remove(i);
                break;
            }
        }
    }

    @Override
    public ContractRepositoryImpl getByCriterias(Predicate<Contract>... predicates) {
        ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
        contractRepository.predicates = predicates;
        return contractRepository;
    }

    @Override
    public void sort(ISorter<Contract> sorter, Comparator<Contract> comparator) {
        this.sorter = sorter;
        this.sorter.setList(cachedContracts);
        this.sorter.setComparator(comparator);
    }


}
