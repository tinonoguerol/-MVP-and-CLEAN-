package com.cnoguerol.tinoexamen.photolist;

import com.cnoguerol.tinoexamen.entities.Photo;

/**
 * Created by cnoguerol.
 */
public class PhotoListInteractorImpl implements PhotoListInteractor {
    private PhotoListRepository repository;

    public PhotoListInteractorImpl(PhotoListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void removePhoto(Photo photo) {
        repository.removePhoto(photo);
    }
}

