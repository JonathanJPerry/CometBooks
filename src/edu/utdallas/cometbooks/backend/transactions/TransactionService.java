package edu.utdallas.cometbooks.backend.transactions;

import edu.utdallas.cometbooks.data.transactions.Transaction;

import java.util.List;

public final class TransactionService {
    public static TransactionService createWith(TransactionDatabase database) {
        return new TransactionService(database);
    }

    private final TransactionDatabase transactionDatabase;

    private TransactionService(TransactionDatabase transactionDatabase) {
        this.transactionDatabase = transactionDatabase;
    }

    public List<Transaction> fetchActiveTransactionsInvolving(String netId) {
        return transactionDatabase.getTransactionsInvolving(netId);
    }

    public void addTransaction(Transaction transaction) {
        transactionDatabase.addTransaction(transaction);
    }
}
