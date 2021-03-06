package temkarus0070.firstTask.validation.validators;

import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.WireInternetContract;
import temkarus0070.firstTask.validation.Status;
import temkarus0070.firstTask.validation.ValidationResult;
import temkarus0070.firstTask.validation.Validator;

public class InternetContractValidator implements Validator<WireInternetContract> {
    @Override
    public ValidationResult validate(WireInternetContract contract) {
        ValidationResult validationResult = new ValidationResult();
        if (contract.getConnectionSpeed() < 0) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Internet speed shouldn't be less than 0");
            validationResult.setFirstErrorField("connectionSpeed");
        } else if (contract.getConnectionSpeed() == 0) {
            validationResult.setStatus(Status.WARNING);
            validationResult.setText("Internet speed should be greater than zero");
            validationResult.setFirstErrorField("connectionSpeed");
        }
        return validationResult;
    }

    @Override
    public boolean isFitToType(Contract contract) {
        return contract instanceof WireInternetContract;
    }
}
