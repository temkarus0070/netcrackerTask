package temkarus0070.firstTask.validation;

public class ValidationResult {
    private Status status;
    private String text;
    private String firstErrorField;

    public ValidationResult(){
        status=Status.OK;
    }

    public ValidationResult(Status status, String text, String firstErrorField) {
        this.status = status;
        this.text = text;
        this.firstErrorField = firstErrorField;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFirstErrorField() {
        return firstErrorField;
    }

    public void setFirstErrorField(String firstErrorField) {
        this.firstErrorField = firstErrorField;
    }
}
