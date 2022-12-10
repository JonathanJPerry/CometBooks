package edu.utdallas.cometbooks.backend;

import edu.utdallas.cometbooks.backend.book.BookDatabase;
import edu.utdallas.cometbooks.backend.book.BookService;
import edu.utdallas.cometbooks.backend.listing.BookCatalog;
import edu.utdallas.cometbooks.backend.listing.BookCatalogService;
import edu.utdallas.cometbooks.backend.student.StudentDatabase;
import edu.utdallas.cometbooks.backend.student.StudentService;
import edu.utdallas.cometbooks.backend.student.UTDStudent;

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

    private static final BookDatabase BOOK_DATABASE = BookDatabase.createEmpty();

    private static final BookCatalog BOOK_CATALOG = BookCatalog.createEmpty();

    private CometBooksBackEnd() {
    }

    public Controller createControllerInstance() {
        StudentService studentService = StudentService.createWith(STUDENTS_DATABASE);
        BookService bookService = BookService.createWith(BOOK_DATABASE);
        BookCatalogService bookCatalogService = BookCatalogService.createWith(BOOK_CATALOG);

        return Controller.createWith(studentService, bookService, bookCatalogService);
    }
}
