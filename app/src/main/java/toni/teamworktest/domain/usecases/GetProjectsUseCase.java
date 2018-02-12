package toni.teamworktest.domain.usecases;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

import toni.teamworktest.TeamworkApplication;
import toni.teamworktest.data.net.api.TeamworkApi;
import toni.teamworktest.data.net.listener.TeamworkApiListener;
import toni.teamworktest.domain.listeners.GetProjectsUseCaseListener;
import toni.teamworktest.extras.utils.DeviceUtils;
import toni.teamworktest.presentation.model.Project;

/**
 * Created by Toni on 8/2/18
 */

public class GetProjectsUseCase implements TeamworkApiListener {

    private static final String NETWORK_ERROR = "NETWORK_ERROR";

    @Inject
    TeamworkApi mTeamworkApi;
    @Inject
    DeviceUtils mDeviceUtils;

    GetProjectsUseCaseListener mGetProjectsUseCaseListener;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    @Inject
    public GetProjectsUseCase() {
        TeamworkApplication.getAppComponent().inject(this);
        mTeamworkApi.setListener(this);
    }

    public void setGetProjectsUseCaseListener(GetProjectsUseCaseListener getProjectsUseCaseListener) {
        mGetProjectsUseCaseListener = getProjectsUseCaseListener;
    }

    public void execute() {
        if (mDeviceUtils.isConnected()) {
            mTeamworkApi.getProjects();
        } else {
            mGetProjectsUseCaseListener.onGetProjectsError(NETWORK_ERROR);
        }
    }

    // ---------------------------------------------------------------------------------------------
    // OVERRIDE METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    public void onSuccess(Object data) {
        Log.d("DEBUG", "onSuccess: " + data.toString());
        final Gson gson = new Gson();
        final java.lang.reflect.Type type = new TypeToken<List<Project>>() {}.getType();
        final List<Project> projectsList = gson.fromJson((data).toString(), type);

        mGetProjectsUseCaseListener.onGetProjects(projectsList);
    }

    @Override
    public void onError(String errorMessage) {
        if (errorMessage != null) {
            Log.d("DEBUG", "onError: " + errorMessage);
            mGetProjectsUseCaseListener.onGetProjectsError(errorMessage);
        }
    }
}
