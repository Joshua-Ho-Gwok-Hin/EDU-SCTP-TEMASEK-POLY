package model;

import java.util.HashMap;
import java.util.List;

public interface Evaluation {
    double getAverage();

    HashMap<Course, Double> getApprovedCourses();
}