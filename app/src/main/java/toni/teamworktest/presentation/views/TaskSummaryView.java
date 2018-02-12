package toni.teamworktest.presentation.views;

import toni.teamworktest.presentation.model.TaskSummary;

/**
 * Created by Toni on 9/2/18
 */

public interface TaskSummaryView extends BaseView{

    void showTaskSummary(TaskSummary mine, TaskSummary all);
}
