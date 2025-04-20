package com.springboot.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvData {

	private Long id;

	private byte[] csvContent;

}