package com.cnoguerol.tinoexamen.photomap.ui;

import com.cnoguerol.tinoexamen.entities.Photo;

/**
 * Created by cnoguerol.
 */
public interface PhotoMapView {
    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotosError(String error);
}
