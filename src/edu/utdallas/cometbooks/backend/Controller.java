package edu.utdallas.cometbooks.backend;

import edu.utdallas.cometbooks.backend.book.BookDatabase;
import edu.utdallas.cometbooks.backend.book.BookRecord;
import edu.utdallas.cometbooks.backend.chat.ChatLog;
import edu.utdallas.cometbooks.backend.listing.BookCatalog;
import edu.utdallas.cometbooks.backend.listing.BookListingEntry;
import edu.utdallas.cometbooks.backend.listing.BookCondition;
import edu.utdallas.cometbooks.backend.student.StudentDatabase;
import edu.utdallas.cometbooks.backend.student.StudentService;
import edu.utdallas.cometbooks.backend.student.UTDStudent;
import edu.utdallas.cometbooks.data.LogInResponse;

public class Controller {
    public static Controller createWith(StudentService service) {
        return new Controller(service);
    }

    //TODO these aren't present in the diagram.
    // Should they be converted to singletons?
    BookCatalog bookCatalog = new BookCatalog();
    BookDatabase bookDatabase = new BookDatabase();
    ChatLog chatLog = new ChatLog();
    private final StudentService service;

    private Controller(StudentService service) {
        this.service = service;
    }

    public LogInResponse logIn(String netId, String password) {
        return service.logIn(netId, password);
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
