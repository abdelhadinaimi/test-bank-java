package domain.contract.port.secondary.persistence;

import domain.contract.model.BankAccount;

import java.util.Optional;

public interface BankAccountPersistencePort {

    /**
     * Get a bank account given its account id
     * @param accountId the account id of the account to find
     * @return an optional containing the found bank account, empty if not found
     */
    Optional<BankAccount> getBankAccount(String accountId);
}
