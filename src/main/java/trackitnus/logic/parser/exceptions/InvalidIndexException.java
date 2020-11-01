package trackitnus.logic.parser.exceptions;

/**
 * Represents a parse error encountered by a parser when parsing an index.
 */
public class InvalidIndexException extends ParseException {
    public InvalidIndexException(String message) {
        super(message);
    }

    public InvalidIndexException(String message, Throwable cause) {
        super(message, cause);
    }
}
