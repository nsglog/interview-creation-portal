package exceptions;

public class UnknownErrorException extends Exception {
    public UnknownErrorException (String message) {
        super(message);
    }
}
