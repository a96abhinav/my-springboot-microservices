package com.springboot.rest.entity.myspringappdb;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Audited
public class Book {

    @Id
    private Integer id;

    private String name;

    private Integer pages;
}
