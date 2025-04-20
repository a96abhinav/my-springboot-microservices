package com.springboot.rest.repository.myspringappdb;

import com.springboot.rest.entity.myspringappdb.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
