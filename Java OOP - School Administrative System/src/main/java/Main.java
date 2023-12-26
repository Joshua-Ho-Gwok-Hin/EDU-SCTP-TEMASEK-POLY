
import model.Course;
import model.Student;
import service.CourseService;
import service.StudentService;
import utils.PrinterHelper;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
            throws ParseException {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    registerStudent(studentService, scanner);
                    break;
                case 2:
                    findStudent(studentService, scanner);
                    break;
                case 3:
                    gradeStudent(studentService, scanner);
                    break;
                case 4:
                    enrollStudentToCourse(studentService, courseService, scanner);
                    break;
                case 5:
                    showStudentsSummary(studentService, scanner);
                    break;
                case 6:
                    showCoursesSummary(courseService, scanner);
                    break;
            }
        }
        while (option != 7);
    }

    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService,
                                              Scanner scanner) {
        System.out.println("Insert student ID");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return;
        }
        System.out.println(student);
        System.out.println("Insert course ID");
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Invalid Course ID");
            return;
        }
        System.out.println(course);
        courseService.enrollStudent(courseId, student); // add student to CourseService HashMap enrolledStudents
        studentService.enrollToCourse(studentId, course); // add course to Student HashMap or List?
        System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

    }

    private static void showCoursesSummary(CourseService courseService, Scanner scanner) {
        courseService.showSummary();
    }

    private static void showStudentsSummary(StudentService studentService, Scanner scanner) {
        studentService.showSummary();
    }

    private static void gradeStudent(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student != null) {
            System.out.println("Student Found: ");
            System.out.println(student);
            System.out.println(student.getName() + " has " + student.getApprovedCourses().size() + " course(s) to grade.");
            for (Course each : student.getApprovedCourses().keySet()) {
//                to implement grade every course.
                boolean canProceed = false;
                while (!canProceed) {
                    System.out.print(each.getCode() + " " + each.getName() + (student.getEachCourseGrading().get(each) == 0.0 ? " not yet graded." : " graded with GPA " + student.getEachCourseGrading().get(each) + "."));
                    System.out.println("\nKey in a double to update grade for " + each.getCode() + " " + each.getName() + " or 'q' to skip to the next course:");
                    if (scanner.hasNextDouble()) {
                        double grade = scanner.nextDouble();
                        student.getEachCourseGrading().put(each, grade);
                        System.out.println(each.getName() + " is graded with GPA of " + grade + ".");
                        canProceed = true;
                    } else {
                        if (scanner.next().substring(0, 1).equalsIgnoreCase("q")) {
                            canProceed = true;
                            System.out.println("Quit grading Course " + each.getName() + ".");
                        } else {
                            System.out.println("Please enter a double to grade or 'q' to skip to next course.");
                        }
                    }
                }
            }
            student.setAverage();
        } else {
            System.out.println("No matching of Student Id.");
        }
    }

    private static void findStudent(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student != null) {
            System.out.println("Student Found: ");
            System.out.println(student);
        } else {
            System.out.println("Student with Id = " + studentId + " not found");
        }
    }

    private static void registerStudent(StudentService studentService, Scanner scanner) throws ParseException {
        Student student = PrinterHelper.createStudentMenu(scanner);
        studentService.subscribeStudent(student);
    }
}