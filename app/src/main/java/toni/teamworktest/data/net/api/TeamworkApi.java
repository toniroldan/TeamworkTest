package toni.teamworktest.data.net.api;

import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import toni.teamworktest.data.net.endpoints.TeamworkEndPoints;
import toni.teamworktest.data.net.interceptor.TeamworkInterceptor;
import toni.teamworktest.data.net.listener.TeamworkApiListener;


/**
 * Created by Toni on 8/2/18
 */
public class TeamworkApi {

    private static final String STATUS = "STATUS";
    private static final String OK = "OK";
    private static final String PROJECTS = "projects";
    private static final String SUMMARY = "summary";
    private static final String TASKS = "tasks";
    private static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    private TeamworkEndPoints mTeamworkEndPoints;
    private TeamworkApiListener mTeamworkApiListener;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    @Inject
    public TeamworkApi() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new TeamworkInterceptor())
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://yat.teamwork.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTeamworkEndPoints = retrofit.create(TeamworkEndPoints.class);
    }

    public void setListener(TeamworkApiListener listener) {
        mTeamworkApiListener = listener;
    }

    public void getProjects() {

        Call<ResponseBody> result = mTeamworkEndPoints.getProjectsList();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()) {
                    handleGetProjectsResponse(response);
                } else {
                    mTeamworkApiListener.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                mTeamworkApiListener.onError(t.getMessage());
            }
        });
    }

    public void getTaskSummary(int idProject) {

        Call<ResponseBody> result = mTeamworkEndPoints.getTaskSummary(idProject);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()) {
                    handleGetTaskSummaryResponse(response);
                } else {
                    mTeamworkApiListener.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                mTeamworkApiListener.onError(t.getMessage());
            }
        });
    }

    // ---------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------------------------------------------------------

    private void handleGetProjectsResponse(Response<ResponseBody> response) {

        try {
            JSONObject jsonObject = new JSONObject(response.body().string());
            String status = jsonObject.get(STATUS).toString();
            JSONArray projectsJson = jsonObject.getJSONArray(PROJECTS);

            if(OK.equals(status)) {
                mTeamworkApiListener.onSuccess(projectsJson);
            } else {
                mTeamworkApiListener.onError(UNKNOWN_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void handleGetTaskSummaryResponse(Response<ResponseBody> response) {

        try {
            JSONObject jsonObject = new JSONObject(response.body().string());
            JSONObject summaryJson = jsonObject.getJSONObject(SUMMARY);
            JSONObject tasksJson = summaryJson.getJSONObject(TASKS);

            mTeamworkApiListener.onSuccess(tasksJson);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
