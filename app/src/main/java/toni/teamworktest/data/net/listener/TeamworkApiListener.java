package toni.teamworktest.data.net.listener;

/**
 * Created by Toni on 8/2/18
 */
public interface TeamworkApiListener {

    void onSuccess(Object data);

    void onError(String errorMessage);
}
