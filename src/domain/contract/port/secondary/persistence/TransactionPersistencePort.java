package domain.contract.port.secondary.persistence;

import domain.contract.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionPersistencePort {

    /**
     * Saves a transaction
     * @param transaction the transaction to save
     * @return the saved transaction
     */
    Transaction saveTransaction(Transaction transaction);

    /**
     * Find transactions for a given account id
     * @param accountId the account it to find the transactions for
     * @return a list of found transactions
     */
    List<Transaction> findTransactionsByAccountId(String accountId);


    @Deprecated
    /**
     * Given an account it calculates the current balance
     * @param accountId the account id to calculate the current balance
     * @return The current balance for the provided account
     */
    BigDecimal calculateAccountCurrentBalance(String accountId);
}
