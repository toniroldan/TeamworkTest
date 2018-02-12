package toni.teamworktest.presentation.presenter;

import android.widget.Toast;

import javax.inject.Inject;

import toni.teamworktest.domain.listeners.GetTaskSummaryUseCaseListener;
import toni.teamworktest.domain.usecases.GetTaskSummaryUseCase;
import toni.teamworktest.presentation.model.TaskSummary;
import toni.teamworktest.presentation.views.TaskSummaryView;

/**
 * Created by Toni on 9/2/18
 */

public class TaskSummaryPresenter implements GetTaskSummaryUseCaseListener {

    private TaskSummaryView mView;
    private GetTaskSummaryUseCase mGetTaskSummaryUseCase;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    @Inject
    public TaskSummaryPresenter(GetTaskSummaryUseCase getTaskSummaryUseCase) {
        mGetTaskSummaryUseCase = getTaskSummaryUseCase;
        mGetTaskSummaryUseCase.setGetTaskSummaryUseCaseListener(this);
    }

    public void setView(TaskSummaryView view) {
        mView = view;
    }

    public void getTaskSummary(int idProject) {
        mGetTaskSummaryUseCase.execute(idProject);
    }

    // ---------------------------------------------------------------------------------------------
    // OVERRIDE METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    public void onGetTaskSummary(TaskSummary mine, TaskSummary all) {
        mView.showTaskSummary(mine, all);
    }

    @Override
    public void onGetTaskSummaryError(String errorMessage) {
        mView.showMessage(errorMessage, Toast.LENGTH_LONG);
    }
}
