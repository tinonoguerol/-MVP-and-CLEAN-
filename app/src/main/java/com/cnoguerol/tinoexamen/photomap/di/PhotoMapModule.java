package com.cnoguerol.tinoexamen.photomap.di;

import com.cnoguerol.tinoexamen.domain.FirebaseApi;
import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.photomap.PhotoMapInteractor;
import com.cnoguerol.tinoexamen.photomap.PhotoMapInteractorImpl;
import com.cnoguerol.tinoexamen.photomap.PhotoMapPresenter;
import com.cnoguerol.tinoexamen.photomap.PhotoMapPresenterImpl;
import com.cnoguerol.tinoexamen.photomap.PhotoMapRepository;
import com.cnoguerol.tinoexamen.photomap.PhotoMapRepositoryImpl;
import com.cnoguerol.tinoexamen.photomap.ui.PhotoMapView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cnoguerol.
 */
@Module
public class PhotoMapModule {
    private PhotoMapView view;

    public PhotoMapModule(PhotoMapView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    PhotoMapView providesPhotoMapView(){
        return this.view;
    }

    @Provides
    @Singleton
    PhotoMapPresenter providesPhotoMapPresenter(EventBus eventBus, PhotoMapView view, PhotoMapInteractor interactor){
        return new PhotoMapPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    PhotoMapInteractor providesPhotoMapInteractor(PhotoMapRepository repository){
        return new PhotoMapInteractorImpl(repository);
    }

    @Provides
    @Singleton
    PhotoMapRepository providesPhotoMapRepository(EventBus eventBus, FirebaseApi firebaseAPI){
        return new PhotoMapRepositoryImpl(eventBus, firebaseAPI);
    }
}


