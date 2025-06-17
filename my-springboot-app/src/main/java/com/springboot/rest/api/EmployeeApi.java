package com.springboot.rest.api;

import java.util.List;

import com.springboot.rest.model.StringResponseModel;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.rest.model.EmployeeModel;
import com.springboot.rest.model.MaleAndFemaleCountByDepartment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "employee-svc")
public interface EmployeeApi {

	@Operation(summary = "This endpoint is used to fetch all the employee details.")
	ResponseEntity<List<EmployeeModel>> findAllEmployees(Pageable pageable);

	@Operation(summary = "This endpoint is used to fetch the employee details by employee Id.")
	ResponseEntity<EmployeeModel> getEmployeeById(int id);

	@Operation(summary = "This endpoint is used to save new employee details.")
	ResponseEntity<EmployeeModel> addEmployee(JsonNode body);

	@Operation(summary = "This endpoint is used to delete the employee details by employee Id.")
    ResponseEntity<StringResponseModel> deleteEmployeeById(int id);

	@Operation(summary = "This endpoint is used to fetch the male and female count.")
	ResponseEntity<List<MaleAndFemaleCountByDepartment>> findMaleAndFemaleCount();

	@Operation(summary = "This endpoint is used to fetch total employee count.")
	ResponseEntity<Long> getTotalCount();

	@Operation(summary = "This endpoint is used to update the employee details.")
	ResponseEntity<EmployeeModel> updateEmployee(@Valid EmployeeModel e);

}
