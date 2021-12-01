package temkarus0070.firstTask.exceptions;

public class CsvReadException extends Exception{
    public CsvReadException(Throwable exception){
        super(exception);
    }

    public CsvReadException(String message){
        super(message);
    }
}
