package com.springboot.hibernateDemoCoreJava.service.impl;

import com.springboot.hibernateDemoCoreJava.exception.ResourceNotFoundException;
import com.springboot.hibernateDemoCoreJava.model.Employee;
import com.springboot.hibernateDemoCoreJava.repository.EmployeeRepository;
import com.springboot.hibernateDemoCoreJava.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //Inject EmployeeRepository using constructor based injection

//    @Autowired //No need to specify this because Starting with spring 4.3, if a class, which is configured as Spring bean,
    // has only ons constructyor, the @Autowired annotation can be omitted and Spring will use that constructor and inject all necessary dependencies
    private EmployeeRepository employeeRepository;

    private EmployeeServiceImpl(EmployeeRepository employeeRepository){
        super();
        this.employeeRepository=employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> empOptional = this.employeeRepository.findById(id);  //Optional is java 8 feature ==> import java.util.Optional
        return empOptional.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //check whether employee is present or not in db
        Employee existingEmployee = this.employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id", id));
        //update the values
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        //save the updation
        this.employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        //what if id is not exist in db.
        this.employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id) );
        this.employeeRepository.deleteById(id);
    }
}
