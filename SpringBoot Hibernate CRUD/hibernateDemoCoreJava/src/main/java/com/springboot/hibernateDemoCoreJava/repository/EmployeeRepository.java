package com.springboot.hibernateDemoCoreJava.repository;

import com.springboot.hibernateDemoCoreJava.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//No need to add @Repository annotation
//No need to @Transactional annotation in service layer also
//These are provided by Spring Data JPA
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
