package com.springboot.hibernateDemoCoreJava.controller;

import com.springboot.hibernateDemoCoreJava.model.Employee;
import com.springboot.hibernateDemoCoreJava.model.Project;
import com.springboot.hibernateDemoCoreJava.service.EmployeeService;
import com.springboot.hibernateDemoCoreJava.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // = @Controller + @ResponseBody
@RequestMapping("/api/projects")
public class ProjectController {
    private EmployeeService employeeService;
    private ProjectService projectService;

    public ProjectController(EmployeeService employeeService, ProjectService projectService) {
        super();
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @PostMapping("/createProject")
    public Project createProject(@RequestBody Project project) {
        return this.projectService.saveProject(project);
    }

    @GetMapping("/assignProject/{employeeId}/{projectId}")
    public Project assignProject(@PathVariable("projectId") Long projectId, @PathVariable("employeeId") Long employeeId) {
        return this.projectService.assignProject(projectId, employeeId);
    }

    @GetMapping("/getEmployeesByProjectId/{projectId}")
    public List<Employee> getEmployeesByProjectId(@PathVariable("projectId") Long projectId) {
        return this.projectService.getEmployeesByProjectId(projectId);
    }
}
