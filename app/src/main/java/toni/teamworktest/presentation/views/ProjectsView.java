package toni.teamworktest.presentation.views;

import java.util.List;

import toni.teamworktest.presentation.model.Project;

/**
 * Created by Toni on 8/2/18
 */

public interface ProjectsView extends BaseView{

    void showProjects(List<Project> projects);
}
