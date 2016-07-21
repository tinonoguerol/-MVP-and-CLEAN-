package com.cnoguerol.tinoexamen.main;

import android.location.Location;

/**
 * Created by cnoguerol.
 */
public interface MainRepository {
    void logout();
    void uploadPhoto(Location location, String path);
}
