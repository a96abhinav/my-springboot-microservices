package com.springboot.rest.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.springboot.rest.model.EmployeeModel;
import com.springboot.rest.model.MaleAndFemaleCountByDepartment;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
	public List<EmployeeModel> findAll(Pageable pageable);

	public EmployeeModel getEmployeeById(int id);

	public EmployeeModel saveEmployee(EmployeeModel e);

	public int delete(int id);

	public List<MaleAndFemaleCountByDepartment> findMaleAndFemaleCountByDepartment();

	public Long getTotalCount();

	public ResponseEntity testCircuitBreakerPattern(String productId);
}
