//
// Calculate department salary.
//
import java.util.*;
import java.util.function.*;
import java.util.stream.*;


public class Main {
    public static void main(String[] args) {

      List<Employee> employees = List.of(
        new Employee(1L, "Ali", "IT", 150000),
        new Employee(2L, "Sara", "HR", 120000),
        new Employee(3L, "Ahmed", "IT", 180000),
        new Employee(4L, "John", "Finance", 160000),
        new Employee(5L, "Maria", "HR", 140000)
      );

      // Approach # 1

      Map<String, Double> mp1 = employees.stream().collect(
        Collectors.groupingBy(
          Employee::getDepartment,
          Collectors.collectingAndThen(
            Collectors.toList(),
            list -> list.stream().mapToDouble(Employee::getSalary).sum()
          )
        )
      );

      for (Map.Entry<String, Double> entry : mp1.entrySet()) {
        System.out.println(entry.getKey() + " --> " + entry.getValue());
      }

      // System.out.println("\nAfter Sorting:\n");

      // mp1.entrySet().stream()
      //               .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
      //               .forEach(System.out::println);


      System.out.println("\n\n");

      // Approach # 2

      Map<String, Double> mp2 = employees.stream().collect(
        Collectors.groupingBy(
          Employee::getDepartment,
        Collectors.summingDouble(Employee::getSalary)
        )
      );

      for (Map.Entry<String, Double> entry : mp2.entrySet()) {
        System.out.println(entry.getKey() + " --> " + entry.getValue());
      }

      System.out.println("\n\n");

      // Approach # 3

      Map<String, Double> mp3 = employees.stream().collect(
        Collectors.groupingBy(
          Employee::getDepartment,
          Collectors.mapping(
            Employee::getSalary,
            Collectors.summingDouble(x -> x)
          )
        )
      );

      for (Map.Entry<String, Double> entry : mp3.entrySet()) {
        System.out.println(entry.getKey() + " --> " + entry.getValue());
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
