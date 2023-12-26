package service;

import model.Course;
import model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student findStudent(String studentId) {
        if (students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;
    }

    public boolean isSubscribed(String studentId) {
        //TODO implement this method
        return students.containsKey(studentId);
    }

    public void showSummary() {
        //TODO implement
        if (!students.isEmpty()) {
            System.out.println("Student List:");
            for (String key : students.keySet()) {
                Student student = students.get(key);
                System.out.println(student);
            }
            System.out.println("\nEnrolled Courses and Grading (if any)");
            for (Student student : students.values()) {
                if (isSubscribed(student.getId())) {
                    System.out.println("Courses enrolled for Student " + student.getName() + " of ID " + student.getId() + ": ");
                    for (var course : student.getApprovedCourses().entrySet()) {
                        System.out.println("\t"+course.getKey().getCode() + ", " + course.getKey().getName() + " of Module " + course.getKey().getModule().getName() + (course.getValue() != 0.0 ? ". Graded with GPA of " + course.getValue() : ". Not yet Graded"));
                    }
                } else {
                    System.out.println("Student " + student.getName() + " of ID " + student.getId() + " have 0 courses enrolled.");
                }
            }
        } else {
            System.out.println("Student list is empty. Please Register Student.");
        }
    }

    public void enrollToCourse(String studentId, Course course) {
        if (students.containsKey(studentId)) {
            students.get(studentId).enrollToCourse(course);
        }
    }

    public Map<String, Student> getStudents() {
        return students;
    }
}