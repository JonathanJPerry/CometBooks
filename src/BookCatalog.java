import java.util.ArrayList;
import java.util.List;

public class BookCatalog {
    public List<BookListingEntry> BookList = new ArrayList<>();

    public void addBook(BookListingEntry book)  {

    }

    public BookListingEntry fetchBookListing(BookListingEntry book) {
        return BookList.get(0);
    }

    public void remove(BookListingEntry book)   {

    }

    public void PutOnHold(BookListingEntry book)    {

    }

    public void completeTransaction(BookListingEntry book)  {
        if(book.getStatus() == StatusEnum.PENDING)   {
            //the second of the buyer/seller finished the transaction
            book.updateStatus(StatusEnum.SOLD);
        } else if (book.getStatus() == StatusEnum.ONHOLD)   {
            //the first of the buyer/seller finished the transaction
            book.updateStatus(StatusEnum.PENDING);
        }
    }

    public void cancelHold(BookListingEntry book)    {

    }
}
