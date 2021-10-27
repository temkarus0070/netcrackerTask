import org.junit.Assert;
import org.junit.Test;
import temkarus0070.firstTask.models.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TestPersonCustomFunctions {
    /**
     * Test age calculate function
     */
    @Test
    public void checkAge() {
        Person person = new Person();
        LocalDate localDate = LocalDate.of(2000, 1, 1);
        person.setBirthDate(new Date(localDate.toEpochDay()));
        Assert.assertEquals(person.getAge(), 21);

        person = new Person();
        localDate = LocalDate.of(2000, 12, 12);
        person.setBirthDate(new Date(localDate.toEpochDay()));
        Assert.assertEquals(person.getAge(), 20);
    }
}
