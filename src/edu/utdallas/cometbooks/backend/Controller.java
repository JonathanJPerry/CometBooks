package edu.utdallas.cometbooks.backend;

import edu.utdallas.cometbooks.backend.book.BookService;
import edu.utdallas.cometbooks.backend.listing.BookCatalogService;
import edu.utdallas.cometbooks.data.book.BookRecord;
import edu.utdallas.cometbooks.backend.chat.ChatLog;
import edu.utdallas.cometbooks.data.listing.BookListingEntry;
import edu.utdallas.cometbooks.data.listing.BookCondition;
import edu.utdallas.cometbooks.backend.student.StudentService;
import edu.utdallas.cometbooks.backend.student.UTDStudent;
import edu.utdallas.cometbooks.data.login.LogInResponse;

import java.util.List;

public class Controller {
    public static Controller createWith(StudentService service, BookService bookService, BookCatalogService bookCatalogService) {
        return new Controller(service, bookService, bookCatalogService);
    }

    //TODO these aren't present in the diagram.
    // Should they be converted to singletons?
    ChatLog chatLog = new ChatLog();
    private final StudentService studentService;
    private final BookService bookService;
    private final BookCatalogService bookCatalogService;

    private Controller(StudentService studentService, BookService bookService, BookCatalogService bookCatalogService) {
        this.studentService = studentService;
        this.bookService = bookService;
        this.bookCatalogService = bookCatalogService;
    }

    public LogInResponse logIn(String netId, String password) {
        return studentService.logIn(netId, password);
    }

    public List<BookRecord> fetchBooks(String netId) {
        List<String> isbns = studentService.getBooks(netId);
        return bookService.fetchBookRecords(isbns);
    }

    public List<BookListingEntry> fetchBookListings(String netId) {
        return bookCatalogService.fetchBookListings(netId);
    }

    public void listForSale(BookListingEntry entry) {
        bookCatalogService.addListing(entry);
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

    /*public void selectTrashListing(BookListingEntry book)   {
        bookCatalog.completeTransaction(book);
    }*/

    public void sendMessage(UTDStudent recipient, String text)  {

    }

    public void selectCompleteButton(BookListingEntry book) {

    }

    public void selectTransactionsTab() {

    }

    public void selectCancelTransactionButton(BookListingEntry book)  {

    }
}
