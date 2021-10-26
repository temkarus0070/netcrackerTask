package temkarus0070.firstTask.models.contract;

import temkarus0070.firstTask.models.Person;

import java.util.Date;
import java.util.Objects;

public class MobileConnectionContract extends Contract{
private int minutesCount;
private int smsCount;
private int gigabytesTraffic;




    public MobileConnectionContract(){

    }

    public MobileConnectionContract(int minutesCount, int smsCount, int gigabytesTraffic,int id, Date beginDate, Date endDate, Integer contractNum, Person personOwner) {
        this.minutesCount = minutesCount;
        this.smsCount = smsCount;
        this.gigabytesTraffic = gigabytesTraffic;
        setId(id);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setContractNum(contractNum);
        setContractOwner(personOwner);
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
