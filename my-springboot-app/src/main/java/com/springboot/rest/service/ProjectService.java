package com.springboot.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest.entity.myspringappdb.Project;
import com.springboot.rest.model.ProjectEmployeeModel;
import com.springboot.rest.model.ProjectModel;
import com.springboot.rest.repository.myspringappdb.ProjectJpaRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectJpaRepository projectJpaRepository;

	public List<ProjectModel> findAllProjects() {

		List<Project> projects = projectJpaRepository.findAll();

		List<ProjectModel> projectResponse = new ArrayList<ProjectModel>();

		for (Project p : projects) {

			List<ProjectEmployeeModel> employees = new ArrayList<ProjectEmployeeModel>();
			p.getEmployees().forEach(employee -> {
				employees.add(ProjectEmployeeModel.builder().empId(employee.getEmpId()).empName(employee.getEmpName())
						.build());
			});
			projectResponse.add(ProjectModel.builder().projectId(p.getProjectId()).title(p.getTitle())
					.employees(employees).build());
		}

		return projectResponse;
	}
}
