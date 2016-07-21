package com.cnoguerol.tinoexamen.domain;

/**
 * Created by cnoguerol.
 */
public interface StorageActionListenerCallback {
    void onLoadSuccess();
    void onLoadFailed(String getErrorCode);

}
