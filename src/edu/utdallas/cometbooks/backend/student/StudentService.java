package edu.utdallas.cometbooks.backend.student;

import edu.utdallas.cometbooks.data.login.LogInResponse;
import edu.utdallas.cometbooks.data.login.LogInResponseType;

import java.util.List;

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

    public List<String> getBooks(String netId) {
        return studentDatabase.getStudent(netId)
                .map(UTDStudent::getCourseBooks)
                .orElse(null);
    }
}
