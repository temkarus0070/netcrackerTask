package temkarus0070.firstTask.sort;

import jakarta.xml.bind.annotation.XmlRootElement;
import temkarus0070.firstTask.models.contract.Contract;

import java.util.Comparator;

@XmlRootElement
public class MyComparator implements Comparator<Contract> {
    @Override
    public int compare(Contract o1, Contract o2) {
        if (o1.getContractOwner().getAge() > o2.getContractOwner().getAge())
            return 1;
        else if (o1.getContractOwner().getAge() == o2.getContractOwner().getAge())
            return 0;
        else return -1;
    }
}
