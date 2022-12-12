package edu.utdallas.cometbooks.backend.transactions;

import edu.utdallas.cometbooks.data.transactions.Transaction;
import edu.utdallas.cometbooks.data.transactions.TransactionCompletionResponse;

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

    public TransactionCompletionResponse completeTransaction(String netId, Transaction transaction) {
        Transaction transactionInDatabase = transactionDatabase.getTransactionBetween(transaction.getSellerNetId(), transaction.getBuyerNetId(), transaction.getListing().getBookRecord().getIsbn())
                .orElseThrow(() -> new IllegalArgumentException("Transaction does not exist in database"));

        if (transactionInDatabase.getBuyerNetId().equals(netId)) {
            transactionDatabase.markBuyerCompleted(transactionInDatabase);
            return TransactionCompletionResponse.SUCCESS;
        }

        if (!transaction.isBuyerCompleted()) {
            return TransactionCompletionResponse.BUYER_NOT_COMPLETED;
        }

        transactionDatabase.remove(transaction);
        transactionDatabase.removeAllRelated(transaction);
        return TransactionCompletionResponse.SUCCESS;
    }

    public void cancelTransaction(Transaction transaction) {
        Transaction transactionInDatabase = transactionDatabase.getTransactionBetween(transaction.getSellerNetId(), transaction.getBuyerNetId(), transaction.getListing().getBookRecord().getIsbn())
                .orElseThrow(() -> new IllegalArgumentException("Transaction does not exist in database"));

        transactionDatabase.remove(transactionInDatabase);
    }
}
