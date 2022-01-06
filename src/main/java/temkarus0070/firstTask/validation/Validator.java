package temkarus0070.firstTask.validation;

import temkarus0070.firstTask.models.contract.Contract;

public interface Validator<T extends Contract> {
    public  ValidationResult validate(T contract);
    public boolean isFitToType(Contract contract);
}
