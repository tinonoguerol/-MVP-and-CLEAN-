package com.cnoguerol.tinoexamen.photolist;

import com.cnoguerol.tinoexamen.entities.Photo;
import com.cnoguerol.tinoexamen.photolist.events.PhotoListEvent;

/**
 * Created by cnoguerol.
 */
public interface PhotoListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removePhoto(Photo photo);
    void onEventMainThread(PhotoListEvent event);
}
