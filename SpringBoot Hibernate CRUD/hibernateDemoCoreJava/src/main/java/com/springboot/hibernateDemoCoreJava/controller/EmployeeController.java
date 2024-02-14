package com.springboot.hibernateDemoCoreJava.controller;

import com.springboot.hibernateDemoCoreJava.model.Employee;
import com.springboot.hibernateDemoCoreJava.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // = @Controller + @ResponseBody
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        System.out.println("*****************************************EmployeeController::saveEmployee********************************************");
        return new ResponseEntity<Employee>(this.employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        System.out.println("*****************************************EmployeeController::getAllEmployees********************************************");
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("{id}")  //url template variable
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long empId) {
        System.out.println("*****************************************EmployeeController::getEmployeeById********************************************");
        return new ResponseEntity<Employee>(this.employeeService.getEmployeeById(empId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long empId, @RequestBody Employee employee) {
        System.out.println("*****************************************EmployeeController::updateEmployee********************************************");
        return new ResponseEntity<Employee>(this.employeeService.updateEmployee(employee ,empId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long empId) {
        this.employeeService.deleteEmployee(empId);
        return new ResponseEntity<String>("Employee deleted successfully...", HttpStatus.OK);
    }

}
