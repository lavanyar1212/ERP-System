import java.util.*;
class Admin extends User {
    List<Course> courses;
    List<Complaint> complaints = new ArrayList<>();
    List<Professor> professors = new ArrayList<>();

    Admin(String email, String password, List<Course> courses, List<Professor> professors) {
        super(email, password);
        this.courses = courses;
        this.professors = professors;
    }

    void viewcourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                System.out.println((i + 1) + ". " + course.code + ": " + course.title + " (" + course.credits + " credits)");
            }
        }
    }


    void removecourse(int n) {
        if (n >= 0 && n < courses.size()) {
            courses.remove(n);
            System.out.println("Course removed successfully.");
        } else {
            System.out.println("Invalid course index.");
        }
    }


    void studentrecords() {
        System.out.println("Student Name: Lavanya");
        System.out.println("Course: CSE101");
        System.out.println("Grade: A");
    }
    void allprofessors() {
        if (professors.isEmpty()) {
            System.out.println("No professors available.");
        } else {
            for (int i = 0; i < professors.size(); i++) {
                Professor professor = professors.get(i);
                System.out.println((i + 1) + ". " + professor.email);
            }
        }
    }
    void professorassignment(Course course, Professor professor) {
        course.professor = professor;
        professor.courses.add(course);
        System.out.println("Professor assigned.");
    }

    void assigningcoursetoprofessor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available courses:");
        viewcourses();
        System.out.print("Enter the number of the course: ");
        int cn = Integer.parseInt(scanner.nextLine()) - 1;

        if (correctindex(cn, courses.size())) {
            Course selectedCourse = courses.get(cn);

            System.out.println("Available professors:");
            allprofessors();
            System.out.print("Enter the number of the professor: ");
            int pn = Integer.parseInt(scanner.nextLine()) - 1;

            if (correctindex(pn, professors.size())) {
                Professor selectedProfessor = professors.get(pn);
                professorassignment(selectedCourse, selectedProfessor);
                System.out.println("Professor assigned to the course.");
            } else {
                System.out.println("Invalid professor.");
            }
        } else {
            System.out.println("Invalid course number.");
        }
    }

    private boolean correctindex(int in, int lsize) {
        return in >= 0 && in < lsize;
    }


    void allcomplaints(List<Complaint> complaintlist) {
        if (complaintlist.isEmpty()) {
            System.out.println("No complaints available.");
        } else {
            for (int i = 0; i < complaintlist.size(); i++) {
                Complaint complaint = complaintlist.get(i);
                System.out.println((i + 1) + ". " + complaint.description + " - Status: " + complaint.status);
            }
        }
    }

    void complaintstatus(List<Complaint> complaintlist, int in, String status) {
        if (in >= 0 && in < complaintlist.size()) {
            Complaint complaint = complaintlist.get(in);
            System.out.println("Current Complaint: " + complaint.description + " - Status: " + complaint.status);
            complaint.status = status;
            System.out.println("Complaint status updated to: " + status);
        } else {
            System.out.println("Invalid complaint index.");
        }
    }

}