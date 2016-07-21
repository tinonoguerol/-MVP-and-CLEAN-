package com.cnoguerol.tinoexamen.main;

import android.location.Location;

import com.cnoguerol.tinoexamen.main.events.MainEvent;

/**
 * Created by cnoguerol.
 */
public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void logout();
    void uploadPhoto(Location location, String path);
    void onEventMainThread(MainEvent event);
}
