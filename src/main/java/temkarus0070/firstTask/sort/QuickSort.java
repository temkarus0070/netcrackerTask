package temkarus0070.firstTask.sort;

import temkarus0070.firstTask.ISorter;
import temkarus0070.firstTask.models.contract.Contract;

import java.util.Comparator;
import java.util.List;

public class QuickSort implements ISorter<Contract> {
    private List<Contract> list;
    @Override
    public void sort(Comparator<Contract> comparator, List<Contract> list) {
        this.list=list;
        quickSort(0,list.size()-1,comparator);

    }
    private void quickSort(int begin, int end, Comparator<? super Contract> c) {
        if (begin >= end) {
            return;
        }
        int middle = move(begin, end, c);
        quickSort(begin, middle, c);
        quickSort(middle + 1, end, c);
    }

    /**
     * method to get necessary order to  quick sort
     *
     * @param begin - begin of array to sort
     * @param end   - end of array to sort
     * @param c     - comparator
     * @return
     */
    private int move(int begin, int end, Comparator<? super Contract> c) {
        int elementIndex = (begin + end) / 2;
        int l1 = begin;
        int l2 = end;
        while (true) {
            while (c.compare(list.get(l1), list.get(elementIndex)) < 0) {
                l1++;
            }
            while (c.compare(list.get(l2), list.get(elementIndex)) > 0) {
                l2--;
            }
            if (l1 >= l2) {
                return l2;
            }
            swap(l1++, l2--,list);
        }

    }

    private void swap(int first,int second,List<Contract> list){
        Contract temp=list.get(first);
        list.set(first,list.get(second));
        list.set(second,temp);
    }
}
