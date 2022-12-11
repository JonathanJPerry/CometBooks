package edu.utdallas.cometbooks.data.transactions;

import edu.utdallas.cometbooks.data.listing.BookListingEntry;

import java.util.Objects;

public final class Transaction {
    public static class TransactionBuilder {
        private String sellerNetId;
        private String buyerNetId;
        private BookListingEntry listing;
        private boolean buyerCompleted;

        public TransactionBuilder sellerNetId(String sellerNetId) {
            this.sellerNetId = sellerNetId;
            return this;
        }

        public TransactionBuilder buyerNetId(String buyerNetId) {
            this.buyerNetId = buyerNetId;
            return this;
        }

        public TransactionBuilder listing(BookListingEntry listing) {
            this.listing = listing;
            return this;
        }

        public TransactionBuilder buyerCompleted(boolean buyerCompleted) {
            this.buyerCompleted = buyerCompleted;
            return this;
        }

        public Transaction build() {
            return new Transaction(sellerNetId, buyerNetId, listing, buyerCompleted);
        }
    }

    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    private final String sellerNetId;
    private final String buyerNetId;
    private final BookListingEntry listing;
    private final boolean buyerCompleted;

    Transaction(String sellerNetId, String buyerNetId, BookListingEntry listing, boolean buyerCompleted) {
        this.sellerNetId = sellerNetId;
        this.buyerNetId = buyerNetId;
        this.listing = listing;
        this.buyerCompleted = buyerCompleted;
    }

    public String getOther(String netId) {
        return netId.equals(buyerNetId) ? sellerNetId : buyerNetId;
    }

    public String getSellerNetId() {
        return sellerNetId;
    }

    public String getBuyerNetId() {
        return buyerNetId;
    }

    public BookListingEntry getListing() {
        return listing;
    }

    public boolean isBuyerCompleted() {
        return buyerCompleted;
    }

    public TransactionBuilder toBuilder() {
        return builder()
                .sellerNetId(sellerNetId)
                .buyerNetId(buyerNetId)
                .listing(listing)
                .buyerCompleted(buyerCompleted);
    }
}
