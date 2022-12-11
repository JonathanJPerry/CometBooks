package edu.utdallas.cometbooks.backend.student;

import edu.utdallas.cometbooks.backend.chat.ChatLog;
import edu.utdallas.cometbooks.data.chat.Message;
import edu.utdallas.cometbooks.data.login.LogInResponse;
import edu.utdallas.cometbooks.data.login.LogInResponseType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<String> getBooks(String netId) {
        return studentDatabase.getStudent(netId)
                .map(UTDStudent::getCourseBooks)
                .orElse(null);
    }
}
