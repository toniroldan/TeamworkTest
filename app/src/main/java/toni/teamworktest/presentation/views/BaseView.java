package toni.teamworktest.presentation.views;

import android.content.Context;

/**
 * Created by Toni on 7/2/18
 */

public interface BaseView {

    void showMessage(String message, int duration);

    Context context();
}
