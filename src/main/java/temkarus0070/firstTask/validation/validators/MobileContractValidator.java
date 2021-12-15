package temkarus0070.firstTask.validation.validators;

import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.validation.Status;
import temkarus0070.firstTask.validation.ValidationResult;
import temkarus0070.firstTask.validation.Validator;

public class MobileContractValidator implements Validator<MobileConnectionContract> {

    @Override
    public ValidationResult validate(MobileConnectionContract contract) {
        ValidationResult validationResult = new ValidationResult();
        if (contract.getGigabytesTraffic() == 0) {
            validationResult.setStatus(Status.WARNING);
            validationResult.setText("traffic gigabytes should be greater than zero");
            validationResult.setFirstErrorField("gigabytesTraffic");
        } else if (contract.getSmsCount() == 0) {
            validationResult.setStatus(Status.WARNING);
            validationResult.setText("Sms count should be greater than zero");
            validationResult.setFirstErrorField("smsCount");
        } else if (contract.getMinutesCount() == 0) {
            validationResult.setStatus(Status.WARNING);
            validationResult.setText("Minutes count should be greater than 0");
            validationResult.setFirstErrorField("minutesCount");
        } else if (contract.getMinutesCount() < 0) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Minutes count shouldn't be less than 0");
            validationResult.setFirstErrorField("minutesCount");
        } else if (contract.getSmsCount() < 0) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Sms count shouldn't be less than 0");
            validationResult.setFirstErrorField("smsCount");
        } else if (contract.getGigabytesTraffic() < 0) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("traffic gigabytes shouldn't be less than 0");
            validationResult.setFirstErrorField("gigabytesTraffic");
        }

        return validationResult;
    }

    @Override
    public boolean isFitToType(Contract contract) {
        return contract instanceof MobileConnectionContract;
    }


}
