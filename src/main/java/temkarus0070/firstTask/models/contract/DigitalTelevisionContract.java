package temkarus0070.firstTask.models.contract;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import temkarus0070.firstTask.models.Person;

import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DigitalTelevisionContract extends Contract {
    private String channelsPackage;

    public DigitalTelevisionContract(String channelsPackage, int id, LocalDate beginDate, LocalDate endDate, Integer contractNum, Person personOwner) {
        super(id,beginDate,endDate,contractNum,personOwner);
        this.channelsPackage = channelsPackage;
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
