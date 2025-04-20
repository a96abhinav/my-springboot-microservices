package com.springboot.rest.service;

import com.springboot.rest.entity.myspringappdb.MediaLibrary;
import com.springboot.rest.model.DeleteResponseModel;
import com.springboot.rest.model.MediaLibraryModel;
import com.springboot.rest.repository.myspringappdb.MediaLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaLibraryService {

    private final MediaLibraryRepository repository;

    public MediaLibraryModel getByMediaId(Integer mediaId){
        MediaLibrary ml = repository.getById(mediaId);

        MediaLibraryModel mlm = new MediaLibraryModel(ml.getMediaId(),ml.getImage(),ml.getTitle(),ml.getDescription(),
                ml.getReleaseYear(),ml.getStartYear(),ml.getEndYear(),ml.getGenre(),ml.getType());
        return mlm;
    }
    
    public MediaLibraryModel saveMediaLibrary(MediaLibraryModel mlm){
        MediaLibrary ml = new MediaLibrary(mlm.getMediaId(),mlm.getImage(),mlm.getTitle(),mlm.getDescription(),
                mlm.getReleaseYear(),mlm.getStartYear(),mlm.getEndYear(),mlm.getGenre(),mlm.getType());
        repository.save(ml);
        return this.getByMediaId(ml.getMediaId());
    }

    public List<MediaLibraryModel> findAllMediaLibrary(){
        List<MediaLibrary> mlList = repository.findAll();

        List<MediaLibraryModel> mlmList = new ArrayList<>();
        
        mlList.stream().forEach(media->{
            MediaLibraryModel mlm = new MediaLibraryModel(media.getMediaId(),media.getImage(),media.getTitle(),media.getDescription(),
                    media.getReleaseYear(),media.getStartYear(),media.getEndYear(),media.getGenre(),media.getType());
            mlmList.add(mlm);
        });

        return mlmList;
    }

    public DeleteResponseModel deleteByMediaId(Integer mediaId){
        repository.deleteById(mediaId);
        return new DeleteResponseModel("Media with mediaId - "+mediaId+" successfully deleted.");
    }
}
