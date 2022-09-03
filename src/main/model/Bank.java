package main.model;

import main.model.accounts.*;
import main.model.accounts.impl.Taxable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Bank {
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public Bank() {
        accounts = new ArrayList<Account>();
        transactions = new ArrayList<Transaction>();
    }

    public void addAccount(Account account) {
        accounts.add(account.clone());
    }

    private void addTransaction(Transaction transaction) {
        transactions.add( new Transaction(transaction));
    }

    public Transaction[] getTransactions(String accountId) {
        ArrayList<Transaction> accountTransactions = new ArrayList<Transaction>();

        accountTransactions.addAll(transactions.stream()
                .filter((transactions) -> (transactions.getId().equals(accountId)))
                .collect(Collectors.toList()));

        return accountTransactions.toArray(new Transaction[accountTransactions.size()]);
    }

    public Account getAccount(String transactionId) {
        return accounts.stream()
                .filter((account) -> account.getId().equals(transactionId))
                .findFirst()
                .orElse(null);
    }

    private void withdrawTransaction(Transaction transaction) {
        if(getAccount(transaction.getId()).withdraw(transaction.getAmount())) {
            addTransaction(transaction);
        }

    }

    private void depositTransaction(Transaction transaction) {
        getAccount(transaction.getId()).deposit(transaction.getAmount());
        addTransaction(transaction);
    }

    public void executeTransaction(Transaction transaction) {
        switch (transaction.getType()) {
            case WITHDRAW: withdrawTransaction(transaction); break;
            case DEPOSIT: depositTransaction(transaction); break;
        }
    }

    private double getIncome(Taxable account) {
        Transaction[] transactions = getTransactions(((Checking)account).getId());
        return Arrays.stream(transactions)
                .mapToDouble((transaction) ->  {
                    switch (transaction.getType()) {
                        case WITHDRAW: return -transaction.getAmount();
                        case DEPOSIT: return transaction.getAmount();
                        default: return 0;
                    }
                }).sum();
    }

    public void deductTaxes() {
        for (Account account: accounts) {
            if(Taxable.class.isAssignableFrom(account.getClass())) {
                Taxable taxable = (Taxable)account;
                taxable.tax(getIncome(taxable));
            }
        }
    }
}
