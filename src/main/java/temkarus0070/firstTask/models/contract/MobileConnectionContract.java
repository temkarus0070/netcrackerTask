package temkarus0070.firstTask.models.contract;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import temkarus0070.firstTask.models.Person;

import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MobileConnectionContract extends Contract{
private int minutesCount;
private int smsCount;
private int gigabytesTraffic;


    public MobileConnectionContract() {

    }

    public MobileConnectionContract(int minutesCount, int smsCount, int gigabytesTraffic, int id, LocalDate beginDate, LocalDate endDate, Integer contractNum, Person personOwner) {
        super(id,beginDate,endDate,contractNum,personOwner);
        this.minutesCount = minutesCount;
        this.smsCount = smsCount;
        this.gigabytesTraffic = gigabytesTraffic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobileConnectionContract that = (MobileConnectionContract) o;
        return minutesCount == that.minutesCount && super.equals(o)&& smsCount == that.smsCount && gigabytesTraffic == that.gigabytesTraffic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutesCount, smsCount, gigabytesTraffic);
    }

    public int getMinutesCount() {
        return minutesCount;
    }

    public void setMinutesCount(int minutesCount) {
        this.minutesCount = minutesCount;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }

    public int getGigabytesTraffic() {
        return gigabytesTraffic;
    }

    public void setGigabytesTraffic(int gigabytesTraffic) {
        this.gigabytesTraffic = gigabytesTraffic;
    }
}
