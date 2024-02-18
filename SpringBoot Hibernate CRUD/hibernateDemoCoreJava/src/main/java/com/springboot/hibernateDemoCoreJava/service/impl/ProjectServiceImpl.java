package com.springboot.hibernateDemoCoreJava.service.impl;

import com.springboot.hibernateDemoCoreJava.model.Employee;
import com.springboot.hibernateDemoCoreJava.model.Project;
import com.springboot.hibernateDemoCoreJava.repository.EmployeeRepository;
import com.springboot.hibernateDemoCoreJava.repository.ProjectRepository;
import com.springboot.hibernateDemoCoreJava.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    private EmployeeRepository employeeRepository;
    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        super();
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Project saveProject(Project project) {
        return this.projectRepository.save(project);
    }

    @Override
    public List<Employee> getEmployeesByProjectId(Long projectId) {
        return this.employeeRepository.findByProjectId(projectId);
    }

    @Override
    public Project assignProject(Long projectId, Long employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).get();
        Project project = this.projectRepository.findById(projectId).get();
        employee.setProject(project);
        this.employeeRepository.save(employee);
        return project;
    }
}
