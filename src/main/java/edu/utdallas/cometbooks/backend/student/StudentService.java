package edu.utdallas.cometbooks.backend.student;

import edu.utdallas.cometbooks.backend.chat.ChatLog;
import edu.utdallas.cometbooks.data.chat.Message;
import edu.utdallas.cometbooks.data.chat.Notification;
import edu.utdallas.cometbooks.data.login.LogInResponse;
import edu.utdallas.cometbooks.data.login.LogInResponseType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class StudentService {
    public static StudentService createWith(StudentDatabase database) {
        return new StudentService(database);
    }

    private final StudentDatabase studentDatabase;

    private StudentService(StudentDatabase studentDatabase) {
        this.studentDatabase = studentDatabase;
    }

    public LogInResponse logIn(String netId, String password) {
        return studentDatabase.getStudent(netId)
                .filter(student -> student.getPassword().equals(password))
                .map(student -> LogInResponse.create(student.getNetId(), student.getName(), LogInResponseType.SUCCESS))
                .orElse(LogInResponse.create(null, null, LogInResponseType.INVALID_CREDENTIALS));
    }

    public void enableChattingBetween(String sellerNetId, String buyerNetId) {
        Optional<UTDStudent> sellerOptional = studentDatabase.getStudent(sellerNetId);
        Optional<UTDStudent> buyerOptional = studentDatabase.getStudent(buyerNetId);

        if (sellerOptional.isEmpty()) {
            throw new IllegalArgumentException("Seller does not exist");
        }

        if (buyerOptional.isEmpty()) {
            throw new IllegalArgumentException("Buyer does not exist");
        }

        UTDStudent seller = sellerOptional.get();
        UTDStudent buyer = buyerOptional.get();

        ChatLog chatLog = ChatLog.between(seller, buyer);

        seller.enableChattingWith(buyerNetId, chatLog);
        buyer.enableChattingWith(sellerNetId, chatLog);
    }

    public List<String> fetchActiveChatLogs(String netId) {
        Optional<UTDStudent> studentOptional = studentDatabase.getStudent(netId);

        if (studentOptional.isEmpty()) {
            throw new IllegalArgumentException("Student does not exist");
        }

        return new ArrayList<>(studentOptional.get().getChatLogs().keySet());
    }

    public List<Message> fetchMessagesBetween(String netId1, String netId2) {
        Optional<UTDStudent> student1Optional = studentDatabase.getStudent(netId1);
        Optional<UTDStudent> student2Optional = studentDatabase.getStudent(netId2);

        if (student1Optional.isEmpty()) {
            throw new IllegalArgumentException("Student 1 does not exist");
        }

        if (student2Optional.isEmpty()) {
            throw new IllegalArgumentException("Student 2 does not exist");
        }

        UTDStudent student1 = student1Optional.get();
        UTDStudent student2 = student2Optional.get();

        if (!student1.getChatLogs().containsKey(netId2)) {
            throw new IllegalArgumentException(netId1 + " is not chatting with " + netId2);
        }

        if (!student2.getChatLogs().containsKey(netId1)) {
            throw new IllegalArgumentException(netId1 + " is not chatting with " + netId2);
        }

        return student1.getChatLogs().get(netId2).getMessages();
    }

    public void sendMessage(String from, String to, String text) {
        Optional<UTDStudent> fromOptional = studentDatabase.getStudent(from);
        Optional<UTDStudent> toOptional = studentDatabase.getStudent(to);

        if (fromOptional.isEmpty()) {
            throw new IllegalArgumentException(from + " is not a student");
        }

        if (toOptional.isEmpty()) {
            throw new IllegalArgumentException(to + " is not a student");
        }

        UTDStudent fromStudent = fromOptional.get();

        if (!fromStudent.getChatLogs().containsKey(to)) {
            throw new IllegalArgumentException(from + " is not chatting with " + to);
        }

        if (!toOptional.get().getChatLogs().containsKey(from)) {
            throw new IllegalArgumentException(to + " is not chatting with " + from);
        }

        Message message = Message.builder()
                .fromNetId(from)
                .text(text)
                .date(LocalDateTime.now())
                .build();

        fromStudent.getChatLogs().get(to).add(message);
    }

    public Message fetchLatestMessageBetween(String netId1, String netId2) {
        Optional<UTDStudent> student1Optional = studentDatabase.getStudent(netId1);
        Optional<UTDStudent> student2Optional = studentDatabase.getStudent(netId2);

        if (student1Optional.isEmpty()) {
            throw new IllegalArgumentException("Student 1 does not exist");
        }

        if (student2Optional.isEmpty()) {
            throw new IllegalArgumentException("Student 2 does not exist");
        }

        UTDStudent student1 = student1Optional.get();
        UTDStudent student2 = student2Optional.get();

        if (!student1.getChatLogs().containsKey(netId2)) {
            throw new IllegalArgumentException(netId1 + " is not chatting with " + netId2);
        }

        if (!student2.getChatLogs().containsKey(netId1)) {
            throw new IllegalArgumentException(netId1 + " is not chatting with " + netId2);
        }

        List<Message> messages = student1.getChatLogs().get(netId2).getMessages();
        return messages.get(messages.size() - 1);
    }

    public List<String> getBooks(String netId) {
        return studentDatabase.getStudent(netId)
                .map(UTDStudent::getCourseBooks)
                .orElse(null);
    }

    public void sendNotification(String netId, Notification notification) {
        studentDatabase.getStudent(netId).ifPresent(student -> student.addNotification(notification));
    }

    public void clearNotifications(String netId) {
        studentDatabase.getStudent(netId).ifPresent(UTDStudent::clearNotifications);
    }

    public List<Notification> getNotifications(String netId) {
        return studentDatabase.getStudent(netId)
                .map(UTDStudent::getNotifications)
                .orElse(null);
    }
}
