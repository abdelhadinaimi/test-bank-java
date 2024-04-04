package domain.contract.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String bankAccountId, BigDecimal currentBalance) {
        super("Bank account %s has inefficient funds (%s)".formatted(bankAccountId, currentBalance.toPlainString()));
    }
}
