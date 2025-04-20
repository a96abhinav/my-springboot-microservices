package com.springboot.rest.controller;

import java.util.List;

import com.springboot.rest.model.DeleteResponseModel;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.rest.api.EmployeeApi;
import com.springboot.rest.constants.CommonConstants;
import com.springboot.rest.model.EmployeeModel;
import com.springboot.rest.model.MaleAndFemaleCountByDepartment;
import com.springboot.rest.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(CommonConstants.API_PATH + CommonConstants.EMPLOYEE_SVC_PATH)
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController implements EmployeeApi {

	private final EmployeeService employeeService;
	private final ObjectMapper mapper;

	@Override
	@GetMapping(path = "/employee", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<EmployeeModel>> findAllEmployees(Pageable pageable) {

		List<EmployeeModel> emp = employeeService.findAll(pageable);

		return ResponseEntity.ok(emp);
	}

	@Override
	@GetMapping(path = "/employee/{employee-id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable("employee-id") int id) {

		EmployeeModel emp = employeeService.getEmployeeById(id);

		return ResponseEntity.ok(emp);
	}

	@Override
	@PostMapping(path = "/employee", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<EmployeeModel> addEmployee(@RequestBody JsonNode body) {
		EmployeeModel e = mapper.convertValue(body, EmployeeModel.class);
		//JsonNode reqBody = mapper.convertValue(e, JsonNode.class);
		return ResponseEntity.ok(employeeService.saveEmployee(e));
	}

	@Override
	@PutMapping(path = "/employee", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<EmployeeModel> updateEmployee(@RequestBody @Valid EmployeeModel e) {

		return ResponseEntity.ok(employeeService.saveEmployee(e));
	}

	@Override
	@DeleteMapping(path = "/employee/{employee-id}")
	public ResponseEntity<DeleteResponseModel> deleteEmployeeById(@PathVariable("employee-id") int id) {

		EmployeeModel emp = employeeService.getEmployeeById(id);
		employeeService.delete(id);

		String response = "Employee " + emp.getEmpName() + " with Employee Id - " + emp.getEmpId()
				+ " is removed from organization.";

		DeleteResponseModel deleteResponse = new DeleteResponseModel(response);

		return ResponseEntity.ok(deleteResponse);
	}

	@Override
	@GetMapping(path = "/employee/male-and-female-count", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<MaleAndFemaleCountByDepartment>> findMaleAndFemaleCount() {

		return ResponseEntity.ok(employeeService.findMaleAndFemaleCountByDepartment());
	}

	@Override
	@GetMapping(path = "/employee/total-count", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Long> getTotalCount() {

		Long count = employeeService.getTotalCount();
		return ResponseEntity.ok(count);
	}
}
