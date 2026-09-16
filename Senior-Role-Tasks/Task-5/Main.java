//
// Highest paid employee in each department.
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

      // Find employee with highest salary:

      Optional<Employee> empHighestSalary = employees.stream().collect(
        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
      );

      System.out.println("Employee with highest salary:" + empHighestSalary.get() + "\n\n");

      Map<String, Employee> mp = employees.stream().collect(Collectors.toMap(
                                      emp -> emp.getDepartment(), // KEY
                                      emp -> emp,                 // VALUE
                                      (e1, e2) -> 
                                        e1.getSalary() > e2.getSalary() ? e1 : e2  // MERGE FUNCTION
                                    ));

      
      for (Map.Entry<String, Employee> e : mp.entrySet()) {
        System.out.println(e.getKey() + "-->" + e.getValue());
      }


      // Approach # 2

      Map<String, Employee> mp2 = employees.stream()
                                    .collect(
                                        Collectors.groupingBy(
                                            Employee::getDepartment,
                                            Collectors.collectingAndThen(
                                                Collectors.maxBy(
                                                    Comparator.comparingDouble(Employee::getSalary)
                                                  ),
                                                optionalEmployee -> optionalEmployee.get()
                                            )
                                        )
                                    );

      System.out.println("\n\n");

      for(Map.Entry<String, Employee> e : mp2.entrySet()) {
        System.out.println(e.getKey() + "-->" + e.getValue());
      }
    

    // Approach # 3

        Map<String, Employee> mp3 = employees.stream().collect(
            Collectors.groupingBy( Employee::getDepartment, Collectors.collectingAndThen(
                Collectors.toList(), list -> { list.sort( Comparator.comparingDouble(Employee::getSalary) ); return list.get(list.size() - 1); }
            ))
        ); 
    
        System.out.println("\n\n");
        for(Map.Entry<String, Employee> e : mp3.entrySet()) {
        System.out.println(e.getKey() + "-->" + e.getValue());
      }

    employees.stream().collect(
        Collectors.groupingBy(
          Employee::getDepartment,
          Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
              list.sort(Comparator.comparingDouble((Employee e) -> e.getSalary()).reversed());
              return list.get(0);
            }
          )
        )
      );


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
