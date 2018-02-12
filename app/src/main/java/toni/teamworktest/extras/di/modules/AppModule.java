package toni.teamworktest.extras.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import toni.teamworktest.TeamworkApplication;
import toni.teamworktest.data.net.api.TeamworkApi;

@Module
public class AppModule {
    private final TeamworkApplication mApplication;

    public AppModule(TeamworkApplication application) {
        this.mApplication = application;
    }

    @Provides
    Context provideContext(){
        return this.mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    TeamworkApi provideTeamworkApi(){
        return new TeamworkApi();
    }
}
