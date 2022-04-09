package temkarus0070.firstTask.JDBCLoaders;

import temkarus0070.firstTask.models.Person;

import java.sql.*;

public class PersonWritter {
    public PreparedStatement fillPersonIfNotExists(Connection connection, Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Persons p where p.id=?");
            preparedStatement.setLong(1, person.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first())
                return null;
            else {
                PreparedStatement personStatement = connection.prepareStatement("INSERT INTO PERSONS VALUES (?,?,?,?,?,?,?,?)");
                personStatement.setLong(1, person.getId());
                personStatement.setString(2, person.getFirstName());
                personStatement.setString(3, person.getLastName());
                personStatement.setString(4, person.getSurname());
                personStatement.setString(5, person.getGender().toString());
                personStatement.setInt(6, person.getPassportChapter());
                personStatement.setInt(7, person.getPassportNum());
                personStatement.setDate(8, Date.valueOf(person.getBirthDate()));
                return personStatement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
