package edu.utdallas.cometbooks.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookDatabase {
    HashMap<String,BookRecord> bookRecords = new HashMap<>();

    public List<BookRecord> fetchRelevantBooks(List<String> courseBookTitles)    {
        List<BookRecord> bookRecordList = new ArrayList<>();
        for(String book : courseBookTitles)   {
            bookRecordList.add(bookRecords.get(book));
        }
        return bookRecordList;
    }

    public BookRecord fetchBookRecord(String bookTitle) {
        return bookRecords.get(bookTitle);
    }
}
