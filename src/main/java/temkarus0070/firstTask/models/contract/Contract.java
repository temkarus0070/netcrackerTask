package temkarus0070.firstTask.models.contract;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.xmlLoader.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Objects;

@XmlSeeAlso({DigitalTelevisionContract.class, MobileConnectionContract.class, WireInternetContract.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Contract {
    private long id;

    @XmlElement(name = "begin_date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate beginDate;

    @XmlElement(name = "end_date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private Integer contractNum;
    private Person contractOwner;

    public Contract() {

    }

    public Contract(long id, LocalDate beginDate, LocalDate endDate, Integer contractNum, Person contractOwner) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.contractNum = contractNum;
        this.contractOwner = contractOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        Contract contract = (Contract) o;
        return id == contract.id && Objects.equals(beginDate, contract.beginDate) && Objects.equals(endDate, contract.endDate) && Objects.equals(contractNum, contract.contractNum) && Objects.equals(contractOwner, contract.contractOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beginDate, endDate, contractNum, contractOwner);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getContractNum() {
        return contractNum;
    }

    public void setContractNum(Integer contractNum) {
        this.contractNum = contractNum;
    }

    public Person getContractOwner() {
        return contractOwner;
    }

    public void setContractOwner(Person contractOwner) {
        this.contractOwner = contractOwner;
    }
}
