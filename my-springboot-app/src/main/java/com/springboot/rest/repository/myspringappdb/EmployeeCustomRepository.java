package com.springboot.rest.repository.myspringappdb;

import java.util.List;

import com.springboot.rest.entity.myspringappdb.Employee;
import com.springboot.rest.model.MaleAndFemaleCountByDepartment;

public interface EmployeeCustomRepository {

	public List<Employee> findAll();

	public Employee getEmployeeById(int id);

	public Employee saveEmployee(Employee e);

	public int delete(int id);

	public List<MaleAndFemaleCountByDepartment> findMaleAndFemaleCountByDepartment();

}
