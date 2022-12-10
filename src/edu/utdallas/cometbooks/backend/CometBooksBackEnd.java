package edu.utdallas.cometbooks.backend;

import edu.utdallas.cometbooks.backend.student.StudentDatabase;
import edu.utdallas.cometbooks.backend.student.UTDStudent;

public final class CometBooksBackEnd {
    public static CometBooksBackEnd initialize() {
        return new CometBooksBackEnd();
    }

    private static final StudentDatabase DATABASE = StudentDatabase.createWith(
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

    private CometBooksBackEnd() {
    }

    public Controller createControllerInstance() {
        Controller controller = Controller.createWith(DATABASE);
        return controller;
    }
}
