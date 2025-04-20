package com.springboot.rest.entity.myspringappdb;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "`user`")
public class User {

    @Column(updatable = false)
    private Long id;

    @Id
    private String username;

    private String password;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp(source = SourceType.DB)
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp(source = SourceType.DB)
    private Instant updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
}
