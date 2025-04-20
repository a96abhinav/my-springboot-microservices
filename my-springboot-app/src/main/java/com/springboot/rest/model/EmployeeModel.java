package com.springboot.rest.model;

import java.util.List;

import com.springboot.rest.constraint.validations.EmployeeJoiningYearConstraint;
import com.springboot.rest.constraint.validations.ValidAgeConstraint;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

	@Schema(accessMode = AccessMode.READ_ONLY)
	private int empId;

	private String empName;

	@ValidAgeConstraint
	private int empAge;

	private String empGender;

	private String empDepartment;

	@EmployeeJoiningYearConstraint
	private int empYearOfJoining;

	private int empAverageSalary;

	private List<EmployeeProjectModel> empProjects;

	private List<EmployeeSkillModel> empSkills;

	private EmployeeAddressModel empAddress;

}