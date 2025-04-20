package com.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.constants.CommonConstants;
import com.springboot.rest.entity.redis.EmployeeSalaryDetailsCacheEntity;
import com.springboot.rest.service.RedisCacheService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "redis-cache-svc")
@RequestMapping(CommonConstants.API_PATH+CommonConstants.REDIS_CACHE_SVC_PATH)
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class RedisCacheController {

	@Autowired
	RedisCacheService cacheService;

	@PostMapping("/save")
	public ResponseEntity<String> saveEmployeeDetailsInCache(
			@RequestBody EmployeeSalaryDetailsCacheEntity salaryDetails) {
		return cacheService.saveEmployeeDetailsInCache(salaryDetails);
	}

	@GetMapping("/get")
	public ResponseEntity<List<EmployeeSalaryDetailsCacheEntity>> getAllEmployeeDetailsFromCache() {
		return cacheService.getAllEmployeeDetailsFromCache();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAllEmployeeDetailsFromCache() {
		cacheService.deleteAllEmployeeDetailsFromCache();
		return new ResponseEntity<String>("Deleted all employee details from Cache Successfully", HttpStatus.OK);
	}
}
