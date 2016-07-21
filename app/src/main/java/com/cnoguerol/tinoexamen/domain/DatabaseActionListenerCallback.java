package com.cnoguerol.tinoexamen.domain;

import com.google.firebase.database.DatabaseError;

/**
 * Created by cnoguerol.
 */
public interface DatabaseActionListenerCallback {
    void onSuccess();
    void onError(DatabaseError error);
}
