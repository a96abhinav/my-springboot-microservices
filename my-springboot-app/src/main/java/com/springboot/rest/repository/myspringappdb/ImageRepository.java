package com.springboot.rest.repository.myspringappdb;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.entity.myspringappdb.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
