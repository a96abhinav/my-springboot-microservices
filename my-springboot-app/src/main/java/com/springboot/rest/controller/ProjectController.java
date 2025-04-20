package com.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.constants.CommonConstants;
import com.springboot.rest.model.ProjectModel;
import com.springboot.rest.service.ProjectService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "project-svc")
@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(CommonConstants.API_PATH+CommonConstants.PROJECT_SVC_PATH)
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@GetMapping("/projects")
	public ResponseEntity<List<ProjectModel>> findAllProjects(){
		return ResponseEntity.ok(projectService.findAllProjects());
	}
}
