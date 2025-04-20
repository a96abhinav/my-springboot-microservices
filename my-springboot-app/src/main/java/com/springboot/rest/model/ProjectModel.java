package com.springboot.rest.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectModel {

	public int projectId;
	public String title;
	public List<ProjectEmployeeModel> employees;
}