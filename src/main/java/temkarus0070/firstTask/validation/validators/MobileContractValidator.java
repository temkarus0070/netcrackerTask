package temkarus0070.firstTask.validation.validators;

import temkarus0070.firstTask.models.contract.MobileConnectionContract;
import temkarus0070.firstTask.validation.Status;
import temkarus0070.firstTask.validation.ValidationResult;
import temkarus0070.firstTask.validation.Validator;

public class MobileContractValidator implements Validator<MobileConnectionContract> {

    @Override
    public ValidationResult validate(MobileConnectionContract contract) {
        ValidationResult validationResult=new ValidationResult();
        validationResult.setStatus(Status.OK);
        if(!validateMinutesCount(contract)){
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Minutes count should be more or equal to zero");
            validationResult.setFirstErrorField("minutesCount");
        }
        else if(!validateSmsCount(contract)){
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("Sms count should be more or equal to zero");
            validationResult.setFirstErrorField("smsCount");
        }
        else if(!validateGigabytesTraffic(contract)){
            validationResult.setStatus(Status.ERROR);
            validationResult.setText("traffic gigabytes should be more or equal to zero");
            validationResult.setFirstErrorField("gigabytesTraffic");
        }
        return validationResult;
    }

    private boolean validateMinutesCount(MobileConnectionContract mobileConnectionContract){
        return mobileConnectionContract.getMinutesCount()>=0;
    }

    private boolean validateSmsCount(MobileConnectionContract mobileConnectionContract){
        return mobileConnectionContract.getSmsCount()>=0;
    }

    private boolean validateGigabytesTraffic(MobileConnectionContract mobileConnectionContract){
        return mobileConnectionContract.getGigabytesTraffic()>=0;
    }
}
