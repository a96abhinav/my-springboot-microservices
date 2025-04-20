package com.springboot.rest.repository.myspringappdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.springboot.rest.entity.myspringappdb.Employee;

@RepositoryRestResource()
public interface EmployeeSpringDataRestRepository extends JpaRepository<Employee,Integer> {

}