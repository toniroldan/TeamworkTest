package toni.teamworktest.presentation.presenter;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import toni.teamworktest.domain.listeners.GetProjectsUseCaseListener;
import toni.teamworktest.domain.usecases.GetProjectsUseCase;
import toni.teamworktest.presentation.model.Project;
import toni.teamworktest.presentation.views.ProjectsView;

/**
 * Created by Toni on 8/2/18
 */

public class ProjectsPresenter implements GetProjectsUseCaseListener {

    private ProjectsView mView;
    private GetProjectsUseCase mGetProjectsUseCase;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    @Inject
    public ProjectsPresenter(GetProjectsUseCase getProjectsUseCase) {
        mGetProjectsUseCase = getProjectsUseCase;
        mGetProjectsUseCase.setGetProjectsUseCaseListener(this);
    }

    public void setView(ProjectsView view) {
        mView = view;
    }

    public void getProjectsList() {
        mGetProjectsUseCase.execute();
    }

    // ---------------------------------------------------------------------------------------------
    // OVERRIDE METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    public void onGetProjects(List<Project> projectsList) {
        mView.showProjects(projectsList);
    }

    @Override
    public void onGetProjectsError(String errorMessage) {
        mView.showMessage(errorMessage, Toast.LENGTH_LONG);
    }
}
