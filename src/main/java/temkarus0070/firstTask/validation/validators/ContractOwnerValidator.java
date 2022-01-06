package temkarus0070.firstTask.validation.validators;

import temkarus0070.firstTask.models.Person;
import temkarus0070.firstTask.models.contract.Contract;
import temkarus0070.firstTask.validation.Status;
import temkarus0070.firstTask.validation.ValidationResult;
import temkarus0070.firstTask.validation.Validator;

import java.time.LocalDate;

public class ContractOwnerValidator implements Validator<Contract> {
    public ContractOwnerValidator() {
    }

    @Override
    public ValidationResult validate(Contract contract) {
        ValidationResult validationResult = new ValidationResult();
        Person person = contract.getContractOwner();
        if (person == null) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Contract owner is equal to null");
            validationResult.setFirstErrorField("contractOwner");
        } else if (person.getFirstName() == null || person.getFirstName().equals("")) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Contract owner should have firstName");
            validationResult.setFirstErrorField("contractOwner.firstName");
        } else if (person.getLastName() == null || person.getLastName().equals("")) {
            validationResult.setStatus(Status.WARNING);
            validationResult.setText("Contract owner should have lastName");
            validationResult.setFirstErrorField("contractOwner.lastName");
        }
        else if (person.getSurname() == null || person.getSurname().equals("")) {
            validationResult.setStatus(Status.WARNING);
            validationResult.setText("Contract owner should have surname");
            validationResult.setFirstErrorField("contractOwner.surName");
        }
        else if (!validatePassportChapter(person)) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Contract owner passport should have correct passport chapter:greater or equal to 1991 and less or equal to current Year");
            validationResult.setFirstErrorField("contractOwner.passportChapter");
        } else if (!validatePassportNum(person)) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Contract owner passport should have correct passport number consist of 6 digits");
            validationResult.setFirstErrorField("contractOwner.passportNum");
        } else if (!validatePersonBirthDate(person)) {
            validationResult.setStatus(Status.WARNING);
            validationResult.setText("Contract owner birth date should be before current date and shouldn't be null");
            validationResult.setFirstErrorField("contractOwner.birthDate");
        } else if (person.getAge() < 18) {
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Contract owner should be older than 17");
            validationResult.setFirstErrorField("contractOwner.birthDate");
        }

        return validationResult;
}

    @Override
    public boolean isFitToType(Contract contract) {
        return true;
    }

    private boolean validatePassportChapter(Person person) {
        return person.getPassportChapter() >= 1991 && person.getPassportChapter() <= LocalDate.now().getYear();
    }

    private boolean validatePassportNum(Person person) {
        String str = String.valueOf(person.getPassportNum());
        return str.length() == 6;
    }

    private boolean validatePersonBirthDate(Person person) {
        boolean hasNullDate = person.getBirthDate() == null;
        boolean isCorrectDate = true;
        if (!hasNullDate) {
            isCorrectDate = person.getBirthDate().compareTo(LocalDate.now()) < 0;
        }
        return !hasNullDate && isCorrectDate;
    }

}
