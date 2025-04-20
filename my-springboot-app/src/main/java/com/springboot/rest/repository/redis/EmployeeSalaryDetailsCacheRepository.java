package com.springboot.rest.repository.redis;

import org.springframework.data.repository.CrudRepository;

import com.springboot.rest.entity.redis.EmployeeSalaryDetailsCacheEntity;

public interface EmployeeSalaryDetailsCacheRepository extends CrudRepository<EmployeeSalaryDetailsCacheEntity, String> {
	
}
