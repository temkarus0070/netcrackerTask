package temkarus0070.firstTask.repository;

import temkarus0070.firstTask.models.contract.Contract;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ArrayListImpl implements List<Contract> {
    private Contract[] array;
    private int size;


    public ArrayListImpl(){
        array =new Contract[50];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o)!=-1;
    }

    @Override
    public Iterator<Contract> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Contract[] contracts1=new Contract[size];
        for(int i=0;i<size;i++){
            contracts1[i]= array[i];
        }
        return contracts1;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        Contract[] contracts1=new Contract[size];
        for(int i=0;i<size;i++){
            contracts1[i]= array[i];
        }
        return (T[]) contracts1;
    }


    @Override
    public Stream<Contract> stream() {
        return Stream.of(array).limit(size);
    }

    @Override
    public boolean add(Contract contract) {
        if(size+1>(array.length*0.75))
            extend();
        array[size]=contract;
        size++;
        return true;
    }

    private void extend(){
        Contract[]newArray=new Contract[array.length*2];
        for(int i=0;i<size;i++){
            newArray[i]= array[i];
        }
        array =newArray;
    }

    @Override
    public boolean remove(Object o) {
        int index=indexOf(o);
        if(index!=-1){
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
        for(Contract contract:collection){
            add(contract);

        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Contract> collection) {
        for(int index=i;index<collection.size();index++)
            add(index,collection.iterator().next());
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
        array=new Contract[50];
    }

    @Override
    public Contract get(int i) {
        return array[i];
    }

    @Override
    public Contract set(int i, Contract contract) {
        array[i]=contract;
        return contract;
    }

    @Override
    public void add(int i, Contract contract) {
        array[i]=contract;
    }

    @Override
    public Contract remove(int i) {
        array[i]=null;
        if(size>i+1){
        moveArray(i);
        }
        return array[i];
    }

    private void moveArray(int index){
        for(int i=size-1;i>index;i--){
            array[i-1]=array[i];
            array[i]=null;
        }
    }

    @Override
    public int indexOf(Object o) {
        for(int i=0;i<size;i++){
            if(array[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index=-1;
        for(int i=0;i<size;i++){
            if(array[i].equals(o))
                index= i;
        }
        return index;
    }

    @Override
    public ListIterator<Contract> listIterator() {
        return new ListIterator<Contract>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Contract next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Contract previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(Contract contract) {

            }

            @Override
            public void add(Contract contract) {

            }
        };
    }

    @Override
    public ListIterator<Contract> listIterator(int i) {
        return null;
    }

    @Override
    public List<Contract> subList(int i, int i1) {
        return null;
    }

    @Override
    public void sort(Comparator<? super Contract> c) {
        List.super.sort(c);
    }

    private void quickSort(int begin,int end,Comparator<? super Contract> c){

    }
}
