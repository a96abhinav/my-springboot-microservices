package com.springboot.rest.controller;

import com.springboot.rest.constants.CommonConstants;
import com.springboot.rest.enums.MediaLibraryGenre;
import com.springboot.rest.enums.MediaLibraryType;
import com.springboot.rest.model.StringResponseModel;
import com.springboot.rest.model.MediaLibraryModel;
import com.springboot.rest.service.MediaLibraryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = CommonConstants.MEDIA_LIBRARY_SVC_PATH)
@RequiredArgsConstructor
@RestController
@RequestMapping(CommonConstants.API_PATH + CommonConstants.MEDIA_LIBRARY_SVC_PATH)
@PreAuthorize("hasRole('ROLE_USER')")
@CrossOrigin(origins = "http://localhost:4200/")
public class MediaLibraryController {

    private final MediaLibraryService service;

    @GetMapping(path = "/findAllMediaLibrary", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<MediaLibraryModel>> findAllMediaLibrary() {

        List<MediaLibraryModel> mlmList = service.findAllMediaLibrary();

        return ResponseEntity.ok(mlmList);
    }

    @GetMapping(path = "/getByMediaId/{mediaId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MediaLibraryModel> getByMediaId(@PathVariable Integer mediaId) {

        MediaLibraryModel mlm = service.getByMediaId(mediaId);

        return ResponseEntity.ok(mlm);
    }

    @PostMapping(path = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MediaLibraryModel> saveNewMedia(@RequestParam("file") MultipartFile file,
                                                         @RequestParam("title") String title,
                                                         @RequestParam("description") String description,
                                                         @RequestParam("releaseYear") Integer releaseYear,
                                                         @RequestParam("startYear") Integer startYear,
                                                         @RequestParam("endYear") Integer endYear,
                                                         @RequestParam("genre") MediaLibraryGenre genre,
                                                         @RequestParam("type") MediaLibraryType type) throws IOException {

        MediaLibraryModel media = new MediaLibraryModel();
        media.setImage(file.getBytes());
        media.setTitle(title);
        media.setDescription(description);
        media.setReleaseYear(releaseYear);
        media.setStartYear(startYear);
        media.setEndYear(endYear);
        media.setGenre(genre);
        media.setType(type);

        return ResponseEntity.ok(service.saveMediaLibrary(media));
    }

    @PutMapping(path = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MediaLibraryModel> updateExistingMedia(@RequestParam("file") MultipartFile file,
                                                                 @RequestParam("mediaId") Integer mediaId,
                                                         @RequestParam("title") String title,
                                                         @RequestParam("description") String description,
                                                         @RequestParam("releaseYear") Integer releaseYear,
                                                         @RequestParam("startYear") Integer startYear,
                                                         @RequestParam("endYear") Integer endYear,
                                                         @RequestParam("genre") MediaLibraryGenre genre,
                                                         @RequestParam("type") MediaLibraryType type) throws IOException {

        MediaLibraryModel media = new MediaLibraryModel();
        media.setMediaId(mediaId);
        media.setImage(file.getBytes());
        media.setTitle(title);
        media.setDescription(description);
        media.setReleaseYear(releaseYear);
        media.setStartYear(startYear);
        media.setEndYear(endYear);
        media.setGenre(genre);
        media.setType(type);

        return ResponseEntity.ok(service.saveMediaLibrary(media));
    }

    @DeleteMapping(path = "/deleteByMediaId/{mediaId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StringResponseModel> deleteByMediaId(@PathVariable Integer mediaId){
        return ResponseEntity.ok(service.deleteByMediaId(mediaId));
    }
}
