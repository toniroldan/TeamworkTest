package toni.teamworktest.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import toni.teamworktest.R;
import toni.teamworktest.TeamworkApplication;
import toni.teamworktest.presentation.adapters.ProjectsAdapter;
import toni.teamworktest.presentation.model.Project;
import toni.teamworktest.presentation.presenter.ProjectsPresenter;
import toni.teamworktest.presentation.views.ProjectsView;

public class ProjectsActivity extends BaseActivity implements ProjectsView, ProjectsAdapter.OnProjectClickListener{

    @BindView(R.id.rv_projects)
    RecyclerView mProjectsRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    ProjectsPresenter mProjectsPresenter;

    private ProjectsAdapter mProjectsAdapter;

    // ---------------------------------------------------------------------------------------------
    // OVERRIDE METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        TeamworkApplication.getAppComponent().inject(this);
        ButterKnife.bind(this);
        setupActionBar();
        setupRecyclerView();
        mProjectsPresenter.setView(this);
        getProjectsList();
    }

    // ---------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------------------------------------------------------

    private void setupActionBar() {
        mToolbar.setTitle(getString(R.string.projects));
        setSupportActionBar(mToolbar);
    }

    private void setupRecyclerView() {

        mProjectsAdapter = new ProjectsAdapter(this);
        mProjectsAdapter.setOnProjectClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mProjectsRecyclerView.setLayoutManager(layoutManager);
        mProjectsRecyclerView.setAdapter(mProjectsAdapter);
    }

    private void getProjectsList() {
        mProjectsPresenter.getProjectsList();
    }

    // ---------------------------------------------------------------------------------------------
    // PROJECTS VIEW METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    public void onProjectClicked(Project project) {
        Intent intent = TaskSummaryActivity.createIntent(this, project);
        startActivity(intent);
    }

    @Override
    public void showProjects(List<Project> projectsList) {
        if (projectsList != null) {
            mProjectsAdapter.setProjectsList(projectsList);
        }
    }
}
