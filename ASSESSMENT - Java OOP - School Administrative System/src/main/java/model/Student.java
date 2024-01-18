package model;

import java.util.*;

public class Student extends Person implements Evaluation {
    private double average;
    private final HashMap<Course, Double> eachCourseGrading = new HashMap<>();
    private final Map<String, Course> approvedCourses = new HashMap<>();

    public Student(String id, String name, String email, Date birthDate) {
        super(id, name, email, birthDate);
    }

    public void enrollToCourse(Course course) {
        //TODO implement this method
        registerApprovedCourse(course);
        eachCourseGrading.put(course, 0.0);
    }

    public void registerApprovedCourse(Course course) {
        approvedCourses.put(course.getCode(), course);
    }

    public boolean isCourseApproved(String courseCode) {
        //TODO implement this method
        return approvedCourses.containsKey(courseCode);
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve.
    public List<Course> findPassedCourses(Course course) {
        //TODO implement this method
        return null;
    }

    public boolean isAttendingCourse(String courseCode) {
        //TODO implement this method
        return approvedCourses.containsKey(courseCode);
    }

    public HashMap<Course, Double> getEachCourseGrading() {
        return eachCourseGrading;
    }

    @Override
    public double getAverage() {
        return average;
    }

    public void setAverage(){
        double total = 0.0;
        int count = 0;
        for (double grading: eachCourseGrading.values()){
            if (grading != 0.0) {
                count++;
                total += grading;
            }
        }
        if (count != 0) {
            double d = total/count;
            average = (double) Math.round(d * 10)/10;
        }
    }

    public List<Course> findPassedCourses(){
        List<Course> passedCourses = new ArrayList<>();
        for (var entries : eachCourseGrading.entrySet()) {
            if (entries.getValue() != 0.0 && entries.getValue() >= 2.5) {
                passedCourses.add(entries.getKey());
            }
        }
        return passedCourses;
    }

    @Override
    public HashMap<Course,Double> getApprovedCourses() {
        //TODO implement this method
        return eachCourseGrading;
    }

    @Override
    public String toString() {
        return "Student {" + super.toString() + "} Current Average: " + getAverage();
    }
}
