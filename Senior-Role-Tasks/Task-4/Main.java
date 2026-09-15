//
// Group Employees by Department
//

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
      
      List<Employee> employees = List.of(
        new Employee(1L, "Ali", "IT", 150000),
        new Employee(2L, "Sara", "HR", 120000),
        new Employee(3L, "Ahmed", "IT", 180000),
        new Employee(4L, "John", "Finance", 160000),
        new Employee(5L, "Maria", "HR", 140000)
      );

      Map<String, List<Employee>> mp = employees.stream()
                    .collect(Collectors.groupingBy(employee -> employee.getDepartment()));

      
      for(Map.Entry<String, List<Employee>> e : mp.entrySet()) {
        System.out.println(e.getKey() + " --> " + e.getValue());
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
