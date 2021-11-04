package temkarus0070.firstTask;

import java.util.Comparator;
import java.util.List;

public interface ISorter<T> {
    public void sort(Comparator<T> comparator, List<T> list);
}
