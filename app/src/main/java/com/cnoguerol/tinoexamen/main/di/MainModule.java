package com.cnoguerol.tinoexamen.main.di;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.cnoguerol.tinoexamen.domain.FirebaseApi;
import com.cnoguerol.tinoexamen.libs.base.EventBus;
import com.cnoguerol.tinoexamen.main.MainPresenter;
import com.cnoguerol.tinoexamen.main.MainPresenterImpl;
import com.cnoguerol.tinoexamen.main.MainRepository;
import com.cnoguerol.tinoexamen.main.MainRepositoryImpl;
import com.cnoguerol.tinoexamen.main.SessionInteractor;
import com.cnoguerol.tinoexamen.main.SessionInteractorImpl;
import com.cnoguerol.tinoexamen.main.UploadInteractor;
import com.cnoguerol.tinoexamen.main.UploadInteractorImpl;
import com.cnoguerol.tinoexamen.main.ui.MainView;
import com.cnoguerol.tinoexamen.main.ui.adapters.MainSectionsPagerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cnoguerol.
 */
@Module
public class MainModule {
    private MainView view;
    private String[] titles;
    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    public MainModule(MainView view, String[] titles, Fragment[] fragments, FragmentManager fragmentManager) {
        this.view = view;
        this.titles = titles;
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
    }

    @Provides
    @Singleton
    MainView providesMainView(){
        return this.view;
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(MainView view, EventBus eventBus, UploadInteractor uploadInteractor, SessionInteractor sessionInteractor){
        return new MainPresenterImpl(view, eventBus, uploadInteractor, sessionInteractor);
    }

    @Provides
    @Singleton
    UploadInteractor providesUploadInteractor(MainRepository repository){
        return new UploadInteractorImpl(repository);
    }

    @Provides
    @Singleton
    SessionInteractor providesSessionInteractor(MainRepository repository){
        return new SessionInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository(EventBus eventBus, FirebaseApi firebase){
        return new MainRepositoryImpl(eventBus, firebase);
    }

    @Provides
    @Singleton
    MainSectionsPagerAdapter providesMainSectionsPagerAdapter(FragmentManager fragment, String[] titles, Fragment[] fragments){
        return new MainSectionsPagerAdapter(fragment, titles, fragments);
    }

    @Provides
    @Singleton
    FragmentManager providesAdapterFragmentManager(){
        return this.fragmentManager;
    }

    @Provides
    @Singleton
    Fragment[] providesFragmentArrayForAdapter(){
        return this.fragments;
    }

    @Provides
    @Singleton
    String[] providesStringArrayForAdapter(){
        return this.titles;
    }
}

