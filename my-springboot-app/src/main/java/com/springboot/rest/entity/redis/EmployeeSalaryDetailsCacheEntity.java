package com.springboot.rest.entity.redis;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@RedisHash(value = "employeeSalaryDetails", timeToLive = 30)
@NoArgsConstructor
public class EmployeeSalaryDetailsCacheEntity {

	@org.springframework.data.annotation.Id
	private String id;
	
	private int empId;

	private String empName;

	private int empAverageSalary;
}
