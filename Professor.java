import java.util.*;
class Professor extends User {
    List<Course> courses = new ArrayList<>();

    Professor(String email, String password) {
        super(email, password);
    }
    List<Course> getcourses() {
        return courses;
    }
    void addcourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            System.out.println("Course added.");
        } else {
            System.out.println("Course already present.");
        }
    }
    void assigngrade(Course course, Student student, String grade) {
        if (courses.contains(course)) {
            if (course.enrolledstudents.contains(student)) {
                student.assigngrade(course, grade);
                System.out.println("Grade assigned to " + student.email + " for " + course.title + ": " + grade);
            } else {
                System.out.println("Student not enrolled.");
            }
        } else {
            System.out.println("You are not assigned to this course.");
        }
    }


    void owncourse() {
        if (courses.isEmpty()) {
            System.out.println("You are not assigned to any courses.");
        } else {
            System.out.println("Your courses: ");
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                System.out.println(course);
            }

        }
    }

    void coursedetails(Course course) {
        if (courses.contains(course)) {
            System.out.println("Course Details:");
            System.out.println(course);
        } else {
            System.out.println("You are not assigned to this course.");
        }
    }

    void updatingdetails(Course course, String updatedsyllabus, String updatedhours, int updatedenlimit) {
        if (courses.contains(course)) {
            course.setsyllabus(updatedsyllabus);
            course.sethours(updatedhours);
            course.setelimit(updatedenlimit);
            System.out.println("Course details updated successfully.");
        } else {
            System.out.println("You are not assigned to this course.");
        }
    }

    void enrolledstudents(Course course) {
        if (courses.contains(course)) {
            System.out.println("Enrolled Students for " + course.title + ":");
            for (int i = 0; i < course.enrolledstudents.size(); i++) {
                Student student = course.enrolledstudents.get(i);
                String grade = student.grades.get(course);
                System.out.print(student.email + " - Grade: ");
                if (grade != null) {
                    System.out.println(grade);
                } else {
                    System.out.println("No grade assigned yet");
                }            }

        } else {
            System.out.println("You are not assigned to this course.");
        }
    }
    void allfeedback(List<Feedback> feedbacklist) {
        if (feedbacklist.isEmpty()) {
            System.out.println("No feedback available.");
        } else {
            for (int i = 0; i < feedbacklist.size(); i++) {
                Feedback feedback = feedbacklist.get(i);
                System.out.println((i + 1) + ". " + feedback.toString());
            }
        }
    }
    void assignta(Course course, TA ta){
        course.ta = ta;
        ta.courses.add(course);
        System.out.println("TA has been assigned.");
    }

}
