import java.awt.print.Book;

public class Controller {
    //TODO these aren't present in the diagram.
    // Should they be converted to singletons?
    BookCatalog bookCatalog = new BookCatalog();
    BookDatabase bookDatabase = new BookDatabase();
    ChatLog chatLog = new ChatLog();

    public void selectMyBookForSaleTab() {

    }

    public void selectAddBookIcon() {

    }

    public void selectBook(BookRecord b) {

    }

    public void selectCondition(ConditionEnum condition) {

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
