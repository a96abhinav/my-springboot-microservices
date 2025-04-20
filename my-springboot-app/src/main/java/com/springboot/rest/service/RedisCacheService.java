package com.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.rest.entity.redis.EmployeeSalaryDetailsCacheEntity;
import com.springboot.rest.repository.redis.EmployeeSalaryDetailsCacheRepository;

@Service
public class RedisCacheService {

	@Autowired
	public EmployeeSalaryDetailsCacheRepository cacheRepository;

	public ResponseEntity<String> saveEmployeeDetailsInCache(
			@RequestBody EmployeeSalaryDetailsCacheEntity salaryDetails) {

		EmployeeSalaryDetailsCacheEntity response = cacheRepository.save(salaryDetails);

		return new ResponseEntity<String>("Salary Details Saved in Cache Successfully", HttpStatus.OK);
	}

	public ResponseEntity<List<EmployeeSalaryDetailsCacheEntity>> getAllEmployeeDetailsFromCache() {
		List<EmployeeSalaryDetailsCacheEntity> employeeSalaryDetailsCacheEntities = (List<EmployeeSalaryDetailsCacheEntity>) cacheRepository
				.findAll();
		return new ResponseEntity<List<EmployeeSalaryDetailsCacheEntity>>(employeeSalaryDetailsCacheEntities,
				HttpStatus.OK);
	}

	public void deleteAllEmployeeDetailsFromCache() {
		cacheRepository.deleteAll();
	}
}
