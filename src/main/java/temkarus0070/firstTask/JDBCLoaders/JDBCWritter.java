package temkarus0070.firstTask.JDBCLoaders;

import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCWritter {
    private Connection connection;
    private ContractJDBCstatementFiller contractJDBCstatementFiller = new ContractJDBCstatementFiller();
    private PersonWritter personWritter = new PersonWritter();

    private String sqlForTables = "CREATE TABLE IF NOT EXISTS Persons(id bigint primary key, first_name varchar(255),last_name varchar(255),sur_name varchar(255),gender varchar(30), passport_chapter integer," +
            "passport_num integer, birth_date date);" +
            "CREATE TABLE IF NOT EXISTS CONTRACTS(" +
            "id bigint primary key,contract_type varchar(20), begin_date date, end_date date, contract_num integer, contract_owner_id bigint," +
            "tv_channels varchar(255),mobile_minutes_count integer, mobile_sms_count integer, mobile_gigabytes_traffic integer," +
            " internet_connection_speed integer," +
            "foreign key (contract_owner_id) references Persons(id));";

    public JDBCWritter(String connectionString, String username, String password) {
        try {
            connection = DriverManager.getConnection(connectionString, username, password);
            connection.createStatement().executeUpdate("DROP ALL OBJECTS");
            connection.createStatement().executeUpdate(sqlForTables);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void save(ContractRepositoryImpl contractRepository) {
        PreparedStatement preparedStatement = null;
        try (PreparedStatement contractStatement = connection.prepareStatement("INSERT INTO CONTRACTS VALUES (?,?,?,?,?,?,?,?,?,?,?);")) {
            int i = 0;
            while (contractRepository.getByIndex(i) != null) {
                Contract contract = contractRepository.getByIndex(i);
                preparedStatement = personWritter.fillPersonIfNotExists(connection, contract.getContractOwner());
                if (preparedStatement != null) {
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
                contractJDBCstatementFiller.contractFill(contractStatement, contract);
                contractStatement.addBatch();
                i++;
            }
            int[] ints = contractStatement.executeBatch();
            System.out.printf("i");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
