package service;

import model.Student;
import org.junit.jupiter.api.Test;
import java.text.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void subscribeStudent() throws ParseException {
        StudentService studentService = new StudentService();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Student peter = new Student("1", "Peter", "peter@pan.com", dateFormat.parse("03/15/2000"));
        Student john = new Student("2", "John", "john@williams.com", dateFormat.parse("04/05/2000"));
        Student tony = new Student("3", "Tony", "tony@stark.com", dateFormat.parse("05/18/2000"));
        Student harry = new Student("4", "Harry", "harry@potter.com", dateFormat.parse("01/25/2000"));
        assertAll(
                () -> {
                    studentService.subscribeStudent(peter);
                    assertTrue(studentService.getStudents().containsKey(peter.getId()));
                },
                () -> {
                    studentService.subscribeStudent(john);
                    assertTrue(studentService.getStudents().containsKey(john.getId()));
                },
                () -> {
                    studentService.subscribeStudent(tony);
                    assertTrue(studentService.getStudents().containsKey(tony.getId()));
                },
                () -> {
                    studentService.subscribeStudent(harry);
                    assertTrue(studentService.getStudents().containsKey(harry.getId()));
                }
        );
    }

    @Test
    void findStudent() throws ParseException {
        StudentService studentService = new StudentService();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Student peter = new Student("1", "Peter", "peter@pan.com", dateFormat.parse("03/15/2000"));
        Student john = new Student("2", "John", "john@williams.com", dateFormat.parse("04/05/2000"));
        Student tony = new Student("3", "Tony", "tony@stark.com", dateFormat.parse("05/18/2000"));
        Student harry = new Student("4", "Harry", "harry@potter.com", dateFormat.parse("01/25/2000"));
        studentService.subscribeStudent(peter);
        studentService.subscribeStudent(john);
        studentService.subscribeStudent(tony);
        studentService.subscribeStudent(harry);
        assertAll(
                () -> {
                    assertEquals(peter, studentService.findStudent("1"),"Hello wake up");
                },
                () -> {
                    assertEquals(john, studentService.findStudent("2"));
                },
                () -> {
                    assertEquals(tony, studentService.findStudent("3"));
                },
                () -> {
                    assertEquals(harry, studentService.findStudent("4"));
                }
        );
    }
}