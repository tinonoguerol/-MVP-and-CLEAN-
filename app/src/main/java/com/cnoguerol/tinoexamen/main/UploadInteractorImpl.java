package com.cnoguerol.tinoexamen.main;

import android.location.Location;

/**
 * Created by cnoguerol.
 */
public class UploadInteractorImpl implements UploadInteractor {
    private MainRepository repository;

    public UploadInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Location location, String path) {
        repository.uploadPhoto(location, path);
    }

}

