package temkarus0070.firstTask.validation.validators;

import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.models.contract.DigitalTelevisionContract;
import temkarus0070.firstTask.validation.Status;
import temkarus0070.firstTask.validation.ValidationResult;
import temkarus0070.firstTask.validation.Validator;

public class TelevisionValidator implements Validator<DigitalTelevisionContract> {
    public TelevisionValidator() {
    }

    @Override
    public ValidationResult validate(DigitalTelevisionContract contract) {
        ValidationResult validationResult = new ValidationResult();

        if (contract.getChannelsPackage() == null || contract.getChannelsPackage().length() == 0) {
            validationResult.setStatus(Status.WARNING);
            validationResult.setFirstErrorField("channelsPackage");
            validationResult.setText("contract should have channels package");

        }
        return validationResult;
    }

    @Override
    public boolean isFitToType(Contract contract) {
        return contract instanceof DigitalTelevisionContract;
    }
}
