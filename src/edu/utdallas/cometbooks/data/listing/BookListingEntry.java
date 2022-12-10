package edu.utdallas.cometbooks.data.listing;

import edu.utdallas.cometbooks.backend.listing.ListingStatus;
import edu.utdallas.cometbooks.data.book.BookRecord;

public final class BookListingEntry {
    public static class BookListingEntryBuilder {
        private String sellerNetId;
        private BookRecord book;
        private BookCondition condition;
        private double price;
        private String description;
        private final ListingStatus status = ListingStatus.AVAILABLE;

        public BookListingEntryBuilder sellerNetId(String sellerNetId) {
            this.sellerNetId = sellerNetId;
            return this;
        }

        public BookListingEntryBuilder book(BookRecord book) {
            this.book = book;
            return this;
        }

        public BookListingEntryBuilder condition(BookCondition condition) {
            this.condition = condition;
            return this;
        }

        public BookListingEntryBuilder price(double price) {
            this.price = price;
            return this;
        }

        public BookListingEntryBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BookListingEntry build() {
            return new BookListingEntry(sellerNetId, book, condition, description, status, price);
        }
    }

    public static BookListingEntryBuilder builder() {
        return new BookListingEntryBuilder();
    }

    private final String sellerNetId;
    private final BookRecord bookRecord;
    private final BookCondition condition;
    private final String description;
    private final ListingStatus status;
    private final double price;

    private BookListingEntry(String sellerNetId, BookRecord bookRecord, BookCondition condition, String description, ListingStatus status, double price) {
        this.sellerNetId = sellerNetId;
        this.bookRecord = bookRecord;
        this.condition = condition;
        this.description = description;
        this.status = status;
        this.price = price;
    }

    public String getSellerNetId() {
        return sellerNetId;
    }

    public BookRecord getBookRecord() {
        return bookRecord;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public ListingStatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }
}
