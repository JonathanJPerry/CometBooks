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
import edu.utdallas.cometbooks.online_retailer.OnlineRetailerController;

import java.util.List;

public class Controller {
    public static Controller createWith(StudentService service, BookService bookService, BookCatalogService bookCatalogService, List<OnlineRetailerController> onlineRetailers) {
        return new Controller(service, bookService, bookCatalogService, onlineRetailers);
    }

    private final StudentService studentService;
    private final BookService bookService;
    private final BookCatalogService bookCatalogService;
    private final List<OnlineRetailerController> onlineRetailers;

    private Controller(StudentService studentService, BookService bookService, BookCatalogService bookCatalogService, List<OnlineRetailerController> onlineRetailers) {
        this.studentService = studentService;
        this.bookService = bookService;
        this.bookCatalogService = bookCatalogService;
        this.onlineRetailers = onlineRetailers;
    }

    public LogInResponse logIn(String netId, String password) {
        return studentService.logIn(netId, password);
    }

    public List<BookRecord> fetchRelevantBooks(String netId) {
        List<String> isbns = studentService.getBooks(netId);
        return bookService.fetchBookRecords(isbns);
    }

    public List<BookListingEntry> fetchBookListingsBy(String netId) {
        return bookCatalogService.fetchActiveListingsBy(netId);
    }

    public List<BookListingEntry> fetchRelevantListings(String netId) {
        List<String> isbns = studentService.getBooks(netId);
        return bookCatalogService.fetchActiveListingsFor(netId, isbns);
    }

    public double fetchSuggestedPrice(String isbn) {
        return bookCatalogService.fetchSuggestedPrice(isbn, onlineRetailers);
    }

    public void listForSale(BookListingEntry entry) {
        bookCatalogService.addListing(entry);
    }

    public void editListing(BookListingEntry original, BookListingEntry updated) {
        bookCatalogService.editListing(original, updated);
    }

    public void trashListing(BookListingEntry entry) {
        bookCatalogService.removeListing(entry);
    }

    public void buyBook(BookListingEntry entry, String interestedBuyerNetId) {
        bookCatalogService.putOnHold(entry);
        studentService.enableChattingBetween(entry.getSellerNetId(), interestedBuyerNetId);
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
