package com.cnoguerol.tinoexamen.main;

import android.location.Location;

import com.cnoguerol.tinoexamen.domain.FirebaseApi;
import com.cnoguerol.tinoexamen.domain.StorageActionListenerCallback;
import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.main.events.MainEvent;

/**
 * Created by cnoguerol.
 */
public class MainRepositoryImpl implements MainRepository{
    private EventBus eventBus;
    private FirebaseApi dataReference;


    public MainRepositoryImpl(EventBus eventBus, FirebaseApi dataReference) {
        this.eventBus = eventBus;
        this.dataReference= dataReference;

    }
    @Override
    public void logout() {
        dataReference.logout();
    }

    @Override
    public void uploadPhoto(Location location, String path) {

        String url=path;
        double lat = 0;
        double log = 0;


        if(location != null){
            lat = (location.getLatitude());
            log = (location.getLongitude());
        }

        post(MainEvent.UPLOAD_INIT);
        dataReference.subirFoto(url, lat , log, new StorageActionListenerCallback() {
            @Override
            public void onLoadSuccess() {

                post(MainEvent.UPLOAD_COMPLETE);
            }
            @Override
            public void onLoadFailed(String error) {
                post(MainEvent.UPLOAD_ERROR, error);
            }
        });
    }


    private void post(int type){
        post(type, null);
    }

    private void post(int type, String error){
        MainEvent event = new MainEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}
