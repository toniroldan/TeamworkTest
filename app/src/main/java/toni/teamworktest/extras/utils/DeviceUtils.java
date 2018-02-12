package toni.teamworktest.extras.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

import toni.teamworktest.TeamworkApplication;

/**
 * Created by Toni on 7/2/18
 */

public class DeviceUtils {

    @Inject
    Context mContext;

    @Inject
    public DeviceUtils() {
        TeamworkApplication.getAppComponent().inject(this);
    }

    public boolean isConnected() {
        boolean connected = false;

        if (mContext != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null) {
                if (networkInfo.isAvailable()) {
                    connected = networkInfo.isConnected();
                }
            }
        }
        return connected;
    }
}
