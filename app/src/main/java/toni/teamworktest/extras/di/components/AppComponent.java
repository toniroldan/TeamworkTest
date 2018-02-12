package toni.teamworktest.extras.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import toni.teamworktest.domain.usecases.GetProjectsUseCase;
import toni.teamworktest.domain.usecases.GetTaskSummaryUseCase;
import toni.teamworktest.extras.di.modules.AppModule;
import toni.teamworktest.extras.utils.DeviceUtils;
import toni.teamworktest.presentation.activities.ProjectsActivity;
import toni.teamworktest.presentation.activities.TaskSummaryActivity;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(ProjectsActivity projectsActivity);
    void inject(TaskSummaryActivity taskSummaryActivity);
    void inject(DeviceUtils deviceUtils);
    void inject(GetProjectsUseCase getProjectsUseCase);
    void inject(GetTaskSummaryUseCase getTaskSummaryUseCase);

    Context provideApplicationContext();
}
