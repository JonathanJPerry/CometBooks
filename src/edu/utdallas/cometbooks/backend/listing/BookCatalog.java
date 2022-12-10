package edu.utdallas.cometbooks.backend.listing;

import edu.utdallas.cometbooks.data.listing.BookListingEntry;

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

    private final List<BookListingEntry> books = new ArrayList<>();

    private BookCatalog() {
    }

    public void addListing(BookListingEntry listing) {
        books.add(listing);
    }

    public List<BookListingEntry> fetchListingsFor(String netId) {
        return books.stream()
                .filter(listing -> listing.getSellerNetId().equals(netId))
                .collect(Collectors.toList());
    }

    public void remove(BookListingEntry book)   {

    }

    public void PutOnHold(BookListingEntry book)    {

    }

    /*public void completeTransaction(BookListingEntry book)  {
        if(book.getStatus() == ListingStatus.PENDING)   {
            //the second of the buyer/seller finished the transaction
            book.updateStatus(ListingStatus.SOLD);
        } else if (book.getStatus() == ListingStatus.ONHOLD)   {
            //the first of the buyer/seller finished the transaction
            book.updateStatus(ListingStatus.PENDING);
        }
    }*/

    public void cancelHold(BookListingEntry book)    {

    }
}
