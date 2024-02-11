
// Sure! Here's another question:

// In a school's grading system, each student takes multiple courses, and each course has multiple assignments. Each assignment has a score. Using flatMap(), 
// how would you calculate the average score of all assignments for each student?

// Feel free to provide your solution or discuss how you would approach solving this problem using flatMap().


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.OptionalDouble;
import java.util.stream.Stream;
class Assignment {
    private String name;
    private double score;

    public Assignment(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}

class Course {
    private String name;
    private List<Assignment> assignments;

    public Course(String name, List<Assignment> assignments) {
        this.name = name;
        this.assignments = assignments;
    }

    public String getName() {
        return name;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
}

class Student {
    private String name;
    private List<Course> courses;

    public Student(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
class StreamFlatMapExample3 {
    public static void main(String[] args) {
        List<Student> students = initializeStudents();
        
        students.forEach((student) -> {
            // double averageScore = student.getCourses().stream().flatMap(course -> course.getAssignments().stream()).mapToDouble(Assignment::getScore).average().orElse(0.0);
            
            Stream<Course> courses = student.getCourses().stream();
            
            Stream<Assignment> assignments = courses.flatMap(course -> course.getAssignments().stream());
            
            DoubleStream doubleStream = assignments.mapToDouble(Assignment::getScore);
            
            OptionalDouble optionalDouble = doubleStream.average();
            
            double averageScore = optionalDouble.orElse(0.0);
            
            System.out.println("Average score of " + student.getName() + " = " + averageScore);
            System.out.println("");
            System.out.println("");
        });
        
        
    }
    
    private static List<Student> initializeStudents() {
        // Assignments for Course 1
        Assignment assignment11 = new Assignment("Assignment 1", 85);
        Assignment assignment12 = new Assignment("Assignment 2", 90);
        List<Assignment> assignmentsCourse1 = Arrays.asList(assignment11, assignment12);

        // Assignments for Course 2
        Assignment assignment21 = new Assignment("Assignment 1", 75);
        Assignment assignment22 = new Assignment("Assignment 2", 80);
        List<Assignment> assignmentsCourse2 = Arrays.asList(assignment21, assignment22);

        // Assignments for Course 3
        Assignment assignment31 = new Assignment("Assignment 1", 95);
        Assignment assignment32 = new Assignment("Assignment 2", 85);
        List<Assignment> assignmentsCourse3 = Arrays.asList(assignment31, assignment32);

        // Assignments for Course 4
        Assignment assignment41 = new Assignment("Assignment 1", 80);
        Assignment assignment42 = new Assignment("Assignment 2", 75);
        List<Assignment> assignmentsCourse4 = Arrays.asList(assignment41, assignment42);

        // Courses for Student 1
        Course course1 = new Course("Math", assignmentsCourse1);
        Course course2 = new Course("English", assignmentsCourse2);
        List<Course> coursesStudent1 = Arrays.asList(course1, course2);

        // Courses for Student 2
        Course course3 = new Course("Science", assignmentsCourse3);
        Course course4 = new Course("History", assignmentsCourse4);
        List<Course> coursesStudent2 = Arrays.asList(course3, course4);

        // Students
        Student student1 = new Student("John", coursesStudent1);
        Student student2 = new Student("Alice", coursesStudent2);

        // List of students
        return Arrays.asList(student1, student2);
    }
}