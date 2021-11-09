package temkarus0070.firstTask.repository;

import temkarus0070.firstTask.ISorter;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public interface Repository<T, ID> {

    public Optional<T> get(ID id);

    public void add(T... entity);

    public T getByIndex(int index);


    public void remove(ID id);

    public Repository<T, ID> getByCriterias(Predicate<T>... predicates);

    public void sort(ISorter<T> sorter, Comparator<T> comparator);


}
