import org.junit.Assert;
import org.junit.Test;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.repository.ArrayListImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class TestPersonCustomFunctions {
    /**
     * Test age calculate function
     */
    @Test
    public void checkAge() {
        Person person = new Person();
        LocalDate localDate = LocalDate.of(2000, 1, 1);
        Calendar calendar = Calendar.getInstance();
        Date firstBirthday = Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());
        person.setBirthDate(firstBirthday);
        Assert.assertEquals(person.getAge(), 21);

        person = new Person();
        localDate = LocalDate.of(2000, 12, 12);
        firstBirthday = Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());
        person.setBirthDate(firstBirthday);
        Assert.assertEquals(person.getAge(), 20);
    }


    @Test
    public void myArrayListTestAddAllFunction() {
        ArrayListImpl arrayList = new ArrayListImpl();
        MobileConnectionContract mobileConnectionContract = new MobileConnectionContract();
        mobileConnectionContract.setId(0);

        MobileConnectionContract mobileConnectionContract1 = new MobileConnectionContract();
        mobileConnectionContract1.setId(3);

        arrayList.add(mobileConnectionContract);
        arrayList.add(mobileConnectionContract1);

        MobileConnectionContract mobileConnectionContract2 = new MobileConnectionContract();
        mobileConnectionContract2.setId(1);

        MobileConnectionContract mobileConnectionContract3 = new MobileConnectionContract();
        mobileConnectionContract3.setId(2);

        arrayList.addAll(1, Arrays.asList(mobileConnectionContract2, mobileConnectionContract3));
        for (int i = 1; i < arrayList.size(); i++)
            Assert.assertTrue(arrayList.get(i - 1).getId() < arrayList.get(i).getId());


    }
}
