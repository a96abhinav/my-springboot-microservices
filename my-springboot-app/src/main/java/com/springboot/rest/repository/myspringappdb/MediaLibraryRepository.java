package com.springboot.rest.repository.myspringappdb;

import com.springboot.rest.entity.myspringappdb.MediaLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MediaLibraryRepository extends JpaRepository<MediaLibrary, Integer> {
}
