package domain.contract.exception;

public class BankValidationException extends RuntimeException {

    public BankValidationException(String field, String reason) {
        super("Error in the provided input. Field %s %s".formatted(field, reason));
    }
}
