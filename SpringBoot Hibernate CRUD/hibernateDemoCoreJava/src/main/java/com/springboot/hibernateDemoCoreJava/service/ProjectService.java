package com.springboot.hibernateDemoCoreJava.service;

import com.springboot.hibernateDemoCoreJava.model.Employee;
import com.springboot.hibernateDemoCoreJava.model.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    List<Employee> getEmployeesByProjectId(Long projectId);
    Project assignProject(Long projectId, Long employeeId);
}
