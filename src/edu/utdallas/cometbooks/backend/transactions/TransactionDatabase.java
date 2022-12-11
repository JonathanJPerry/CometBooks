package edu.utdallas.cometbooks.backend.transactions;

import edu.utdallas.cometbooks.data.transactions.Transaction;

import java.util.*;

public final class TransactionDatabase {
    public static TransactionDatabase createEmpty() {
        return new TransactionDatabase();
    }

    public static TransactionDatabase createWith(Transaction... transactions) {
        TransactionDatabase database = createEmpty();
        for (Transaction transaction : transactions) {
            database.addTransaction(transaction);
        }
        return database;
    }

    private final List<Transaction> transactions = new ArrayList<>();

    private TransactionDatabase() {
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Optional<Transaction> getTransactionBetween(String sellerNetId, String buyerNetId, String isbn) {
        return transactions.stream()
                .filter(transaction -> transaction.getSellerNetId().equals(sellerNetId))
                .filter(transaction -> transaction.getBuyerNetId().equals(buyerNetId))
                .filter(transaction -> transaction.getListing().getBookRecord().getIsbn().equals(isbn))
                .findFirst();
    }
}
