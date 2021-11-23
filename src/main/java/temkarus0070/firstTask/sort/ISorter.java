package temkarus0070.firstTask.sort;

import java.util.Comparator;
import java.util.List;

public interface ISorter<T> {
    public void sort();
    public void setList(List<T> list);
    public void setComparator(Comparator<T> comparator);
}
