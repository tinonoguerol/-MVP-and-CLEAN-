package com.cnoguerol.tinoexamen.photolist.ui;

import com.cnoguerol.tinoexamen.entities.Photo;

/**
 * Created by cnoguerol.
 */
public interface PhotoListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotosError(String error);
}
