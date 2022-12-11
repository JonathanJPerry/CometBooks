package edu.utdallas.cometbooks.backend;

import edu.utdallas.cometbooks.backend.book.BookDatabase;
import edu.utdallas.cometbooks.backend.book.BookService;
import edu.utdallas.cometbooks.backend.listing.BookCatalog;
import edu.utdallas.cometbooks.backend.listing.BookCatalogService;
import edu.utdallas.cometbooks.backend.student.StudentDatabase;
import edu.utdallas.cometbooks.backend.student.StudentService;
import edu.utdallas.cometbooks.backend.student.UTDStudent;
import edu.utdallas.cometbooks.backend.transactions.TransactionDatabase;
import edu.utdallas.cometbooks.backend.transactions.TransactionService;
import edu.utdallas.cometbooks.data.book.BookRecord;
import edu.utdallas.cometbooks.online_retailer.OnlineRetailerController;

import java.util.List;

public final class CometBooksBackEnd {
    public static CometBooksBackEnd initialize() {
        return new CometBooksBackEnd();
    }

    private static final StudentDatabase STUDENTS_DATABASE = StudentDatabase.createWith(
            UTDStudent.builder()
                    .netId("ajs180009")
                    .password("password")
                    .name("Arham J. Siddiqui")
                    .courseBook("978-0-321-87758-1")
                    .courseBook("978-0-321-87759-8")
                    .build(),
            UTDStudent.builder()
                    .netId("jjp000000")
                    .password("password")
                    .name("Jonathan J. Perry")
                    .courseBook("978-0-321-87758-1")
                    .courseBook("978-0-321-87759-8")
                    .build()
    );

    private static final BookDatabase BOOK_DATABASE = BookDatabase.createWith(
            BookRecord.builder()
                    .author("Pascal Roques")
                    .isbn("978-0-321-87758-1")
                    .title("UML in Practice The Art of Modeling Software Systems Demonstrated through Worked Examples and Solutions")
                    .year(2006)
                    .suggestedRetailPrice(100.0)
                    .build(),
            BookRecord.builder()
                    .author("John L. Hennessy & David A. Patterson")
                    .isbn("978-0-321-87759-8")
                    .title("Computer Architecture: A Quantitative Approach, 6th Edition")
                    .year(2019)
                    .suggestedRetailPrice(200.0)
                    .build()
    );

    private static final BookCatalog BOOK_CATALOG = BookCatalog.createEmpty();

    private static final TransactionDatabase TRANSACTION_DATABASE = TransactionDatabase.createEmpty();

    private CometBooksBackEnd() {
    }

    public Controller createControllerInstance() {
        StudentService studentService = StudentService.createWith(STUDENTS_DATABASE);
        BookService bookService = BookService.createWith(BOOK_DATABASE);
        TransactionService transactionService = TransactionService.createWith(TRANSACTION_DATABASE);
        BookCatalogService bookCatalogService = BookCatalogService.createWith(BOOK_CATALOG, transactionService);

        OnlineRetailerController onlineRetailer1 = OnlineRetailerController.withMockPrice(150.0);
        OnlineRetailerController onlineRetailer2 = OnlineRetailerController.withMockPrice(100.0);

        return Controller.createWith(studentService, bookService, bookCatalogService, transactionService, List.of(onlineRetailer1, onlineRetailer2));
    }
}
