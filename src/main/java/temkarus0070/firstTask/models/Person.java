package temkarus0070.firstTask.models;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private String surname;
    private Gender gender;
    private int passportChapter;
    private int passportNum;
    private Date birthDate;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public Person() {

    }

    public Person(long id, String firstName, String lastName, String surname, Gender gender, int passportChapter, int passportNum, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
        this.gender = gender;
        this.passportChapter = passportChapter;
        this.passportNum = passportNum;
        this.birthDate = birthDate;
    }


    /**
     * Calculate age by birthdate
     *
     * @return age that is calculated with birthdate and today date
     */
    public int getAge() {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(birthDate);
        LocalDate d1 = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate d2 = LocalDate.now();
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        return (int) (diffDays / 365);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getPassportChapter() {
        return passportChapter;
    }

    public void setPassportChapter(int passportChapter) {
        this.passportChapter = passportChapter;
    }

    public int getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(int passportNum) {
        this.passportNum = passportNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && passportChapter == person.passportChapter && passportNum == person.passportNum && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(surname, person.surname) && gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, surname, gender, passportChapter, passportNum);
    }
}
