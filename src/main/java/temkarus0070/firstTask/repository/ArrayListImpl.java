package temkarus0070.firstTask.repository;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import temkarus0070.firstTask.models.contract.Contract;

import java.util.*;
import java.util.stream.Stream;

/**
 * My realization of dynamic array with List and Iterable interfaces
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ArrayListImpl implements List<Contract>, Iterable<Contract> {
    private Contract[] array;
    private int size;


    public ArrayListImpl() {
        array = new Contract[50];
    }

    public ArrayListImpl(Collection<Contract> contracts) {
        array = new Contract[50];
        for (Contract contract : contracts) {
            add(contract);
        }
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
        try {
            if (size + 1 > (array.length * 0.75))
                extend();
            array[size] = contract;
            size++;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * extend array and rewrite existed values to new array
     */
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
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Contract contract = (Contract) iterator.next();
            if (!contains(contract))
                return false;
        }
        return true;
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
        int collectionSize = collection.size();
        if (size() + collection.size() > array.length) {
            extend();
        }
        int index = i;
        while (array[index] != null) {
            array[index + collectionSize] = array[index];
            index++;

        }
        Iterator<Contract> iterator = (Iterator<Contract>) collection.iterator();
        for (int index1 = i; index1 < this.size && iterator.hasNext(); index1++) {
            array[index1] = iterator.next();
            this.size++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        try {
            collection.forEach(this::remove);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        try {
            collection.forEach(item -> add((Contract) item));
        } catch (Exception ex) {
            return false;
        }
        return true;
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
        ArrayListImpl arrayList = new ArrayListImpl();
        for (int j = 0; j < i1; j++) {
            arrayList.add(get(j));
        }
        return arrayList;
    }

    /**
     * Quick sort of arrayList by given comparator
     *
     * @param c - comparator
     */
    @Override
    public void sort(Comparator<? super Contract> c) {
        quickSort(0, size - 1, c);
    }


    /**
     * Quick sort of array list by given comparator for needed indexes
     *
     * @param begin - begin of array to sort
     * @param end   - end of array to sort
     * @param c     - comparator
     */
    private void quickSort(int begin, int end, Comparator<? super Contract> c) {
        if (begin >= end) {
            return;
        }
        int middle = move(begin, end, c);
        quickSort(begin, middle, c);
        quickSort(middle + 1, end, c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayListImpl)) return false;
        ArrayListImpl contracts = (ArrayListImpl) o;
        return size == contracts.size && Arrays.equals(array, contracts.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
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

    /**
     * change elements in array
     *
     * @param first  - first element to change
     * @param second - second element to change
     */
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
