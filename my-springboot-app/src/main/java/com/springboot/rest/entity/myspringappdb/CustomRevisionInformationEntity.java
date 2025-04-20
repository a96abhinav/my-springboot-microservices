package com.springboot.rest.entity.myspringappdb;

import com.springboot.rest.entity.customlistener.CustomRevisionListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.time.LocalDateTime;

@Entity
@RevisionEntity(CustomRevisionListener.class) // Link to a custom listener
@Getter
@Setter
@Table(name = "REVINFO")
public class CustomRevisionInformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int rev;

    @RevisionTimestamp
    private long revtstmp;

    private String createdBy;

    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime createdAt;
}
