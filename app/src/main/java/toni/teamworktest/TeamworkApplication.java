package toni.teamworktest;

import android.app.Application;

import toni.teamworktest.extras.di.components.AppComponent;
import toni.teamworktest.extras.di.components.DaggerAppComponent;
import toni.teamworktest.extras.di.modules.AppModule;

/**
 * Created by Toni on 8/2/18
 */

public class TeamworkApplication extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
