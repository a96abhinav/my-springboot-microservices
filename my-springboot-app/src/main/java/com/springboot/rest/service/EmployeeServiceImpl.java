package com.springboot.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.rest.config.logging.Loggable;
import com.springboot.rest.entity.myspringappdb.Address;
import com.springboot.rest.entity.myspringappdb.Employee;
import com.springboot.rest.entity.myspringappdb.Project;
import com.springboot.rest.entity.myspringappdb.Skill;
import com.springboot.rest.model.EmployeeAddressModel;
import com.springboot.rest.model.EmployeeModel;
import com.springboot.rest.model.EmployeeProjectModel;
import com.springboot.rest.model.EmployeeSkillModel;
import com.springboot.rest.model.MaleAndFemaleCountByDepartment;
import com.springboot.rest.repository.myspringappdb.EmployeeCustomRepository;
import com.springboot.rest.repository.myspringappdb.EmployeeJpaRepository;

@Service
@Loggable
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeCustomRepository employeeDao;

	@Autowired
	EmployeeJpaRepository employeeJpaRepository;

	@Override
	// @Cacheable(value = "employee", key = "#root.methodName", unless =
	// "#result==null")
	public List<EmployeeModel> findAll(Pageable pageable) {
		
		//Sort sort = Sort.by(Sort.Direction.ASC, "empName");
		//Pageable pageable = PageRequest.of(0, 5, sort);
		Page<Employee> pageableEmployees = employeeJpaRepository.findAll(pageable);

		List<Employee> employees = pageableEmployees.getContent();
		List<EmployeeModel> employeeResponse = new ArrayList<EmployeeModel>();

		for (Employee emp : employees) {
			List<EmployeeProjectModel> projects = new ArrayList<EmployeeProjectModel>();
			List<EmployeeSkillModel> skills = new ArrayList<EmployeeSkillModel>();
			emp.getEmpProjects().forEach(project -> {
				projects.add(EmployeeProjectModel.builder().projectId(project.getProjectId()).title(project.getTitle())
						.build());
			});

			emp.getEmpSkills().forEach(skill -> {
				skills.add(EmployeeSkillModel.builder().id(skill.getId()).skillName(skill.getSkillName()).build());
			});

			EmployeeAddressModel address = emp.getEmpAddress()!= null?EmployeeAddressModel.builder().id(emp.getEmpAddress().getId())
					.street(emp.getEmpAddress().getStreet()).city(emp.getEmpAddress().getCity())
					.state(emp.getEmpAddress().getState()).zipCode(emp.getEmpAddress().getZipCode()).build():null;

			employeeResponse
					.add(EmployeeModel.builder().empId(emp.getEmpId()).empName(emp.getEmpName()).empAge(emp.getEmpAge())
							.empAverageSalary(emp.getEmpAverageSalary()).empDepartment(emp.getEmpDepartment())
							.empGender(emp.getEmpGender()).empYearOfJoining(emp.getEmpYearOfJoining())
							.empProjects(projects).empSkills(skills).empAddress(address).build());
		}

		return employeeResponse;
	}

	@Override
	public EmployeeModel getEmployeeById(int id) {

		Employee employee = employeeJpaRepository.findById(id).get();

		List<EmployeeProjectModel> projects = new ArrayList<EmployeeProjectModel>();
		List<EmployeeSkillModel> skills = new ArrayList<EmployeeSkillModel>();
		employee.getEmpProjects().forEach(project -> {
			projects.add(
					EmployeeProjectModel.builder().projectId(project.getProjectId()).title(project.getTitle()).build());
		});

		employee.getEmpSkills().forEach(skill -> {
			skills.add(EmployeeSkillModel.builder().id(skill.getId()).skillName(skill.getSkillName()).build());
		});

		EmployeeAddressModel address = EmployeeAddressModel.builder().id(employee.getEmpAddress().getId())
				.street(employee.getEmpAddress().getStreet()).city(employee.getEmpAddress().getCity())
				.state(employee.getEmpAddress().getState()).zipCode(employee.getEmpAddress().getZipCode()).build();

		EmployeeModel employeeResponse = EmployeeModel.builder().empId(employee.getEmpId())
				.empName(employee.getEmpName()).empAge(employee.getEmpAge())
				.empAverageSalary(employee.getEmpAverageSalary()).empDepartment(employee.getEmpDepartment())
				.empGender(employee.getEmpGender()).empYearOfJoining(employee.getEmpYearOfJoining())
				.empProjects(projects).empSkills(skills).empAddress(address).build();

		return employeeResponse;
	}

	@Override
	@Transactional
	public EmployeeModel saveEmployee(EmployeeModel employeeRequest) {
		
		Employee employee = new Employee();

		if(null != employeeRequest.getEmpProjects()){
		employeeRequest.getEmpProjects().forEach(project -> {
			Project projct = new Project();
			projct.setProjectId(project.getProjectId());
			projct.setTitle(project.getTitle());
			employee.getEmpProjects().add(projct);
		});}

		if(null != employeeRequest.getEmpSkills()){
		employeeRequest.getEmpSkills().forEach(skill -> {
			Skill skl = new Skill();
			skl.setId(skill.getId());
			skl.setSkillName(skill.getSkillName());
			employee.addSkill(skl);
		});}

		if(null != employeeRequest.getEmpAddress()){
		Address address = new Address();
		address.setId(employeeRequest.getEmpAddress().getId());
		address.setStreet(employeeRequest.getEmpAddress().getStreet());
		address.setCity(employeeRequest.getEmpAddress().getCity());
		address.setState(employeeRequest.getEmpAddress().getState());
		address.setZipCode(employeeRequest.getEmpAddress().getZipCode());
		employee.addAddress(address);}

		employee.setEmpId(employeeRequest.getEmpId());
		employee.setEmpAge(employeeRequest.getEmpAge());
		employee.setEmpGender(employeeRequest.getEmpGender());
		employee.setEmpName(employeeRequest.getEmpName());
		employee.setEmpDepartment(employeeRequest.getEmpDepartment());
		employee.setEmpYearOfJoining(employeeRequest.getEmpYearOfJoining());
		employee.setEmpAverageSalary(employeeRequest.getEmpAverageSalary());

		employeeDao.saveEmployee(employee);

		
		return this.getEmployeeById(employee.getEmpId());
	}

	@Override
	@Transactional
	public int delete(int id) {

		employeeJpaRepository.deleteById(id);
		return id;
	}

	@Override
	public List<MaleAndFemaleCountByDepartment> findMaleAndFemaleCountByDepartment() {
		return employeeDao.findMaleAndFemaleCountByDepartment();
	}

	@Override
	public Long getTotalCount() {

		Long count = employeeJpaRepository.employeeCount();
		return count;

	}
}
