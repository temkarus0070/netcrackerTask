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
        if (contract.getBeginDate() == null) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setFirstErrorField("beginDate");
            validationResult.setText("beginDate is equal to null ");
        } else if (contract.getBeginDate() != null) {
            if (contract.getEndDate() != null) {
                if (contract.getBeginDate().compareTo(contract.getEndDate()) > 0) {
                    validationResult.setStatus(Status.WARNING);
                    validationResult.setFirstErrorField("beginDate && endDate");
                    validationResult.setText("beginDate is greater than  endDate ");
                }
            }
        }
        else if(contract.getEndDate()==null){
            validationResult.setStatus(Status.ERROR);
            validationResult.setFirstErrorField("endDate");
            validationResult.setText("endDate is equal to null ");
        }
        return validationResult;
    }

    @Override
    public boolean isFitToType(Contract contract) {
        return true;
    }


}
