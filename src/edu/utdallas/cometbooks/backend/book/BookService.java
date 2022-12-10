package edu.utdallas.cometbooks.backend.book;

import edu.utdallas.cometbooks.data.book.BookRecord;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class BookService {
    public static BookService createWith(BookDatabase database) {
        return new BookService(database);
    }

    private final BookDatabase bookDatabase;

    private BookService(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    public List<BookRecord> fetchBookRecords(List<String> bookIsbns) {
        return bookIsbns.stream()
                .map(bookDatabase::getBook)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
