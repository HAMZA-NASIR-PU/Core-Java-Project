// Certainly! Here's another advanced Java interview question:

// Question:
// You are given a list of employees, each represented by an Employee class with attributes such as name, department, salary, and age. Write a Java program to find the average salary of employees in each department. Additionally, find the department with the highest average salary.

// Please provide the code using Java 8 Stream API and any necessary classes or interfaces.

// Feel free to take your time to write the code, and I'll be here to provide guidance or feedback if needed.

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

class Employee {
    private String name;
    private String department;
    private double salary;
    private int age;

    public Employee(String name, String department, double salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

public class PredicateQuestion4 {

    public static List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee("John", "Engineering", 60000.0, 30),
                new Employee("Alice", "Engineering", 65000.0, 28),
                new Employee("Bob", "HR", 55000.0, 35),
                new Employee("Charlie", "HR", 58000.0, 32),
                new Employee("David", "Sales", 70000.0, 40),
                new Employee("Eve", "Sales", 72000.0, 38),
                new Employee("Frank", "Marketing", 62000.0, 33),
                new Employee("Grace", "Marketing", 64000.0, 31));
    }

    public static void main(String[] args) {

        List<Employee> emp = getEmployees();

        Map<String, List<Employee>> data = emp.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        // System.out.println(data);

        // for(Map.Entry<String, List<Employee>> entry: data.entrySet()) {
        // System.out.println("**********************************************************************************");
        // System.out.println(entry);
        // System.out.println("Average salary in department " + entry.getKey() + " " +
        // entry.getValue().stream().mapToDouble(Employee::getSalary).average().getAsDouble());
        // System.out.println("**********************************************************************************");
        // }

        Map<String, Double> averageSalaryByDepartment = new HashMap<String, Double>();
        for (Map.Entry<String, List<Employee>> entry : data.entrySet()) {
            double averageSalary = entry.getValue().stream().mapToDouble(Employee::getSalary).average().getAsDouble();
            averageSalaryByDepartment.put(entry.getKey(), averageSalary);
        }
        System.out.println(averageSalaryByDepartment);

        // Finding the department with the highest average salary
        String departmentWithHighestAverageSalary = averageSalaryByDepartment.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No department found");

        System.out.println("Department with the highest average salary: " + departmentWithHighestAverageSalary);

    }
}