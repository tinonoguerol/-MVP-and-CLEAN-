package com.cnoguerol.tinoexamen.photolist;

import com.cnoguerol.tinoexamen.entities.Photo;

/**
 * Created by cnoguerol.
 */
public interface PhotoListInteractor {
    void subscribe();
    void unsubscribe();
    void removePhoto(Photo photo);
}

