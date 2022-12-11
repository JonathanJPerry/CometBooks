package edu.utdallas.cometbooks.backend.student;

import edu.utdallas.cometbooks.backend.chat.ChatLog;
import edu.utdallas.cometbooks.data.login.LogInResponse;
import edu.utdallas.cometbooks.data.login.LogInResponseType;

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

    public List<String> getBooks(String netId) {
        return studentDatabase.getStudent(netId)
                .map(UTDStudent::getCourseBooks)
                .orElse(null);
    }
}
