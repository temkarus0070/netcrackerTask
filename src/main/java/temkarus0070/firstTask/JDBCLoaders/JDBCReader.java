package temkarus0070.firstTask.JDBCLoaders;

import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;

import java.sql.*;

public class JDBCReader {
    private Connection connection;
    private ContractExtractor contractExtractor = new ContractExtractor();


    public JDBCReader(String connectionString, String username, String password) {
        try {
            connection = DriverManager.getConnection(connectionString, username, password);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public ContractRepositoryImpl read() {
        try (PreparedStatement contractStatement = connection.prepareStatement("SELECT * FROM CONTRACTS c INNER JOIN PERSONS p ON c.contract_owner_id=p.id ")) {
            ContractRepositoryImpl contractRepository = new ContractRepositoryImpl();
            ResultSet resultSet = contractStatement.executeQuery();
            while (resultSet.next()) {
                Contract contract = contractExtractor.extract(resultSet);
                if (contract != null)
                    contractRepository.add(contract);
            }
            return contractRepository;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
