import java.text.SimpleDateFormat;
import java.util.*;

class Course {
    String code;
    String title;
    Professor professor;
    int credits;
    List<Course> prerequisites;
    String schedule;
    List<Student> enrolledstudents = new ArrayList<>();
    String syllabus;
    String officehours;
    int elimit;
    Date deadlinefordrop;
    TA ta;


    Course(String code, String title, int credits, Professor professor,String syllabus, String officehours, String schedule, TA ta) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.professor = professor;
        this.schedule = schedule;
        this.prerequisites = new ArrayList<>();
        this.syllabus = syllabus;
        this.officehours = officehours;
        this.elimit = 10;
        this.deadlinefordrop = new Date(2024-1900,8, 27);
        this.ta = ta;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

    @Override
    public String toString() {
        return "Course Details:\n" +
                "-----------------\n" +
                "Code: " + code + "\n" +
                "Title: " + title + "\n" +
                "Credits: " + credits + "\n" +
                "Professor: " + (professor != null ? professor.email : "TBA") + "\n" +
                "Syllabus: " + syllabus + "\n" +
                "Office Hours: " + officehours + "\n" +
                "Enrollment Limit: " + elimit + "\n" +
                "Drop Deadline: " + sdf.format(deadlinefordrop) + "\n";
    }

    void setsyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    void sethours(String officehours) {
        this.officehours = officehours;
    }

    void setelimit(int limit) {
        this.elimit = limit;
    }

    void studentenroll(Student student) {
        enrolledstudents.add(student);
    }
}
