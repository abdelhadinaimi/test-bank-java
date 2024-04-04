package domain.core.query;

import domain.contract.action.UseCase;
import domain.contract.exception.BankValidationException;
import domain.contract.model.Transaction;
import domain.contract.port.secondary.persistence.TransactionPersistencePort;
import domain.contract.query.GetBankAccountHistoryQuery;

import java.util.List;
import java.util.Objects;

public class GetBankAccountHistoryUseCase implements UseCase<GetBankAccountHistoryQuery, List<Transaction>> {

    private final TransactionPersistencePort transactionPersistencePort;

    public GetBankAccountHistoryUseCase(TransactionPersistencePort transactionPersistencePort){
        this.transactionPersistencePort = transactionPersistencePort;
    }
    @Override
    public List<Transaction> execute(GetBankAccountHistoryQuery query) {
        validateQuery(query);
        return transactionPersistencePort.findTransactionsByAccountId(query.accountId());
    }

    private static void validateQuery(GetBankAccountHistoryQuery query) {
        if (Objects.isNull(query.accountId()) || query.accountId().isBlank()) {
            throw new BankValidationException("accountId", "is null or blank");
        }
    }
}
