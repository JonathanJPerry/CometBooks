package edu.utdallas.cometbooks.backend.transactions;

import edu.utdallas.cometbooks.data.transactions.Transaction;

public final class TransactionService {
    public static TransactionService createWith(TransactionDatabase database) {
        return new TransactionService(database);
    }

    private final TransactionDatabase transactionDatabase;

    private TransactionService(TransactionDatabase transactionDatabase) {
        this.transactionDatabase = transactionDatabase;
    }

    public void addTransaction(Transaction transaction) {
        transactionDatabase.addTransaction(transaction);
    }
}
