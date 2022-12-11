package edu.utdallas.cometbooks.backend.student;

import edu.utdallas.cometbooks.backend.chat.ChatLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final Map<String, ChatLog> chatLogs = new HashMap<>();

    public UTDStudent(String netId, String password, String name, List<String> courseBooks) {
        this.netId = netId;
        this.password = password;
        this.name = name;
        this.courseBooks = courseBooks;
    }

    public void enableChattingWith(String buyerNetId, ChatLog chatLog) {
        chatLogs.put(buyerNetId, chatLog);
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

    public Map<String, ChatLog> getChatLogs() {
        return chatLogs;
    }
}
