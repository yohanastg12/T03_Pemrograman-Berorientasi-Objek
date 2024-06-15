package academic.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.util.TreeSet;
import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;


public class Driver1 {
 

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] _args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Enrollment> enrollments = new ArrayList<>();
        List<String> inputList = new ArrayList<>();

        do {
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }
            inputList.add(input);
        } while (true);

        for (String input : inputList) {
            String[] command = input.split("#");

            switch (command[0]) {
                case "course-add":
                    if (command.length == 5) {
                        Course course = new Course(command[1], command[2], Integer.parseInt(command[3]), command[4]);
                        if (!isDuplicateCourse(course, courses)) {
                            courses.add(course);
                        }
                    }
                    break;
                case "student-add":
                    if (command.length == 5) {
                        Student student = new Student(command[1], command[2], command[3], command[4]);
                        if (!isDuplicateStudent(student, students)) {
                            students.add(student);
                        }
                    }
                    break;
                case "enrollment-add":
                    if (command.length == 5) {
                        Enrollment enrollment = new Enrollment(command[1], command[2], command[3], command[4]);
                        if (!isDuplicateEnrollment(enrollment, enrollments)) {
                            enrollments.add(enrollment);
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        
TreeSet<Course> sortedCourses = new TreeSet<>((c1, c2) -> c1.getName().compareTo(c2.getName()));
TreeSet<Student> sortedStudents = new TreeSet<>((s1, s2) -> s1.getId().compareTo(s2.getId()));
TreeSet<Enrollment> sortedEnrollments = new TreeSet<>((e1, e2) -> {
    if (!e1.getCode().equals(e2.getCode())) {
        return e1.getCode().compareTo(e2.getCode());
    }
    return e1.getId().compareTo(e2.getId());
});

sortedCourses.addAll(courses);
sortedStudents.addAll(students);
sortedEnrollments.addAll(enrollments);

for (Course course : sortedCourses) {
    System.out.println(course);
}
for (Student student : sortedStudents) {
    System.out.println(student);
}
for (Enrollment enrollment : sortedEnrollments) {
    System.out.println(enrollment);
}
    }

    private static boolean isDuplicateCourse(Course course, List<Course> courses) {
        for (Course c : courses) {
            if (c.getCode().equals(course.getCode())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDuplicateStudent(Student student, List<Student> students) {
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDuplicateEnrollment(Enrollment enrollment, List<Enrollment> enrollments) {
        for (Enrollment e : enrollments) {
            if (e.getId().equals(enrollment.getId())) {
                return true;
            }
        }
        return false;
    }

}
