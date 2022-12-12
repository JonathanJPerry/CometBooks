package edu.utdallas.cometbooks.backend.student;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class StudentDatabase {
    public static StudentDatabase createEmpty() {
        return new StudentDatabase();
    }

    public static StudentDatabase createWith(UTDStudent... students) {
        StudentDatabase database = createEmpty();
        for (UTDStudent student : students) {
            database.addStudent(student);
        }
        return database;
    }

    private final Map<String, UTDStudent> students = new HashMap<>();

    private StudentDatabase() {
    }

    public void addStudent(UTDStudent student) {
        students.put(student.getNetId(), student);
    }

    public Optional<UTDStudent> getStudent(String netId) {
        return Optional.ofNullable(students.get(netId));
    }
}
