package temkarus0070.firstTask.JDBCLoaders;

import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;

public class ContractJDBCstatementFiller {
    public void contractFill(PreparedStatement preparedStatement, Contract contract) {
        try {
            preparedStatement.setLong(1, contract.getId());
            preparedStatement.setDate(3, Date.valueOf(contract.getBeginDate()));
            preparedStatement.setDate(4, Date.valueOf(contract.getEndDate()));
            preparedStatement.setInt(5, contract.getContractNum());
            preparedStatement.setLong(6, contract.getContractOwner().getId());
            if (contract instanceof DigitalTelevisionContract) {
                DigitalTelevisionContract digitalTelevisionContract = (DigitalTelevisionContract) contract;
                preparedStatement.setString(2, "DigitalTV");
                preparedStatement.setString(7, digitalTelevisionContract.getChannelsPackage());

                preparedStatement.setNull(8, Types.INTEGER);
                preparedStatement.setNull(9, Types.INTEGER);
                preparedStatement.setNull(10, Types.INTEGER);
                preparedStatement.setNull(11, Types.INTEGER);
            } else if (contract instanceof MobileConnectionContract) {
                preparedStatement.setString(2, "Mobile");
                preparedStatement.setNull(7, Types.VARCHAR);
                preparedStatement.setNull(11, Types.INTEGER);
                MobileConnectionContract mobileConnectionContract = (MobileConnectionContract) contract;
                preparedStatement.setInt(8, mobileConnectionContract.getMinutesCount());
                preparedStatement.setInt(9, mobileConnectionContract.getSmsCount());
                preparedStatement.setInt(10, mobileConnectionContract.getGigabytesTraffic());
            } else if (contract instanceof WireInternetContract) {
                WireInternetContract wireInternetContract = (WireInternetContract) contract;
                preparedStatement.setString(2, "WireInternet");
                preparedStatement.setNull(7, Types.VARCHAR);
                preparedStatement.setNull(8, Types.INTEGER);
                preparedStatement.setNull(9, Types.INTEGER);
                preparedStatement.setNull(10, Types.INTEGER);
                preparedStatement.setInt(11, wireInternetContract.getConnectionSpeed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
