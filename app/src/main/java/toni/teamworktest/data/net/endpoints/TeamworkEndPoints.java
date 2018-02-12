package toni.teamworktest.data.net.endpoints;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Toni on 8/2/18
 */
public interface TeamworkEndPoints {

    @GET("projects.json")
    Call<ResponseBody> getProjectsList();

    @GET("projects/{idProject}/summary.json")
    Call<ResponseBody> getTaskSummary(@Path("idProject") int idProject);
}