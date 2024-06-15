package academic.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
                        if (!isDuplicateEnrollment(enrollment, enrollments) && isEnrollmentValid(enrollment, courses, students)) {
                            enrollments.add(enrollment);
                        }
                    }
                    break;
                default:
                    break;
            }
        }

TreeSet<Course> sortedCourses = new TreeSet<>((c1, c2) -> c2.getName().compareTo(c1.getName())); //besar ke kecil
TreeSet<Student> sortedStudents = new TreeSet<>((s1, s2) -> s2.getId().compareTo(s1.getId())); //besar ke kecil

        sortedCourses.addAll(courses);
        sortedStudents.addAll(students);

        for (Course course : sortedCourses) {
            System.out.println(course);
        }
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
    }

    private static boolean isDuplicateCourse(Course course, List<Course> courses) {
        return courses.stream().anyMatch(c -> c.getCode().equals(course.getCode()));
    }

    private static boolean isDuplicateStudent(Student student, List<Student> students) {
        return students.stream().anyMatch(s -> s.getId().equals(student.getId()));
    }

    private static boolean isDuplicateEnrollment(Enrollment enrollment, List<Enrollment> enrollments) {
        return enrollments.stream().anyMatch(e -> e.getId().equals(enrollment.getId()));
    }

    private static boolean isEnrollmentValid(Enrollment enrollment, List<Course> courses, List<Student> students) {
        return courses.stream().anyMatch(c -> c.getCode().equals(enrollment.getCode()))
                && students.stream().anyMatch(s -> s.getId().equals(enrollment.getId()));
    }
}
