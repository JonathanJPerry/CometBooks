package edu.utdallas.cometbooks.backend.listing;

import edu.utdallas.cometbooks.data.listing.BookListingEntry;
import edu.utdallas.cometbooks.data.listing.ListingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookCatalog {
    public static BookCatalog createEmpty() {
        return new BookCatalog();
    }

    public static BookCatalog createWith(BookListingEntry... listings) {
        BookCatalog database = createEmpty();
        for (BookListingEntry listing : listings) {
            database.addListing(listing);
        }
        return database;
    }

    private final List<BookListingEntry> listings = new ArrayList<>();

    private BookCatalog() {
    }

    public void addListing(BookListingEntry listing) {
        listings.add(listing);
    }

    public void replaceListing(BookListingEntry original, BookListingEntry updated) {
        listings.remove(original);
        listings.add(updated);
    }

    public List<BookListingEntry> fetchActiveListingsBy(String netId) {
        return listings.stream()
                .filter(listing -> listing.getSellerNetId().equals(netId))
                .filter(listing -> listing.getStatus() != ListingStatus.SOLD)
                .collect(Collectors.toList());
    }

    public List<BookListingEntry> fetchActiveListingsFor(String isbn) {
        return listings.stream()
                .filter(listing -> listing.getBookRecord().getIsbn().equals(isbn))
                .filter(listing -> listing.getStatus() == ListingStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    public List<BookListingEntry> fetchSoldListingsFor(String isbn) {
        return listings.stream()
                .filter(listing -> listing.getBookRecord().getIsbn().equals(isbn))
                .filter(listing -> listing.getStatus() == ListingStatus.SOLD)
                .collect(Collectors.toList());
    }

    public void updateStatus(BookListingEntry entry, ListingStatus status) {
        listings.remove(entry);

        BookListingEntry updatedEntry = entry.toBuilder()
                .status(status)
                .build();

        listings.add(updatedEntry);
    }

    public void remove(BookListingEntry listing)   {
        listings.remove(listing);
    }
}
