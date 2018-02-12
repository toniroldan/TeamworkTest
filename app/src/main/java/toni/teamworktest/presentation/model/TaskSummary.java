package toni.teamworktest.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Toni on 9/2/18
 */

public class TaskSummary implements Parcelable {

    @Expose
    @SerializedName("nodate")
    private Integer mNoDate;

    @Expose
    @SerializedName("today")
    private Integer mToday;

    @Expose
    @SerializedName("late")
    private Integer mLate;

    @Expose
    @SerializedName("started")
    private Integer mStarted;

    @Expose
    @SerializedName("upcoming")
    private Integer mUpcoming;

    @Expose
    @SerializedName("active")
    private Integer mActive;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    public Integer getNoDate() {
        return mNoDate;
    }

    public Integer getToday() {
        return mToday;
    }

    public Integer getLate() {
        return mLate;
    }

    public Integer getStarted() {
        return mStarted;
    }

    public Integer getUpcoming() {
        return mUpcoming;
    }

    public Integer getActive() {
        return mActive;
    }


    // ---------------------------------------------------------------------------------------------
    // PARCELABLE METHODS
    // ---------------------------------------------------------------------------------------------

    protected TaskSummary(Parcel in) {
        mNoDate = in.readInt();
        mToday = in.readInt();
        mLate = in.readInt();
        mStarted = in.readInt();
        mUpcoming = in.readInt();
        mActive = in.readInt();
    }

    public static final Creator<TaskSummary> CREATOR = new Creator<TaskSummary>() {
        @Override
        public TaskSummary createFromParcel(Parcel in) {
            return new TaskSummary(in);
        }

        @Override
        public TaskSummary[] newArray(int size) {
            return new TaskSummary[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mNoDate);
        dest.writeInt(mToday);
        dest.writeInt(mLate);
        dest.writeInt(mStarted);
        dest.writeInt(mUpcoming);
        dest.writeInt(mActive);
    }
}
