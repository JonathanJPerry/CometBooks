package edu.utdallas.cometbooks.backend;

import edu.utdallas.cometbooks.backend.book.BookDatabase;
import edu.utdallas.cometbooks.backend.book.BookRecord;
import edu.utdallas.cometbooks.backend.chat.ChatLog;
import edu.utdallas.cometbooks.backend.listing.BookCatalog;
import edu.utdallas.cometbooks.backend.listing.BookListingEntry;
import edu.utdallas.cometbooks.backend.listing.BookCondition;
import edu.utdallas.cometbooks.data.student.UTDStudent;

public class Controller {
    public static Controller create() {
        return new Controller();
    }

    //TODO these aren't present in the diagram.
    // Should they be converted to singletons?
    BookCatalog bookCatalog = new BookCatalog();
    BookDatabase bookDatabase = new BookDatabase();
    ChatLog chatLog = new ChatLog();

    private Controller() {
    }

    public void selectMyBookForSaleTab() {

    }

    public void selectAddBookIcon() {

    }

    public void selectBook(BookRecord b) {

    }

    public void selectCondition(BookCondition condition) {

    }

    public void enterPrice(double price)    {

    }

    public void enterDescription(String description)    {

    }

    public void selectListForSale(BookListingEntry book)    {

    }

    public void selectUpdateListing(BookListingEntry book)  {

    }

    public void selectTrashListing(BookListingEntry book)   {
        bookCatalog.completeTransaction(book);
    }

    public void sendMessage(UTDStudent recipient, String text)  {

    }

    public void selectCompleteButton(BookListingEntry book) {

    }

    public void selectTransactionsTab() {

    }

    public void selectCancelTransactionButton(BookListingEntry book)  {

    }
}