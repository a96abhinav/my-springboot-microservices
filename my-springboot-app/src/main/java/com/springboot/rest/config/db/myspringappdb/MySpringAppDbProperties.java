package com.springboot.rest.config.db.myspringappdb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.myspringappdb")
public class MySpringAppDbProperties {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
}