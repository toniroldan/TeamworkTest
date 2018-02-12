package toni.teamworktest.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Toni on 8/2/18
 */

public class Company implements Parcelable {

    @Expose
    @SerializedName("name")
    private String mName;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    public String getName() {
        return mName;
    }

    // ---------------------------------------------------------------------------------------------
    // PARCELABLE METHODS
    // ---------------------------------------------------------------------------------------------

    protected Company(Parcel in) {
        mName = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mName);
    }
}
