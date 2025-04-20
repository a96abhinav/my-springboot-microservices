package com.springboot.rest.service;
// ImageService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest.entity.myspringappdb.Image;
import com.springboot.rest.repository.myspringappdb.ImageRepository;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(Image image) {
        imageRepository.save(image);
    }
    
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
