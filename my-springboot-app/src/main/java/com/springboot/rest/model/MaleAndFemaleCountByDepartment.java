package com.springboot.rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class MaleAndFemaleCountByDepartment implements Serializable {

	private static final long serialVersionUID = 4415534769960962501L;
	
	private String department;

	private Long totalMaleCount;

	private Long totalFemaleCount;
}
