package temkarus0070.firstTask.JDBCLoaders;

import temkarus0070.firstTask.models.Gender;
import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.models.contract.WireInternetContract;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractExtractor {
    public Contract extract(ResultSet set) {
        try {
            String type = set.getString("contract_type");
            switch (type) {
                case "DigitalTV":
                    DigitalTelevisionContract digitalTelevisionContract = new DigitalTelevisionContract();
                    fillCommonColumns(digitalTelevisionContract, set);
                    digitalTelevisionContract.setChannelsPackage(set.getString("tv_channels"));
                    return digitalTelevisionContract;

                case "Mobile":
                    MobileConnectionContract mobileConnectionContract = new MobileConnectionContract();
                    fillCommonColumns(mobileConnectionContract, set);
                    mobileConnectionContract.setMinutesCount(set.getInt("mobile_minutes_count"));
                    mobileConnectionContract.setSmsCount(set.getInt("mobile_sms_count"));
                    mobileConnectionContract.setGigabytesTraffic(set.getInt("mobile_gigabytes_traffic"));
                    return mobileConnectionContract;

                case "WireInternet":
                    WireInternetContract wireInternetContract = new WireInternetContract();
                    fillCommonColumns(wireInternetContract, set);
                    wireInternetContract.setConnectionSpeed(set.getInt("internet_connection_speed"));
                    return wireInternetContract;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void fillCommonColumns(Contract contract, ResultSet resultSet) throws SQLException {
        contract.setId(resultSet.getInt("id"));
        contract.setBeginDate(resultSet.getDate("begin_date").toLocalDate());
        contract.setEndDate(resultSet.getDate("end_date").toLocalDate());
        contract.setContractNum(resultSet.getInt("contract_num"));
        Person person = new Person();
        person.setId(resultSet.getLong("contract_owner_id"));
        person.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        person.setGender(Gender.valueOf(resultSet.getString("gender")));
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setSurname(resultSet.getString("sur_name"));
        person.setPassportChapter(resultSet.getInt("passport_chapter"));
        person.setPassportNum(resultSet.getInt("passport_num"));
        contract.setContractOwner(person);
    }
}
