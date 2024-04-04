package domain.core.action;

import domain.contract.Engine;
import domain.contract.action.CreateTransactionAction;
import domain.contract.action.UseCase;
import domain.contract.exception.BankAccountNotFoundException;
import domain.contract.exception.BankValidationException;
import domain.contract.exception.InsufficientFundsException;
import domain.contract.model.BankAccount;
import domain.contract.model.Transaction;
import domain.contract.model.TransactionType;
import domain.contract.port.secondary.persistence.BankAccountPersistencePort;
import domain.contract.port.secondary.persistence.TransactionPersistencePort;
import domain.contract.query.GetBankAccountBalanceQuery;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class CreateTransactionUseCase implements UseCase<CreateTransactionAction, Transaction> {

    private static final String IS_NULL_OR_EMPTY = "is null or empty";

    private final BankAccountPersistencePort bankAccountPersistencePort;
    private final TransactionPersistencePort transactionPersistencePort;
    private final Engine engine;

    public CreateTransactionUseCase(BankAccountPersistencePort bankAccountPersistencePort,
                                    TransactionPersistencePort transactionPersistencePort,
                                    Engine engine) {
        this.bankAccountPersistencePort = bankAccountPersistencePort;
        this.transactionPersistencePort = transactionPersistencePort;
        this.engine = engine;
    }

    @Override
    public Transaction execute(CreateTransactionAction action) {
        validateAction(action);

        BankAccount bankAccount = bankAccountPersistencePort.getBankAccount(action.accountId())
                .orElseThrow(() -> new BankAccountNotFoundException(action.accountId()));

        if (action.type() == TransactionType.WITHDRAW) {
            checkAccountBalance(action.accountId(), action.amount());
        }

        Transaction transactionToSave = new Transaction(bankAccount.accountId(), action.amount(), LocalDateTime.now(), action.type());

        return transactionPersistencePort.saveTransaction(transactionToSave);
    }


    private static void validateAction(CreateTransactionAction action) {
        if (Objects.isNull(action.accountId()) || action.accountId().isBlank()) {
            throw new BankValidationException("accountId", "is null or blank");
        }
        if (Objects.isNull(action.amount())) {
            throw new BankValidationException("amount", IS_NULL_OR_EMPTY);
        }
        if (action.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BankValidationException("amount", "is 0 or less");
        }
    }

    private void checkAccountBalance(String accountId, BigDecimal amount) {
        BigDecimal currentBalance = engine.execute(new GetBankAccountBalanceQuery(accountId));
        if (currentBalance.compareTo(amount) <= 0) {
            throw new InsufficientFundsException(accountId, currentBalance);
        }
    }
}
