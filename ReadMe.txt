1. Exception Handling:-
	1. Course Drop Deadline Exception: I have used Java's date library to compares dates and determine if it is past the deadline or not using .after for comparison and deadline date has been hardcoded to 27-9-24.
	2. Course Enrollment Exception: I have set the enrollment limit to 10 and if the list of students in a particular course is greater than the limit, then the exception is thrown.
	3. Invalid Login: I have hardcoded the students, professors, TA's and admin, and I use functions like studentemail and profemail to find whether or not the email and password that is being inputted same as the one that has already been set. If they do not match, then the exception says invalid credentials and take the user back to login menu.

2. TA class is being inherited by student class. It has all the functions of a student and additional (tagrading and viewstudents).
    TA can only view/assign grades to the students enrolled in his assigned course (course of which that student is a TA).
TA can also be assigned through a professor.

3. The Feedback class uses a generic type parameter (T), which means the data type for feedback attributes such as rating, and description can be defined when an object of the Feedback class is instantiated.
    This allows the class to handle multiple types of data while keeping the code reusable and type-safe.

Functionality:-
1. Feedback System- Feedback given for a specific course can only be viewed by the professor assigned to the course and the feedback is anonymous. Students have to enter the course code, and give description and/or rating as they wish.
2. TA - For TA, I have created a different usertype for it and it can do everything that a student can do like view courses, register etc and in addition view and manage grades of the students of the course it is teaching.
3. Professor can also assign a TA.