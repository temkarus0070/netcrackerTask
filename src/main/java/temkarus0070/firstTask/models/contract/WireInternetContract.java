package temkarus0070.firstTask.models.contract;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import temkarus0070.firstTask.models.Person;

import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WireInternetContract extends Contract {
    private int connectionSpeed;

    public WireInternetContract(int connectionSpeed, int id, LocalDate beginDate, LocalDate endDate, Integer contractNum, Person personOwner) {
        super(id,beginDate,endDate,contractNum,personOwner);
        this.connectionSpeed = connectionSpeed;
    }

    public WireInternetContract() {

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
