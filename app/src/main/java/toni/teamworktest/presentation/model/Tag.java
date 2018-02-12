package toni.teamworktest.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Toni on 8/2/18
 */

public class Tag implements Parcelable {

    @Expose
    @SerializedName("name")
    private String mName;

    @Expose
    @SerializedName("color")
    private String mColor;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    public String getName() {
        return mName;
    }

    public String getColor() {
        return mColor;
    }

    // ---------------------------------------------------------------------------------------------
    // PARCELABLE METHODS
    // ---------------------------------------------------------------------------------------------

    protected Tag(Parcel in) {
        mName = in.readString();
    }

    public static final Creator<Tag> CREATOR = new Creator<Tag>() {
        @Override
        public Tag createFromParcel(Parcel in) {
            return new Tag(in);
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mName);
        dest.writeValue(mColor);
    }
}
