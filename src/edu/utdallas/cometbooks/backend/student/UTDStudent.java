package edu.utdallas.cometbooks.backend.student;

import java.util.ArrayList;
import java.util.List;

public class UTDStudent {
    public static class UTDStudentBuilder {
        private String netId;
        private String password;
        private String name;
        private List<String> courseBooks = new ArrayList<>();

        public UTDStudentBuilder netId(String netId) {
            this.netId = netId;
            return this;
        }

        public UTDStudentBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UTDStudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UTDStudentBuilder courseBook(String isbn) {
            courseBooks.add(isbn);
            return this;
        }

        public UTDStudent build() {
            return new UTDStudent(netId, password, name, courseBooks);
        }
    }

    public static UTDStudentBuilder builder() {
        return new UTDStudentBuilder();
    }

    private final String netId;
    private final String password;
    private final String name;
    private final List<String> courseBooks; // a string of ISBNs of books

    public UTDStudent(String netId, String password, String name, List<String> courseBooks) {
        this.netId = netId;
        this.password = password;
        this.name = name;
        this.courseBooks = courseBooks;
    }

    public String getNetId() {
        return netId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public List<String> getCourseBooks() {
        return courseBooks;
    }
}
