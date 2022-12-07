import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class BookRecord {
    public String title;
    public String isbn;
    public int year;
    public String author;
    public double suggested_retail_price;
}

public class BookDatabase {
    HashMap<String,BookRecord> BookRecords = new HashMap<>();

    public List<BookRecord> fetchRelevantBooks(List<String> courseBookTitles)    {
        List<BookRecord> bookRecordList = new ArrayList<>();
        for(String book : courseBookTitles)   {
            bookRecordList.add(BookRecords.get(book));
        }
        return bookRecordList;
    }

    public BookRecord fetchBookRecord(String bookTitle) {
        return BookRecords.get(bookTitle);
    }
}
