import java.text.SimpleDateFormat;
import java.util.*;
class Student extends User {
    int semester;
    List<Course> registeredcourses = new ArrayList<>();
    Map<Course, String> grades = new HashMap<>();
    float SGPA;
    float CGPA;
    List<Complaint> complaints = new ArrayList<>();
    Map<Course, List<Feedback>> feedbacklist = new HashMap<>();

    Student(String email, String password) {
        super(email, password);
        this.semester = 1;
    }

    void availablecourses(List<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i));
        }
    }

    void assigngrade(Course course, String grade) {
        grades.put(course, grade);
        System.out.println("Grade assigned successfully.");
    }

    void courseregistration(Course course) {
        if (registeredcourses.contains(course)) {
            System.out.println("You are already registered.");
        } else if (course.enrolledstudents.size()>course.elimit){
            throw new enrollmentlimitexception("Course Enrollment Limit Already Full.");
        } else if (course.prerequisites.stream().allMatch(grades::containsKey)) {
            registeredcourses.add(course);
            course.studentenroll(this);
            System.out.println("Successfully registered for the course.");
        } else {
            System.out.println("Prerequisites not met.");
        }
    }


    void dropcourse(Course course) {
        Date today = new Date();
        if (today.after(course.deadlinefordrop)){
            throw new coursedropexception("Deadline Passed.");
        }
        if (registeredcourses.remove(course)) {
            System.out.println("Course dropped.");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    void viewschedule() {
        for (Course course : registeredcourses) {
            System.out.println(course.schedule);
        }
    }

    void trackingprogress() {
        if (grades.isEmpty()) {
            System.out.println("No grades assigned yet.");
        } else {
            System.out.println("Grades: ");
            for (Map.Entry<Course, String> entry : grades.entrySet()) {
                Course course = entry.getKey();
                String grade = entry.getValue();
                System.out.println(course.title + ": " + grade);
            }
        }
    }

    void submitcomplaint(String description) {
        complaints.add(new Complaint(description));
    }

    void submitfeedbackdescription(Course course,String description) {
        Feedback feedback = new Feedback(description);
        if (!feedbacklist.containsKey(course)) {
            feedbacklist.put(course, new ArrayList<>());
        }
        feedbacklist.get(course).add(feedback);

    }

    void submitfeedbackrating(Course course, int rating) {
        Feedback feedback = new Feedback(rating);
        if (!feedbacklist.containsKey(course)) {
            feedbacklist.put(course, new ArrayList<>());
        }
        feedbacklist.get(course).add(feedback);
    }

    void submitfeedbackboth(Course course, String description, int rating) {
        Feedback feedback = new Feedback(description);
        Feedback feedback1 = new Feedback(rating);
        if (!feedbacklist.containsKey(course)) {
            feedbacklist.put(course, new ArrayList<>());
        }
        feedbacklist.get(course).add(feedback);
        feedbacklist.get(course).add(feedback1);
    }


}
