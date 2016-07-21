package com.cnoguerol.tinoexamen;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.cnoguerol.tinoexamen.domain.di.DomainModule;
import com.cnoguerol.tinoexamen.libs.di.LibsModule;
import com.cnoguerol.tinoexamen.login.di.DaggerLoginComponent;
import com.cnoguerol.tinoexamen.login.di.LoginComponent;
import com.cnoguerol.tinoexamen.login.di.LoginModule;
import com.cnoguerol.tinoexamen.login.ui.LoginView;
import com.cnoguerol.tinoexamen.main.di.DaggerMainComponent;
import com.cnoguerol.tinoexamen.main.di.MainComponent;
import com.cnoguerol.tinoexamen.main.di.MainModule;
import com.cnoguerol.tinoexamen.main.ui.MainView;
import com.cnoguerol.tinoexamen.photolist.di.DaggerPhotoListComponent;
import com.cnoguerol.tinoexamen.photolist.di.PhotoListComponent;
import com.cnoguerol.tinoexamen.photolist.di.PhotoListModule;
import com.cnoguerol.tinoexamen.photolist.ui.PhotoListFragment;
import com.cnoguerol.tinoexamen.photolist.ui.PhotoListView;
import com.cnoguerol.tinoexamen.photolist.ui.adapters.OnItemClickListener;
import com.cnoguerol.tinoexamen.photomap.di.DaggerPhotoMapComponent;
import com.cnoguerol.tinoexamen.photomap.di.PhotoMapComponent;
import com.cnoguerol.tinoexamen.photomap.di.PhotoMapModule;
import com.cnoguerol.tinoexamen.photomap.ui.PhotoMapFragment;
import com.cnoguerol.tinoexamen.photomap.ui.PhotoMapView;

/**
 * Created by cnoguerol.
 */
public class PhotoFeedApp extends Application {
    private final static String EMAIL_KEY = "email";
    private final static String SHARED_PREFERENCES_NAME = "UserPrefs";


    private DomainModule domainModule;
    private PhotoFeedAppModule photoFeedAppModule;


    @Override
    public void onCreate() {
        super.onCreate();

        initModules();
    }

    private void initModules() {

        domainModule = new DomainModule();
        photoFeedAppModule = new PhotoFeedAppModule(this);

    }



    public String getEmailKey() {
        return EMAIL_KEY;
    }

    public String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view){
        return DaggerLoginComponent
                .builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view))
                .build();
    }

    public MainComponent getMainComponent(MainView view, String[] titles, Fragment[] fragments, FragmentManager manager){
        return DaggerMainComponent
                .builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .mainModule(new MainModule(view, titles, fragments, manager))
                .build();
    }

    public PhotoListComponent getPhotoListComponent(PhotoListFragment fragment, PhotoListView view, OnItemClickListener onItemClickListener){
        return DaggerPhotoListComponent
                .builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .photoListModule(new PhotoListModule(view, onItemClickListener))
                .build();
    }

    public PhotoMapComponent getPhotoMapComponent(PhotoMapFragment fragment, PhotoMapView view) {
        return DaggerPhotoMapComponent
                .builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .photoMapModule(new PhotoMapModule(view))
                .build();
    }
}
