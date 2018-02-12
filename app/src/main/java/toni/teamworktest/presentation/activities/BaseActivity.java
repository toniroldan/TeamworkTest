package toni.teamworktest.presentation.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import toni.teamworktest.presentation.views.BaseView;

/**
 * Created by Toni on 8/2/18
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public void showMessage(String message, int duration) {
        if(!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, duration).show();
        }
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }
}
