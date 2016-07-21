package com.cnoguerol.tinoexamen.main;

import android.location.Location;

/**
 * Created by cnoguerol.
 */
public interface UploadInteractor {
    void execute(Location location, String path);
}
