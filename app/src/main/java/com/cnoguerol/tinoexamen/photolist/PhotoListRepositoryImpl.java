package com.cnoguerol.tinoexamen.photolist;

import com.cnoguerol.tinoexamen.domain.DatabaseActionListenerCallback;
import com.cnoguerol.tinoexamen.domain.FirebaseEventListenerCallback;
import com.cnoguerol.tinoexamen.domain.FirebaseApi;
import com.cnoguerol.tinoexamen.entities.Photo;
import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.photolist.events.PhotoListEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by cnoguerol.
 */
public class PhotoListRepositoryImpl implements PhotoListRepository {
    private EventBus eventBus;
    private FirebaseApi firebaseAPI;

    public PhotoListRepositoryImpl(EventBus eventBus, FirebaseApi firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseAPI.checkForData(new DatabaseActionListenerCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(DatabaseError error) {
                if(error != null){
                    post(PhotoListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(PhotoListEvent.READ_EVENT, "");
                }
            }
        });

        firebaseAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());

                String email = firebaseAPI.getAuthEmail();
                boolean publishedByMe = photo.getEmail().equals(email);
                photo.setPublishedByMe(publishedByMe);
                post(PhotoListEvent.READ_EVENT, photo);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());

                post(PhotoListEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                post(PhotoListEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        firebaseAPI.unsubscribe();
    }

    @Override
    public void removePhoto(final Photo photo) {
        firebaseAPI.remove(photo, new DatabaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(PhotoListEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onError(DatabaseError error) {
                post(PhotoListEvent.DELETE_EVENT, error.getMessage());
            }
        });
    }

    private void post(int type, Photo photo){
        post(type, null, photo);
    }

    private void post(int type, String error){
        post(type, error, null);
    }

    private void post(int type, String error, Photo photo){
        PhotoListEvent event = new PhotoListEvent();
        event.setType(type);
        event.setError(error);
        event.setPhoto(photo);
        eventBus.post(event);
    }
}
