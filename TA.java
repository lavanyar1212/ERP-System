import java.util.*;

class TA extends Student{
    List<Course> courses = new ArrayList<>();
    TA(String email, String password) {
        super(email, password);
    }
    void viewstudents(Course course) {
        System.out.println("Enrolled Students in " + course.title + ":");
        for (int i = 0; i < course.enrolledstudents.size(); i++) {
            Student student = course.enrolledstudents.get(i);
            System.out.println(student.email);
        }

    }

    void tagrading(Course course, Student student, String grade) {
        if (course.enrolledstudents.contains(student)) {
            student.assigngrade(course, grade);
            System.out.println("Grade assigned to " + student.email + " for " + course.title + ": " + grade);
        } else {
            System.out.println("Student not enrolled.");
        }
    }
}
