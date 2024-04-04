package domain.contract.exception;

public class BankAccountNotFoundException extends RuntimeException {

    public BankAccountNotFoundException(String accountId) {
        super("Bank account %s not found".formatted(accountId));
    }
}
