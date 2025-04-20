package com.springboot.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectEmployeeModel {

	private int empId;
	private String empName;

}