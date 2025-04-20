package com.springboot.rest.repository.myspringappdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.rest.entity.myspringappdb.Employee;

import jakarta.transaction.Transactional;

@Transactional
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {

	@Query("select count(*) from Employee")
	public Long employeeCount();

	@Query("select distinct empDepartment from Employee")
	public List<String> findDistinctEmpDepartments();

}