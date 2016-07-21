package com.cnoguerol.tinoexamen.photolist.di;

import com.cnoguerol.tinoexamen.domain.FirebaseApi;
import com.cnoguerol.tinoexamen.domain.Util;
import com.cnoguerol.tinoexamen.entities.Photo;
import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.libs.base.ImageLoader;
import com.cnoguerol.tinoexamen.photolist.PhotoListInteractor;
import com.cnoguerol.tinoexamen.photolist.PhotoListInteractorImpl;
import com.cnoguerol.tinoexamen.photolist.PhotoListPresenter;
import com.cnoguerol.tinoexamen.photolist.PhotoListPresenterImpl;
import com.cnoguerol.tinoexamen.photolist.PhotoListRepository;
import com.cnoguerol.tinoexamen.photolist.PhotoListRepositoryImpl;
import com.cnoguerol.tinoexamen.photolist.ui.PhotoListView;
import com.cnoguerol.tinoexamen.photolist.ui.adapters.OnItemClickListener;
import com.cnoguerol.tinoexamen.photolist.ui.adapters.PhotoListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cnoguerol.
 */
@Module
public class PhotoListModule {
    private PhotoListView view;
    private OnItemClickListener onItemClickListener;

    public PhotoListModule(PhotoListView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    PhotoListView providesPhotoListView(){
        return this.view;
    }

    @Provides
    @Singleton
    PhotoListPresenter providesPhotoListPresenter(EventBus eventBus, PhotoListView view, PhotoListInteractor listInteractor){
        return new PhotoListPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides
    @Singleton
    PhotoListInteractor providesPhotoListInteractor(PhotoListRepository repository){
        return new PhotoListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    PhotoListRepository providesPhotoListRepository(EventBus eventBus, FirebaseApi firebase){
        return new PhotoListRepositoryImpl(eventBus, firebase);
    }

    @Provides
    @Singleton
    PhotoListAdapter providesPhotoListAdapter(Util util, List<Photo> photoList, ImageLoader imageLoader, OnItemClickListener onItemClickListener){
        return new PhotoListAdapter(util, photoList, imageLoader, onItemClickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    List<Photo> providesPhotoList(){
        return new ArrayList<Photo>();
    }

}
