package temkarus0070.firstTask.repository;

import temkarus0070.firstTask.ISorter;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Repository<T, ID> {

    public Optional<T> get(ID id);

    public void add(T... entity);

    public void remove(ID id);

    public List<T> getByCriterias(Predicate<T> ...predicates);

    public List<T> sort(ISorter<T> sorter, Comparator<T> comparator);


}
