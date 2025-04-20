package com.springboot.rest.entity.myspringappdb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springboot.rest.constraint.validations.EmployeeJoiningYearConstraint;
import com.springboot.rest.constraint.validations.ValidAgeConstraint;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PersistenceProperty;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	public static final long serialVersionUID = 7118515707408403559L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	public int empId;

	@Column(name = "emp_name")
	public String empName;

	@Column(name = "emp_age")
	public int empAge;

	@Column(name = "emp_gender")
	public String empGender;

	@Column(name = "emp_department")
	public String empDepartment;

	@Column(name = "emp_year_of_joining")
	public int empYearOfJoining;

	@Column(name = "emp_average_salary")
	public int empAverageSalary;

	@ManyToMany(cascade =  {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinTable(
        name = "employee_project", 
        joinColumns = { @JoinColumn(name = "employee_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    public List<Project> empProjects = new ArrayList();
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	public List<Skill> empSkills = new ArrayList();
	
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	public Address empAddress = new Address();
	
	public void addSkill(Skill skill) {
		this.empSkills.add(skill);
		skill.setEmployee(this);
	}
	
	public void addAddress(Address address) {
		this.empAddress = address;
		address.setEmployee(this);
	}

}