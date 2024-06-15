package academic.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;

public class Driver2 {

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
                            isValidEnrollment(enrollment, courses, students); // Cek validitas enrollments tanpa menambahkannya langsung
                            enrollments.add(enrollment); // Tambahkan enrollment jika valid
                   }
                    break;
                default:
                    break;
            }
        }

        // TreeSets for sorting courses, students, and enrollments
        TreeSet<Course> sortedCourses = new TreeSet<>((c1, c2) -> {
            if (!c1.getCode().equals(c2.getCode())) { //membandingkan terlebih dahulu course dengan code yang berbeda, jika sama bandingkan dengan name
                return c1.getCode().compareTo(c2.getCode());
            }
            return c1.getName().compareTo(c2.getName());
        });
        TreeSet<Student> sortedStudents = new TreeSet<>((s1, s2) -> s2.getId().compareTo(s1.getId()));
        TreeSet<Enrollment> sortedEnrollments = new TreeSet<>((e1, e2) -> {
        if (!e1.getCode().equals(e2.getCode())) {
        return e2.getCode().compareTo(e1.getCode());
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

    private static boolean isValidEnrollment(Enrollment enrollment, List<Course> courses, List<Student> students) {
        boolean validCourse = false;
        boolean validStudent = false;

        for (Course course : courses) {
            if (course.getCode().equals(enrollment.getCode())) {
                validCourse = true;
                break;
            }
        }

        for (Student student : students) {
            if (student.getId().equals(enrollment.getId())) {
                validStudent = true;
                break;
            }
        }

        if (!validCourse) {
            throw new IllegalArgumentException("invalid course|" + enrollment.getCode());
        }

        if (!validStudent) {
            throw new IllegalArgumentException("invalid student|" + enrollment.getId());
        }

        return true;
    }
}
