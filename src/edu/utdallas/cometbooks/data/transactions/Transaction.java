package edu.utdallas.cometbooks.data.transactions;

import edu.utdallas.cometbooks.data.listing.BookListingEntry;

public final class Transaction {
    public static Transaction createFor(String sellerNetId, String buyerNetId, BookListingEntry listing) {
        return new Transaction(sellerNetId, buyerNetId, listing);
    }

    private final String sellerNetId;
    private final String buyerNetId;
    private final BookListingEntry listing;

    private Transaction(String sellerNetId, String buyerNetId, BookListingEntry listing) {
        this.sellerNetId = sellerNetId;
        this.buyerNetId = buyerNetId;
        this.listing = listing;
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
}
