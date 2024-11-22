import java.util.*;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Professor> professors = new ArrayList<>();
        List<Complaint> complaintlist = new ArrayList<>();
        List<Feedback> feedbacklist = new ArrayList<>();

        Student Lavanya = new Student("lavanya", "123a");
        Student Aditi = new Student("aditi", "123a");
        Student Purvi = new Student("purvi", "123a");
        Student Sid = new Student("siddharth", "123a");
        students.add(Lavanya);
        students.add(Aditi);
        students.add(Purvi);

        TA Sid1 = new TA("siddharth", "123a");
        TA Sagar = new TA("sagar", "123a");
        Admin Jagdish = new Admin("jagdish", "123a", courses, professors);

        Professor Archana = new Professor("archana", "123a");
        Professor Tanmay = new Professor("tanmay", "123a");
        Professor Mia = new Professor("mia", "123a");
        Professor Mac = new Professor("mac", "123a");

        professors.add(Archana);
        professors.add(Tanmay);
        professors.add(Mia);
        professors.add(Mac);

        courses.add(new Course("CSE101", "Introduction to Coding", 4, Archana, "Coding", "CS101: Mon 10 AM", "CS101: Tue, Thurs 3 PM", Sid1));
        courses.add(new Course("CSE102", "Data Structures", 4, Tanmay, "Data Structures", "CS102: Wed 12 PM", "CS102: Mon, Wed, Fri 11 AM", Sagar));
        courses.add(new Course("CSE103", "AP", 4, Mia, "Java", "CS103: Mon 10 AM", "CS103: Tue, Thurs 3 PM", Sid1));
        courses.add(new Course("CSE104", "ML", 4, Mac, "Javascript", "CS104: Wed 12 PM", "CS104: Mon, Wed, Fri 11 AM",Sagar));

        Archana.addcourse(courses.get(0));
        Tanmay.addcourse(courses.get(1));
        Mia.addcourse(courses.get(2));
        Mac.addcourse(courses.get(3));

        Scanner object = new Scanner(System.in);


        System.out.println("Welcome to University Course Registration System.");

        String userType;
        do {
            System.out.println("Login as: ");
            System.out.println("1. Student");
            System.out.println("2. Professor");
            System.out.println("3. Admin");
            System.out.println("4. Teaching Assistant");
            System.out.println("5. Exit Application.");
            System.out.println("Your Choice: ");
            userType = object.nextLine();

            if (userType.equals("1")) {
                System.out.println("Enter EmailId: ");
                String email = object.nextLine();
                System.out.println("Enter Password: ");
                String password = object.nextLine();
                try{
                    Student student = studentemail(students, email, password);

                    String choice;
                    do {
                        System.out.println("What do you want to do?");
                        System.out.println("1. View Available Courses.");
                        System.out.println("2. Register for Courses.");
                        System.out.println("3. View Schedule.");
                        System.out.println("4. Track Academic Progress.");
                        System.out.println("5. Drop Courses.");
                        System.out.println("6. Submit Complaints.");
                        System.out.println("7. Submit Course Feedback");
                        System.out.println("8. Logout.");
                        System.out.println("Enter Your Choice: ");
                        choice = object.nextLine();

                        switch (choice) {
                            case "1" -> student.availablecourses(courses);
                            case "2" -> {
                                System.out.println("Enter course code: ");
                                String ccode = object.nextLine();
                                Course courseforregistration = findcourse(courses, ccode);
                                if (courseforregistration != null) {
                                    try{
                                        student.courseregistration(courseforregistration);
                                    }
                                    catch (enrollmentlimitexception e){
                                        System.out.println(e.getMessage());
                                    }
                                } else {
                                    System.out.println("Course not found.");
                                }
                            }
                            case "3" -> student.viewschedule();
                            case "4" -> {
                                student.trackingprogress();
                            }
                            case "5" -> {
                                System.out.println("Enter course code: ");
                                String ccode = object.nextLine();
                                Course coursefordrop = findcourse(courses, ccode);
                                if (coursefordrop != null) {
                                    try{
                                        student.dropcourse(coursefordrop);
                                    }
                                    catch (coursedropexception e){
                                        System.out.println(e.getMessage());
                                    }
                                } else {
                                    System.out.println("Course not found.");
                                }
                            }
                            case "6" -> {
                                System.out.println("Enter your complaint: ");
                                String complaintdescription = object.nextLine();
                                Complaint complaint = new Complaint(complaintdescription);
                                complaintlist.add(complaint);
                                student.submitcomplaint(complaintdescription);
                                System.out.println("Complaint submitted successfully.");
                            }
                            case "7" ->{
                                System.out.println("Enter Course Code: ");
                                String ccode = object.nextLine();
                                Course courseforfeedback = findcourse(courses, ccode);
                                if (courseforfeedback != null) {
                                    System.out.println("1. Submit Description.");
                                    System.out.println("2. Submit Rating.");
                                    System.out.println("3. Submit Description and Rating.");
                                    String fc = object.nextLine();
                                    switch (fc){
                                        case "1" -> {
                                            System.out.println("Enter Description: ");
                                            String descrip = object.nextLine();
                                            Feedback feedback = new Feedback(descrip);
                                            feedbacklist.add(feedback);
                                            student.submitfeedbackdescription(courseforfeedback, descrip);
                                            System.out.println("Feedback Submitted.");
                                        }
                                        case "2" -> {
                                            System.out.println("Enter Rating out of 5: ");
                                            int rate = object.nextInt();
                                            Feedback feedback = new Feedback(rate);
                                            feedbacklist.add(feedback);
                                            student.submitfeedbackrating(courseforfeedback,rate);
                                            System.out.println("Feedback Submitted.");
                                        }
                                        case "3" -> {
                                            System.out.println("Enter Description: ");
                                            String descrip = object.nextLine();
                                            System.out.println("Enter Rating out of 5: ");
                                            int rate = object.nextInt();
                                            Feedback feedback = new Feedback(descrip, rate);
                                            feedbacklist.add(feedback);
                                            student.submitfeedbackboth(courseforfeedback, descrip,rate);
                                            System.out.println("Feedback Submitted.");
                                        }
                                        default -> System.out.println("Invalid choice.");
                                    }
                                }
                            }
                            case "8" -> System.out.println("Log Out Successful.");
                            default -> System.out.println("Invalid choice.");
                        }
                    } while (!choice.equals("8"));}
                catch (invalidloginexception e) {
                    System.out.println(e.getMessage());
                }

            } else if (userType.equals("2")) {
                System.out.println("Enter EmailId: ");
                String email = object.nextLine();
                System.out.println("Enter Password: ");
                String password = object.nextLine();
                try{
                    Professor professor = profemail(professors, email, password);


                    if (professor != null) {
                        String choice2;
                        do {
                            System.out.println("What do you want to do?");
                            System.out.println("1. Manage Courses.");
                            System.out.println("2. View Enrolled Students.");
                            System.out.println("3. Assign Grades.");
                            System.out.println("4. View Feedback.");
                            System.out.println("5. Logout.");
                            choice2 = object.nextLine();

                            switch (choice2) {
                                case "1" -> {
                                    System.out.println("1. View Course Details.");
                                    System.out.println("2. Update Course Details.");
                                    String opt = object.nextLine();

                                    switch (opt) {
                                        case "1" -> {
                                            professor.owncourse();
                                            System.out.println("Enter course code to view details: ");
                                            String ccode = object.nextLine();
                                            Course viewcourse = findcourse(professor.getcourses(), ccode);
                                            if (viewcourse != null) {
                                                professor.coursedetails(viewcourse);
                                            } else {
                                                System.out.println("Course not found.");
                                            }
                                        }
                                        case "2" -> {
                                            professor.owncourse();
                                            System.out.println("Enter course code to update: ");
                                            String ccode = object.nextLine();
                                            Course courseupdate = findcourse(professor.getcourses(), ccode);
                                            if (courseupdate != null) {
                                                System.out.println("Enter new syllabus: ");
                                                String ns = object.nextLine();
                                                System.out.println("Enter new office hours: ");
                                                String no = object.nextLine();
                                                System.out.println("Enter new enrollment limit: ");
                                                int ne = Integer.parseInt(object.nextLine());
                                                professor.updatingdetails(courseupdate, ns, no, ne);
                                            } else {
                                                System.out.println("Course not found.");
                                            }
                                        }
                                        default -> System.out.println("Invalid choice.");
                                    }
                                }

                                case "2" -> {
                                    System.out.println("Select a course:");
                                    professor.owncourse();
                                    System.out.println("Enter course code: ");
                                    String ccode = object.nextLine();
                                    Course viewcourse = findcourse(professor.getcourses(), ccode);
                                    if (viewcourse != null) {
                                        professor.enrolledstudents(viewcourse);
                                    } else {
                                        System.out.println("Course not found.");
                                    }
                                }

                                case "3" -> {
                                    System.out.println("Select a course:");
                                    professor.owncourse();
                                    System.out.println("Enter course code: ");
                                    String ccode = object.nextLine();
                                    Course cassign = findcourse(professor.getcourses(), ccode);

                                    if (cassign != null) {
                                        System.out.println("Enter student email: ");
                                        String semail = object.nextLine();
                                        Student student = studentemail(students, semail,"123a");

                                        if (student != null) {
                                            System.out.println("Enter grade: ");
                                            String grade = object.nextLine();
                                            professor.assigngrade(cassign, student, grade);
                                        } else {
                                            System.out.println("Student not found.");
                                        }
                                    } else {
                                        System.out.println("Course not found.");
                                    }
                                    break;
                                }
                                case "4" -> {
                                    System.out.println("Feedback for your course: ");
                                    professor.allfeedback(feedbacklist);
                                }

                                case "5" -> System.out.println("Log Out Successful.");
                                default -> System.out.println("Invalid choice.");
                            }
                        } while (!choice2.equals("5"));}
                    }
                    catch (invalidloginexception e){
                        System.out.println(e.getMessage());
                    }

            } else if (userType.equals("3")) {
                System.out.println("You are an admin.");
                System.out.println("Enter EmailId: ");
                String email = object.nextLine();
                System.out.println("Enter Password: ");
                String password = object.nextLine();
                Admin admin = new Admin(email, password, courses, professors);

                String choice3;
                do {
                    System.out.println("What do you want to do?");
                    System.out.println("1. Manage Course Catalog");
                    System.out.println("2. Manage Student Records");
                    System.out.println("3. Assign Professors to Courses");
                    System.out.println("4. Handle Complaints");
                    System.out.println("5. Logout");
                    choice3 = object.nextLine();

                    switch (choice3) {
                        case "1" -> {
                            System.out.println("1. View All Courses");
                            System.out.println("2. Add a Course");
                            System.out.println("3. Remove a Course");
                            String cc = object.nextLine();

                            switch (cc) {
                                case "1" -> admin.viewcourses();
                                case "2" -> {
                                    System.out.println("Course Name: ");
                                    String cname = object.nextLine();
                                    System.out.println("Course Code: ");
                                    String ccode = object.nextLine();
                                    System.out.println("Enter Course Credits: ");
                                    int ccredits = object.nextInt();
                                    object.nextLine();
                                    System.out.println("Enter Professor's Name: ");
                                    String cprof = object.nextLine();
                                    System.out.println("Enter Syllabus: ");
                                    String csyllabus = object.nextLine();
                                    System.out.println("Enter Office Hours: ");
                                    String coffice = object.nextLine();
                                    System.out.println("Enter Schedule: ");
                                    String cschedule = object.nextLine();
                                    Professor professor = new Professor(cprof, "");
                                    Course newCourse = new Course(ccode, cname, ccredits, professor, csyllabus, coffice, cschedule, Sagar);
                                    courses.add(newCourse);
                                }

                                case "3" -> {
                                    admin.viewcourses();
                                    System.out.println("Enter the number of the course to remove: ");
                                    int n = Integer.parseInt(object.nextLine()) - 1;
                                    admin.removecourse(n);
                                }

                                default -> System.out.println("Invalid choice.");
                            }
                        }

                        case "2" -> admin.studentrecords();
                        case "3" -> admin.assigningcoursetoprofessor();
                        case "4" -> {
                            System.out.println("1. View Complaints");
                            System.out.println("2. Update Complaint Status");
                            String c = object.nextLine();
                            if (c.equals("1")) {
                                admin.allcomplaints(complaintlist);
                            } else {
                                System.out.println("Enter complaint number to update: ");
                                int n = Integer.parseInt(object.nextLine()) - 1;
                                System.out.println("Enter new status: ");
                                String newStatus = object.nextLine();
                                admin.complaintstatus(complaintlist, n, newStatus);
                            }
                        }

                        case "5" -> System.out.println("Log Out Successful.");
                        default -> System.out.println("Invalid choice.");
                    }

                } while (!choice3.equals("5"));
//TA
            } else if (userType.equals("4")) {
                System.out.println("Enter EmailId: ");
                String email = object.nextLine();
                System.out.println("Enter Password: ");
                String password = object.nextLine();
                TA ta = new TA(email, password);
                String choiceta;
                do {
                    System.out.println("What do you want to do?");
                    System.out.println("1. View Available Courses.");
                    System.out.println("2. Register for Courses.");
                    System.out.println("3. View Schedule.");
                    System.out.println("4. Track Academic Progress.");
                    System.out.println("5. Drop Courses.");
                    System.out.println("6. Submit Complaints.");
                    System.out.println("7. Submit Course Feedback");
                    System.out.println("8. View Student Grades.(TA)");
                    System.out.println("9. Manage Student Grades.(TA)");
                    System.out.println("10. Logout.");
                    System.out.println("Enter Your Choice: ");
                    choiceta = object.nextLine();

                    switch (choiceta) {
                        case "1" -> ta.availablecourses(courses);
                        case "2" -> {
                            System.out.println("Enter course code to register: ");
                            String ccode = object.nextLine();
                            Course courseforregistration = findcourse(courses, ccode);
                            if (courseforregistration != null) {
                                try{
                                    ta.courseregistration(courseforregistration);
                                }
                                catch (enrollmentlimitexception e){
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Course not found.");
                            }
                        }
                        case "3" -> ta.viewschedule();
                        case "4" -> {
                            ta.trackingprogress();
                        }
                        case "5" -> {
                            System.out.println("Enter course code to drop: ");
                            String ccode = object.nextLine();
                            Course coursefordrop = findcourse(courses, ccode);
                            if (coursefordrop != null) {
                                try{
                                    ta.dropcourse(coursefordrop);
                                }
                                catch (coursedropexception e){
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Course not found.");
                            }
                        }
                        case "6" -> {
                            System.out.println("Enter your complaint: ");
                            String complaintdescription = object.nextLine();
                            Complaint complaint = new Complaint(complaintdescription);
                            complaintlist.add(complaint);
                            ta.submitcomplaint(complaintdescription);
                            System.out.println("Complaint submitted successfully.");
                        }
                        case "7" ->{
                            System.out.println("Enter Course Code for Feedback: ");
                            String ccode = object.nextLine();
                            Course courseforfeedback = findcourse(courses, ccode);
                            if (courseforfeedback != null) {
                                System.out.println("1. Submit Description.");
                                System.out.println("2. Submit Rating.");
                                System.out.println("3. Submit Description and Rating.");
                                String fc = object.nextLine();
                                switch (fc){
                                    case "1" -> {
                                        System.out.println("Enter Description: ");
                                        String descrip = object.nextLine();
                                        Feedback feedback = new Feedback(descrip);
                                        feedbacklist.add(feedback);
                                        ta.submitfeedbackdescription(courseforfeedback, descrip);
                                        System.out.println("Feedback Submitted.");
                                    }
                                    case "2" -> {
                                        System.out.println("Enter Rating out of 5: ");
                                        int rate = object.nextInt();
                                        Feedback feedback = new Feedback(rate);
                                        feedbacklist.add(feedback);
                                        ta.submitfeedbackrating(courseforfeedback,rate);
                                        System.out.println("Feedback Submitted.");
                                    }
                                    case "3" -> {
                                        System.out.println("Enter Description: ");
                                        String descrip = object.nextLine();
                                        System.out.println("Enter Rating out of 5: ");
                                        int rate = object.nextInt();
                                        Feedback feedback = new Feedback(descrip, rate);
                                        feedbacklist.add(feedback);
                                        ta.submitfeedbackboth(courseforfeedback, descrip,rate);
                                        System.out.println("Feedback Submitted.");
                                    }
                                    default -> System.out.println("Invalid choice.");
                                }
                            }
                        }
                        case "8" -> {
                            System.out.print("Enter course code to view enrolled students: ");
                            String ccode = object.nextLine();
                            Course course = findcourse(courses, ccode);
                            if (course != null) {
                                ta.viewstudents(course);
                            } else {
                                System.out.println("Course not found.");
                            }
                        }
                        case "9" -> {
                            System.out.print("Enter course code to assign grades: ");
                            String ccode = object.nextLine();
                            Course course = findcourse(courses, ccode);
                            if (course != null) {
                                System.out.print("Enter student email: ");
                                String semail = object.nextLine();
                                Student student1 = studentemail(students, semail, "123a");
                                if (student1 != null) {
                                    System.out.print("Enter grade: ");
                                    String grade = object.nextLine();
                                    ta.tagrading(course, student1, grade);
                                } else {
                                    System.out.println("Student not found.");
                                }
                            } else {
                                System.out.println("Course not found.");
                            }
                        }
                        case "10" -> System.out.println("Log Out Successful.");
                        default -> System.out.println("Invalid choice.");
                    }
                } while (!choiceta.equals("10"));}


            else if (!userType.equals("5")) {
                System.out.println("Invalid choice.");
            }

        } while (!userType.equals("5"));

        System.out.println("Exiting the application.");
    }

    private static Course findcourse(List<Course> courses, String ccode) {
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (course.code.equalsIgnoreCase(ccode)) {
                return course;
            }
        }
        return null;
    }

    private static Professor profemail(List<Professor> professors, String email, String password) {
        for (int i = 0; i < professors.size(); i++) {
            Professor professor = professors.get(i);
            if (professor.email.equalsIgnoreCase(email) && professor.password.equals(password)) {
                return professor;
            }
        }
        throw new invalidloginexception("Incorrect Credentials.");
    }

    private static Student studentemail(List<Student> students, String email, String password) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.email.equalsIgnoreCase(email)&& student.password.equals(password)) {
                return student;
            }
        }
        throw new invalidloginexception("Incorrect Credentials.");
    }
}