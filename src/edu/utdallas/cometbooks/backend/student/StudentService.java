package edu.utdallas.cometbooks.backend.student;

import edu.utdallas.cometbooks.data.LogInResponse;

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
                .map(student -> LogInResponse.SUCCESS)
                .orElse(LogInResponse.INVALID_CREDENTIALS);
    }
}
