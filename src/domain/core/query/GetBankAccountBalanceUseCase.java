package domain.core.query;

import domain.contract.action.UseCase;
import domain.contract.exception.BankValidationException;
import domain.contract.model.Transaction;
import domain.contract.model.TransactionType;
import domain.contract.port.secondary.persistence.TransactionPersistencePort;
import domain.contract.query.GetBankAccountBalanceQuery;

import java.math.BigDecimal;
import java.util.Objects;

public class GetBankAccountBalanceUseCase implements UseCase<GetBankAccountBalanceQuery, BigDecimal> {

    private final TransactionPersistencePort transactionPersistencePort;

    public GetBankAccountBalanceUseCase(TransactionPersistencePort transactionPersistencePort){
        this.transactionPersistencePort = transactionPersistencePort;
    }

    @Override
    public BigDecimal execute(GetBankAccountBalanceQuery query) {
        validateQuery(query);
        return transactionPersistencePort.findTransactionsByAccountId(query.accountId())
                .stream()
                .map(GetBankAccountBalanceUseCase::getSignedAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static void validateQuery(GetBankAccountBalanceQuery query) {
        if (Objects.isNull(query.accountId()) || query.accountId().isBlank()) {
            throw new BankValidationException("accountId", "is null or blank");
        }
    }

    private static BigDecimal getSignedAmount(Transaction transaction) {
        return transaction.getTransactionType() == TransactionType.DEPOSIT ?
                transaction.getAmount() :
                transaction.getAmount().negate();
    }
}
