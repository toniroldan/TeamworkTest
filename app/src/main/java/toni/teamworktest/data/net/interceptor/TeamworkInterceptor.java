package toni.teamworktest.data.net.interceptor;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Toni on 8/2/18
 */
public class TeamworkInterceptor implements Interceptor {

    private String credentials = Credentials.basic("twp_TEbBXGCnvl2HfvXWfkLUlzx92e3T", "yatyatyat27");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Accept", "application/json")
                .header("Authorization", credentials).build();
        return chain.proceed(request);
    }
}
