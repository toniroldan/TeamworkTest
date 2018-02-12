package toni.teamworktest.domain.listeners;

import toni.teamworktest.presentation.model.TaskSummary;

/**
 * Created by Toni on 9/2/18
 */
public interface GetTaskSummaryUseCaseListener {

    void onGetTaskSummary(TaskSummary mine, TaskSummary all);
    void onGetTaskSummaryError(String errorMessage);
}
