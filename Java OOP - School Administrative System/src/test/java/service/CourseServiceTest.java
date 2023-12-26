package service;

import model.Course;
import model.Module;
import model.Student;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    CourseService courseService = new CourseService();
    Course science = new Course("INTRO-CS-01", "Intro to Computer Science", 12, new Module("NEW", "New Module", "Really new module"));
    Course math = new Course("INTRO-CS-02", "Intro to Mathematics", 13, new Module("NEW", "New Module", "Really new module"));
    Course java = new Course("INTRO-CS-03", "Intro to Java", 14, new Module("NEW", "New Module", "Really new module"));
    Course database = new Course("INTRO-CS-04", "Intro to Database Designs", 15, new Module("NEW", "New Module", "Really new module"));


//    Test if getCourse is working with self-defined courses.
    @Test
    void getCourse() {
        assertAll(
                () -> {
                    courseService.registerCourse(science);
                    assertEquals(science, courseService.getCourse("INTRO-CS-01"));
                },
                () -> {
                    courseService.registerCourse(math);
                    assertEquals(math, courseService.getCourse("INTRO-CS-02"));
                },
                () -> {
                    courseService.registerCourse(java);
                    assertEquals(java, courseService.getCourse("INTRO-CS-03"));
                },
                () -> {
                    courseService.registerCourse(database);
                    assertEquals(database, courseService.getCourse("INTRO-CS-04"));
                }
        );
    }

    @Test
    void getEnrolledStudents() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Student peter = new Student("1", "Peter", "peter@pan.com", dateFormat.parse("01/15/2000"));
        Student john = new Student("2", "John", "john@williams.com", dateFormat.parse("01/15/2000"));
        Student tony = new Student("3", "Tony", "tony@stark.com", dateFormat.parse("01/15/2000"));
        Student harry = new Student("4", "Harry", "harry@potter.com", dateFormat.parse("01/15/2000"));

        courseService.enrollStudent("INTRO-CS-03", peter);
        courseService.enrollStudent("INTRO-CS-03", john);
        courseService.enrollStudent("INTRO-CS-03", tony);
        courseService.enrollStudent("INTRO-CS-03", harry);

//        Manually adding students into an arraylist.
        List<Student> studentArrayList = new ArrayList<>();
        studentArrayList.add(peter);
        studentArrayList.add(john);
        studentArrayList.add(tony);
        studentArrayList.add(harry);

//        testing getEnrolledStudents() method returns the same arraylist given the same students.
        assertEquals(studentArrayList,courseService.getEnrolledStudents("INTRO-CS-03"));
    }
}