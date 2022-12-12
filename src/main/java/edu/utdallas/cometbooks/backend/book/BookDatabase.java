package edu.utdallas.cometbooks.backend.book;

import edu.utdallas.cometbooks.data.book.BookRecord;

import java.util.*;

public class BookDatabase {
    public static BookDatabase createEmpty() {
        return new BookDatabase();
    }

    public static BookDatabase createWith(BookRecord... books) {
        BookDatabase database = createEmpty();
        for (BookRecord book : books) {
            database.addBook(book);
        }
        return database;
    }

    private final Map<String, BookRecord> books = new HashMap<>();

    private BookDatabase() {
    }

    public void addBook(BookRecord book) {
        books.put(book.getIsbn(), book);
    }

    public Optional<BookRecord> getBook(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }
}
