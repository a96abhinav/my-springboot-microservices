package com.springboot.rest.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;

@Data
public class UserModel {

    private String username;

    private String password;

    private String email;

    private String mobileNumber;

    private Set<RoleModel> roles;
}
