package com.cnoguerol.tinoexamen.photomap;

import com.cnoguerol.tinoexamen.domain.FirebaseEventListenerCallback;
import com.cnoguerol.tinoexamen.domain.FirebaseApi;
import com.cnoguerol.tinoexamen.entities.Photo;
import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.photomap.events.PhotoMapEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by cnoguerol.
 */
public class PhotoMapRepositoryImpl implements PhotoMapRepository {
    private EventBus eventBus;
    private FirebaseApi firebaseAPI;

    public PhotoMapRepositoryImpl(EventBus eventBus, FirebaseApi firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());

                String email = firebaseAPI.getAuthEmail();
                boolean publishedByMe = photo.getEmail().equals(email);
                photo.setPublishedByMe(publishedByMe);
                post(PhotoMapEvent.READ_EVENT, photo);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());

                post(PhotoMapEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                post(PhotoMapEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        firebaseAPI.unsubscribe();
    }


    private void post(int type, Photo photo){
        post(type, null, photo);
    }

    private void post(int type, String error){
        post(type, error, null);
    }

    private void post(int type, String error, Photo photo){
        PhotoMapEvent event = new PhotoMapEvent();
        event.setType(type);
        event.setError(error);
        event.setPhoto(photo);
        eventBus.post(event);
    }
}

