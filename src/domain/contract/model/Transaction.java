package domain.contract.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public final class Transaction {
    private final String transactionId;
    private final String accountId;
    private final BigDecimal amount;
    private final LocalDateTime date;
    private final TransactionType transactionType;

    public Transaction(String transactionId, String accountId, BigDecimal amount, LocalDateTime date, TransactionType transactionType) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
    }

    public Transaction( String accountId, BigDecimal amount, LocalDateTime date, TransactionType transactionType) {
        this.transactionId = null;
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

}
