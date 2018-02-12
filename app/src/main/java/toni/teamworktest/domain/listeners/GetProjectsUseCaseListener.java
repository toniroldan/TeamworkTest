package toni.teamworktest.domain.listeners;

import java.util.List;

import toni.teamworktest.presentation.model.Project;

/**
 * Created by Toni on 8/2/18
 */
public interface GetProjectsUseCaseListener {

    void onGetProjects(List<Project> projectsList);
    void onGetProjectsError(String errorMessage);
}
