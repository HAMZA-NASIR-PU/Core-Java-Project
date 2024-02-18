package com.springboot.hibernateDemoCoreJava.repository;

import com.springboot.hibernateDemoCoreJava.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
