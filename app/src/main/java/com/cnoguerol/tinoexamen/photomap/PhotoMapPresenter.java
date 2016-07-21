package com.cnoguerol.tinoexamen.photomap;

import com.cnoguerol.tinoexamen.photomap.events.PhotoMapEvent;

/**
 * Created by cnoguerol.
 */
public interface PhotoMapPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void onEventMainThread(PhotoMapEvent event);
}
