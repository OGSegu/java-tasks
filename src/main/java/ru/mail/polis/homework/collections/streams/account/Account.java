package ru.mail.polis.homework.collections.streams.account;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализуйте класс Account с полями:
 * id
 * список всех транзакций с аккаунта (входящие и исходящие)
 * баланс
 * 1 балл
 */
public class Account {
    private final String id;
    private final List<Transaction> transactions;
    private long balance;

    public Account(String id, List<Transaction> transactionList, long balance) {
        this.id = id;
        this.transactions = transactionList;
        this.balance = balance;
    }

    public List<Transaction> getInTransactions() {
        return transactions.stream()
                .filter(e -> !e.getFromId().equals(this.id))
                .collect(Collectors.toList());
    }

    public List<Transaction> getOutTransactions() {
        return transactions.stream()
                .filter(e -> e.getFromId().equals(this.id))
                .collect(Collectors.toList());
    }

    public long getBalanceInTime(long t) {
        return balance +
                getInTransactions().stream()
                        .filter(e -> e.getDate().getTime() >= t)
                        .map(Transaction::getSum)
                        .reduce(0L, Long::sum) -
                getOutTransactions().stream()
                        .filter(e -> e.getDate().getTime() >= t)
                        .map(Transaction::getSum)
                        .reduce(0L, Long::sum);
    }

    public String getId() {
        return id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

}
