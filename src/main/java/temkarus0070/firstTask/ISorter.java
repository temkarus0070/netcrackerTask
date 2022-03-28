package temkarus0070.firstTask;

import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlTransient;
import temkarus0070.firstTask.sort.BubbleSort;
import temkarus0070.firstTask.sort.fast.QuickSort;

import java.util.Comparator;
import java.util.List;

@XmlTransient
@XmlSeeAlso({BubbleSort.class, QuickSort.class})
public interface ISorter<T> {
    public void sort();

    public void setList(List<T> list);

    public void setComparator(Comparator<T> comparator);
}
