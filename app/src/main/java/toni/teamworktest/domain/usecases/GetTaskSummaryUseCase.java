package toni.teamworktest.domain.usecases;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import toni.teamworktest.TeamworkApplication;
import toni.teamworktest.data.net.api.TeamworkApi;
import toni.teamworktest.data.net.listener.TeamworkApiListener;
import toni.teamworktest.domain.listeners.GetTaskSummaryUseCaseListener;
import toni.teamworktest.extras.utils.DeviceUtils;
import toni.teamworktest.presentation.model.TaskSummary;

/**
 * Created by Toni on 8/2/18
 */

public class GetTaskSummaryUseCase implements TeamworkApiListener {

    private static final String NETWORK_ERROR = "NETWORK_ERROR";
    private static final String MINE = "mine";
    private static final String EVERYONE = "everyone";

    @Inject
    TeamworkApi mTeamworkApi;
    @Inject
    DeviceUtils mDeviceUtils;

    GetTaskSummaryUseCaseListener mGetTaskSummaryUseCaseListener;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    @Inject
    public GetTaskSummaryUseCase() {
        TeamworkApplication.getAppComponent().inject(this);
        mTeamworkApi.setListener(this);
    }

    public void setGetTaskSummaryUseCaseListener(GetTaskSummaryUseCaseListener getTaskSummaryUseCaseListener) {
        mGetTaskSummaryUseCaseListener = getTaskSummaryUseCaseListener;
    }

    public void execute(int idProject) {
        if (mDeviceUtils.isConnected()) {
            mTeamworkApi.getTaskSummary(idProject);
        } else {
            mGetTaskSummaryUseCaseListener.onGetTaskSummaryError(NETWORK_ERROR);
        }
    }

    // ---------------------------------------------------------------------------------------------
    // OVERRIDE METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    public void onSuccess(Object data) {

        try {
            Log.d("DEBUG", "onSuccess: " + data.toString());
            final Gson gson = new Gson();
            final java.lang.reflect.Type type = new TypeToken<TaskSummary>() {}.getType();

            JSONObject tasksJson = (JSONObject)data;
            TaskSummary myTasks = gson.fromJson(tasksJson.getString(MINE), type);
            TaskSummary allTasks = gson.fromJson(tasksJson.getString(EVERYONE), type);

            mGetTaskSummaryUseCaseListener.onGetTaskSummary(myTasks, allTasks);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String errorMessage) {
        if (errorMessage != null) {
            Log.d("DEBUG", "onError: " + errorMessage);
            mGetTaskSummaryUseCaseListener.onGetTaskSummaryError(errorMessage);
        }
    }
}
