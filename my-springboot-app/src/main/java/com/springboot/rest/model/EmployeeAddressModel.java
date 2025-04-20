package com.springboot.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAddressModel {

	//@JsonIgnore
	private int id;
	private String street;
	private String city;
	private String state;
	private String zipCode;
}
