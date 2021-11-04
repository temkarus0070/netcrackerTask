package temkarus0070.firstTask.sort;

import temkarus0070.firstTask.ISorter;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.repository.Repository;

import java.util.Comparator;
import java.util.List;

public class BubbleSort implements ISorter<Contract> {
    @Override
    public void sort(Comparator<Contract> comparator, List<Contract> list) {
        for(int i=0;i<list.size()-1;i++){
            for (int j=list.size()-1;j>i;j--){
                if(comparator.compare(list.get(j),list.get(j-1))<0)
                    swap(j-1,j,list);
            }
        }
    }


    private void swap(int first,int second,List<Contract> list){
        Contract temp=list.get(first);
        list.set(first,list.get(second));
        list.set(second,temp);
    }
}
