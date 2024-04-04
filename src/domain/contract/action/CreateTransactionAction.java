package domain.contract.action;

import domain.contract.model.TransactionType;

import java.math.BigDecimal;

public record CreateTransactionAction(String accountId, BigDecimal amount, TransactionType type) {
}
