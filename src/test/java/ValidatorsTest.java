import org.junit.Assert;
import org.junit.Test;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;
import temkarus0070.firstTask.validation.Status;
import temkarus0070.firstTask.validation.ValidationResult;
import temkarus0070.firstTask.validation.Validator;
import temkarus0070.firstTask.validation.validators.*;

import java.time.LocalDate;

public class ValidatorsTest {


    @Test
    public void testContractValidation() {
        ContractValidator contractValidator = new ContractValidator();
        ValidationResult validationResult = new ValidationResult();
        Person person = new Person();
        person.setBirthDate(LocalDate.of(1990, 1, 2));
        person.setFirstName("Vasya");
        person.setLastName("Pupkin");
        person.setPassportChapter(2014);
        person.setPassportChapter(199999);
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract();
        mobileConnectionContract.setBeginDate(LocalDate.of(2021, 1, 2));
        mobileConnectionContract.setEndDate(LocalDate.of(2020, 1, 2));
        validationResult = contractValidator.validate(mobileConnectionContract);
        Assert.assertEquals(validationResult.getStatus(),Status.WARNING);
        Assert.assertEquals(validationResult.getFirstErrorField(), "beginDate && endDate");

        mobileConnectionContract.setBeginDate(null);
        mobileConnectionContract.setEndDate(LocalDate.of(2020, 1, 2));
        validationResult = contractValidator.validate(mobileConnectionContract);
        Assert.assertEquals(validationResult.getStatus(),Status.ERROR);
        Assert.assertEquals(validationResult.getFirstErrorField(), "beginDate");
    }

    @Test
    public void testInternetContractValidator() {
        InternetContractValidator internetContractValidator = new InternetContractValidator();
        ValidationResult validationResult = new ValidationResult();
        Person person = new Person();
        person.setBirthDate(LocalDate.of(1990, 1, 2));
        person.setFirstName("Vasya");
        person.setLastName("Pupkin");
        person.setPassportChapter(2014);
        person.setPassportChapter(199999);
        WireInternetContract wireInternetContract = new WireInternetContract();
        wireInternetContract.setContractOwner(person);
        wireInternetContract.setConnectionSpeed(-100);
        validationResult = internetContractValidator.validate(wireInternetContract);
        Assert.assertEquals(validationResult.getStatus(),Status.ERROR);
        Assert.assertEquals(validationResult.getFirstErrorField(), "connectionSpeed");
        wireInternetContract.setConnectionSpeed(0);
        validationResult = internetContractValidator.validate(wireInternetContract);
        Assert.assertEquals(validationResult.getStatus(),Status.WARNING);
        Assert.assertEquals(validationResult.getFirstErrorField(), "connectionSpeed");
        wireInternetContract.setConnectionSpeed(100);
        validationResult = internetContractValidator.validate(wireInternetContract);
        Assert.assertEquals(validationResult.getStatus(), Status.OK);
    }

    @Test
    public void testMobileContractValidation() {
        MobileContractValidator mobileConnectionContractValidator = new MobileContractValidator();
        ValidationResult validationResult = new ValidationResult();
        Person person = new Person();
        person.setBirthDate(LocalDate.of(1990, 1, 2));
        person.setFirstName("Vasya");
        person.setLastName("Pupkin");
        person.setPassportChapter(2014);
        person.setPassportChapter(199999);
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract();
        mobileConnectionContract.setBeginDate(LocalDate.of(2021, 1, 2));
        mobileConnectionContract.setEndDate(LocalDate.of(2020, 1, 2));
        mobileConnectionContract.setMinutesCount(0);
        mobileConnectionContract.setGigabytesTraffic(100);
        mobileConnectionContract.setSmsCount(300);
        mobileConnectionContract.setContractOwner(person);
        validationResult = mobileConnectionContractValidator.validate(mobileConnectionContract);
        Assert.assertEquals(validationResult.getStatus(), Status.WARNING);
        Assert.assertEquals(validationResult.getFirstErrorField(), "minutesCount");
        mobileConnectionContract.setMinutesCount(100);
        mobileConnectionContract.setSmsCount(-300);

        validationResult = mobileConnectionContractValidator.validate(mobileConnectionContract);
        Assert.assertEquals(validationResult.getStatus(), Status.ERROR);
        Assert.assertEquals(validationResult.getFirstErrorField(), "smsCount");
        mobileConnectionContract.setSmsCount(100);
        mobileConnectionContract.setGigabytesTraffic(-80);
        validationResult = mobileConnectionContractValidator.validate(mobileConnectionContract);
        Assert.assertEquals(validationResult.getStatus(), Status.ERROR);
        Assert.assertEquals(validationResult.getFirstErrorField(), "gigabytesTraffic");
    }

    @Test
    public void testTelevisionContract() {
        ValidationResult validationResult = new ValidationResult();
        TelevisionValidator televisionValidator = new TelevisionValidator();
        Person person = new Person();
        person.setBirthDate(LocalDate.of(1990, 1, 2));
        person.setFirstName("Vasya");
        person.setLastName("Pupkin");
        person.setPassportChapter(2014);
        person.setPassportChapter(199999);
        DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract();
        digitalTelevisionContract.setBeginDate(LocalDate.of(2021, 1, 2));
        digitalTelevisionContract.setEndDate(LocalDate.of(2020, 1, 2));
        digitalTelevisionContract.setChannelsPackage(null);
        validationResult = televisionValidator.validate(digitalTelevisionContract);
        Assert.assertEquals(validationResult.getStatus(), Status.WARNING);
        Assert.assertEquals(validationResult.getFirstErrorField(), "channelsPackage");
    }

    @Test
    public void testPersonValidation() {
        ContractOwnerValidator contractOwnerValidator = new ContractOwnerValidator();
        ValidationResult validationResult = new ValidationResult();
        Person person = new Person();
        person.setBirthDate(LocalDate.of(1990, 1, 2));
        person.setFirstName("Vasya");
        person.setLastName("Pupkin");
        person.setPassportChapter(0000);
        person.setPassportNum(199999);
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract();
        mobileConnectionContract.setMinutesCount(-1);
        mobileConnectionContract.setContractOwner(person);
        validationResult = contractOwnerValidator.validate(mobileConnectionContract);
        Assert.assertEquals(validationResult.getFirstErrorField(), "contractOwner.passportChapter");
        person.setPassportChapter(2014);
        person.setBirthDate(null);
        validationResult = contractOwnerValidator.validate(mobileConnectionContract);
        Assert.assertEquals(validationResult.getFirstErrorField(), "contractOwner.birthDate");
        person.setBirthDate(LocalDate.of(2020, 1, 2));
        validationResult = contractOwnerValidator.validate(mobileConnectionContract);
        Assert.assertEquals(validationResult.getFirstErrorField(), "contractOwner.birthDate");
    }


}
