package com.springboot.rest.entity.myspringappdb;


import com.springboot.rest.enums.MediaLibraryGenre;
import com.springboot.rest.enums.MediaLibraryType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "media_library")
public class MediaLibrary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Integer mediaId;

    @Lob
    @Column(name = "image", nullable = false,columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(name = "start_year", nullable = false)
    private Integer startYear;

    @Column(name = "end_year", nullable = false)
    private Integer endYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private MediaLibraryGenre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MediaLibraryType type;
}
