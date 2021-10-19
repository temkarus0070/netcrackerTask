package temkarus0070.firstTask.models.contract;

import temkarus0070.firstTask.models.Person;

import java.util.Date;
import java.util.Objects;

public class WireInternetContract extends Contract {
    private int connectionSpeed;

    public WireInternetContract(int connectionSpeed, int id, Date beginDate, Date endDate, Integer contractNum, Person personOwner) {
        this.connectionSpeed=connectionSpeed;
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
        WireInternetContract that = (WireInternetContract) o;
        return connectionSpeed == that.connectionSpeed && super.equals(o);
    }



    @Override
    public int hashCode() {
        return Objects.hash(connectionSpeed);
    }

    public int getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(int connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }
}
