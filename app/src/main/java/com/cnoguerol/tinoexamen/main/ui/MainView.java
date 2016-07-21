package com.cnoguerol.tinoexamen.main.ui;

/**
 * Created by cnoguerol.
 */
public interface MainView {
    void onUploadInit();
    void onUploadComplete();
    void onUploadError(String error);
}
