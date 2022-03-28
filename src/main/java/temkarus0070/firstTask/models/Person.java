package temkarus0070.firstTask.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import temkarus0070.firstTask.xmlLoader.LocalDateAdapter;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private String surname;
    private Gender gender;
    private int passportChapter;
    private int passportNum;

    @XmlElement(name = "birth_date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public Person() {

    }

    public Person(long id, String firstName, String lastName, String surname, Gender gender, int passportChapter, int passportNum, LocalDate birthDate) {
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
        LocalDate d1 = birthDate;
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
