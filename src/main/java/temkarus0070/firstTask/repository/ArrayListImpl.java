package temkarus0070.firstTask.repository;

import temkarus0070.firstTask.models.contract.Contract;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ArrayListImpl implements List<Contract>, Iterable<Contract> {
    private Contract[] array;
    private int size;


    public ArrayListImpl() {
        array = new Contract[50];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<Contract> iterator() {
        return (Iterator<Contract>) listIterator();
    }

    @Override
    public Object[] toArray() {
        Contract[] contracts1 = new Contract[size];
        for (int i = 0; i < size; i++) {
            contracts1[i] = array[i];
        }
        return contracts1;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        Contract[] contracts1 = new Contract[size];
        for (int i = 0; i < size; i++) {
            contracts1[i] = array[i];
        }
        return (T[]) contracts1;
    }


    @Override
    public Stream<Contract> stream() {
        return Stream.of(array).limit(size);
    }

    @Override
    public boolean add(Contract contract) {
        if (size + 1 > (array.length * 0.75))
            extend();
        array[size] = contract;
        size++;
        return true;
    }

    private void extend() {
        Contract[] newArray = new Contract[array.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Contract> collection) {
        for (Contract contract : collection) {
            add(contract);

        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Contract> collection) {
        for (int index = i; index < collection.size(); index++)
            add(index, collection.iterator().next());
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        array = new Contract[50];
    }

    @Override
    public Contract get(int i) {
        return array[i];
    }

    @Override
    public Contract set(int i, Contract contract) {
        array[i] = contract;
        return contract;
    }

    @Override
    public void add(int i, Contract contract) {
        array[i] = contract;
    }

    @Override
    public Contract remove(int i) {
        Contract contract = array[i];
        array[i] = null;
        if (i != size - 1) {
            moveArray(i);
        }
        size--;
        return contract;
    }

    private void moveArray(int index) {
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o))
                index = i;
        }
        return index;
    }

    @Override
    public ListIterator<Contract> listIterator() {
        return new MyListIterator(this);
    }

    @Override
    public ListIterator<Contract> listIterator(int i) {
        return new MyListIterator(this, i);
    }

    @Override
    public List<Contract> subList(int i, int i1) {
        return null;
    }

    @Override
    public void sort(Comparator<? super Contract> c) {
        quickSort(0, size - 1, c);
    }

    private void quickSort(int begin, int end, Comparator<? super Contract> c) {
        if (begin >= end) {
            return;
        }
        int middle = move(begin, end, c);
        quickSort(begin, middle, c);
        quickSort(middle + 1, end, c);
    }

    private int move(int begin, int end, Comparator<? super Contract> c) {
        int elementIndex = (begin + end) / 2;
        int l1 = begin;
        int l2 = end;
        while (true) {

            while (c.compare(array[l1], array[elementIndex]) < 0) {
                l1++;
            }
            while (c.compare(array[l2], array[elementIndex]) > 0) {
                l2--;
            }
            if (l1 >= l2) {
                return l2;
            }
            change(l1++, l2--);
        }

    }

    private void change(int first, int second) {
        Contract temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

}

class MyListIterator implements ListIterator<Contract> {
    int index = 0;
    private ArrayListImpl arrayList;

    public MyListIterator(ArrayListImpl arrayList) {
        this.arrayList = arrayList;
    }

    public MyListIterator(ArrayListImpl arrayList, int index) {
        this.arrayList = arrayList;
        this.index = index;
    }


    @Override
    public boolean hasNext() {
        return index < arrayList.size();
    }

    @Override
    public Contract next() {
        Contract contract = arrayList.get(index);
        index++;
        return contract;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public Contract previous() {
        if (index > 0) {
            index--;
        } else {
            throw new UnsupportedOperationException();
        }
        return arrayList.get(index);
    }

    @Override
    public int nextIndex() {
        return index + 1;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        arrayList.remove(index);
    }

    @Override
    public void set(Contract contract) {
        arrayList.set(index, contract);
    }

    @Override
    public void add(Contract contract) {
        arrayList.add(contract);
    }
}
