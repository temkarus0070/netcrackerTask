package temkarus0070.firstTask.models.contract;

import temkarus0070.firstTask.models.Person;

import java.util.Date;
import java.util.Objects;

public class DigitalTelevisionContract extends Contract {
    private String channelsPackage;

    public DigitalTelevisionContract(String channelsPackage, int id, Date beginDate, Date endDate, Integer contractNum, Person personOwner) {
        this.channelsPackage = channelsPackage;
        setId(id);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setContractNum(contractNum);
        setContractOwner(personOwner);
    }

    public DigitalTelevisionContract() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DigitalTelevisionContract)) return false;
        DigitalTelevisionContract that = (DigitalTelevisionContract) o;
        return Objects.equals(channelsPackage, that.channelsPackage) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelsPackage);
    }

    public String getChannelsPackage() {
        return channelsPackage;
    }

    public void setChannelsPackage(String channelsPackage) {
        this.channelsPackage = channelsPackage;
    }
}
