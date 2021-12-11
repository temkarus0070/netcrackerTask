package temkarus0070.firstTask.validation.validators;

import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.validation.Status;
import temkarus0070.firstTask.validation.ValidationResult;
import temkarus0070.firstTask.validation.Validator;

public class ContractValidator implements Validator<Contract> {
    @Override
    public ValidationResult validate(Contract contract) {
        ValidationResult validationResult = new ValidationResult();
        if (!checkContractDates(contract)) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setFirstErrorField("beginDate || endDate");
            validationResult.setText("beginDate or endDate equals to null or beginDate more than endDate");
        }
        return validationResult;
    }

    @Override
    public boolean isFitToType(Contract contract) {
        return true;
    }

    private boolean checkContractDates(Contract contract) {
        boolean haveNulls = contract.getBeginDate() == null || contract.getEndDate() == null;
        boolean correctDates = true;
        if (!haveNulls)
            correctDates = contract.getBeginDate().compareTo(contract.getEndDate()) <= 0;
        return !haveNulls && correctDates;
    }

}
