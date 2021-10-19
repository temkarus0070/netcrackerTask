package temkarus0070.firstTask.models.contract;

import temkarus0070.firstTask.models.Person;

import java.util.Date;
import java.util.Objects;

public abstract class Contract {
    private long id;
    private Date beginDate;
    private Date endDate;
    private Integer contractNum;
    private Person contractOwner;

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
