package temkarus0070.firstTask.sort;

import temkarus0070.firstTask.ISorter;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.repository.Repository;

import java.util.Comparator;
import java.util.List;

public class BubbleSort implements ISorter<Contract> {
    private Comparator<Contract> comparator;
    private List<Contract> list;

    public BubbleSort() {
    }

    private void swap(int first, int second, List<Contract> list) {
        Contract temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    @Override
    public void sort() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (comparator.compare(list.get(j), list.get(j - 1)) < 0)
                    swap(j - 1, j, list);
            }
        }
    }

    @Override
    public void setList(List<Contract> list) {
        this.list = list;
    }

    @Override
    public void setComparator(Comparator<Contract> comparator) {
        this.comparator = comparator;
    }
}
