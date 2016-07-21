package com.cnoguerol.tinoexamen.domain;

/**
 * Created by cnoguerol.
 */
public interface FirebaseActionListenerCallback {
    void onAuthSuccess();
    void onAuthFailed(String getErrorCode);

}
