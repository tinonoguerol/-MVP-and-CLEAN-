package com.cnoguerol.tinoexamen.domain;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by cnoguerol.
 */
public interface FirebaseEventListenerCallback {
    void onChildAdded(DataSnapshot dataSnapshot);
    void onChildRemoved(DataSnapshot dataSnapshot);
    //   void onCancelled(FirebaseError error); Anterior
    void onCancelled(DatabaseError error);
}
