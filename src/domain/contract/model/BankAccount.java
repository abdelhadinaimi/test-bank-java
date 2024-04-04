package domain.contract.model;

import java.math.BigDecimal;
import java.util.List;

public record BankAccount(String accountId, String name, BigDecimal currentBalance, List<Transaction> transactions){};
