package com.springboot.rest.repository.myspringappdb;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.entity.myspringappdb.Project;

public interface ProjectJpaRepository extends JpaRepository<Project, Integer> {

}
