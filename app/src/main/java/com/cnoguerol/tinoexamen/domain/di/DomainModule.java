package com.cnoguerol.tinoexamen.domain.di;

import android.content.Context;
import android.location.Geocoder;

import com.cnoguerol.tinoexamen.domain.FirebaseApi;
import com.cnoguerol.tinoexamen.domain.Util;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cnoguerol.
 */
@Module
public class DomainModule {
    @Provides
    @Singleton
    FirebaseApi providesMyFirebaseAPI(DatabaseReference dataReference) {
        return new FirebaseApi(dataReference);
    }

    @Provides
    @Singleton
    DatabaseReference providesFirebase() {
        return   FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    @Singleton
    Util providesUtil(Geocoder geocoder) {
        return new Util(geocoder);
    }

    @Provides
    @Singleton
    Geocoder providesGeocoder(Context context) {
        return new Geocoder(context);
    }

}
