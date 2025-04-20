package com.springboot.rest.model;


import com.springboot.rest.enums.MediaLibraryGenre;
import com.springboot.rest.enums.MediaLibraryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaLibraryModel implements Serializable{

    private Integer mediaId;

    private byte[] image;

    private String title;

    private String description;

    private Integer releaseYear;

    private Integer startYear;

    private Integer endYear;

    private MediaLibraryGenre genre;

    private MediaLibraryType type;
}
