package core.mate.exceptions;

public class ArgumentsParseException extends RuntimeException {
    public ArgumentsParseException(String message) {
        super(message);
    }

    public ArgumentsParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
