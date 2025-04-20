package com.springboot.rest.repository.myspringappdb;

import com.springboot.rest.entity.myspringappdb.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
