//
// Multilevel grouping => Department -> Salary Grade
//

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
      
      List<Employee> employees = List.of(
    new Employee(1L, "Ali", "IT", 8000),
    new Employee(2L, "Sara", "IT", 12000),
    new Employee(3L, "Ahmed", "IT", 25000),
    new Employee(4L, "Hamza", "IT", 18000),
    new Employee(5L, "Zain", "IT", 7000),

    new Employee(6L, "Ayesha", "HR", 9000),
    new Employee(7L, "Bilal", "HR", 15000),
    new Employee(8L, "Fatima", "HR", 22000),
    new Employee(9L, "Usman", "HR", 19000),
    new Employee(10L, "Hira", "HR", 6500),

    new Employee(11L, "Hassan", "Finance", 11000),
    new Employee(12L, "Iqra", "Finance", 28000),
    new Employee(13L, "Omar", "Finance", 9500),
    new Employee(14L, "Sana", "Finance", 21000),
    new Employee(15L, "Talha", "Finance", 14000)
);


    Map<String, Map<String, List<Employee>>> mp = employees.stream().collect(
      Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.groupingBy(
          (e) -> {
            if (e.getSalary() < 10000) return "Grade C";
            else if (e.getSalary() < 20000) return "Grade B";
            return "Grade A";
          }
        )
      )
    );

    for (Map.Entry<String, Map<String, List<Employee>>> e : mp.entrySet()) {
      System.out.println(e.getKey() + "\n****************************************");
      for (Map.Entry<String, List<Employee>> e2 : e.getValue().entrySet()) {
        System.out.println(e2.getKey() + "\n-------------------------------------");
        for (Employee emp : e2.getValue()) {
          System.out.println(emp);
        }
        System.out.println("-------------------------------------");
      }
      System.out.println("\n\n");
    }


    }
}

class Employee {

    private Long id;
    private String name;
    private String department;
    private double salary;

    public Employee(Long id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
