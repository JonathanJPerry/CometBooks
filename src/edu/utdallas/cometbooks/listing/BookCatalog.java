package edu.utdallas.cometbooks.listing;

import java.util.ArrayList;
import java.util.List;

public class BookCatalog {
    public List<BookListingEntry> bookList = new ArrayList<>();

    public void addBook(BookListingEntry book)  {

    }

    public BookListingEntry fetchBookListing(BookListingEntry book) {
        return bookList.get(0);
    }

    public void remove(BookListingEntry book)   {

    }

    public void PutOnHold(BookListingEntry book)    {

    }

    public void completeTransaction(BookListingEntry book)  {
        if(book.getStatus() == ListingStatus.PENDING)   {
            //the second of the buyer/seller finished the transaction
            book.updateStatus(ListingStatus.SOLD);
        } else if (book.getStatus() == ListingStatus.ONHOLD)   {
            //the first of the buyer/seller finished the transaction
            book.updateStatus(ListingStatus.PENDING);
        }
    }

    public void cancelHold(BookListingEntry book)    {

    }
}
