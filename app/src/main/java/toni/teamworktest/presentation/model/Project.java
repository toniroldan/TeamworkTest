package toni.teamworktest.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Toni on 8/2/18
 */

public class Project implements Parcelable {

    @Expose
    @SerializedName("id")
    private int mId;

    @Expose
    @SerializedName("name")
    private String mName;

    @Expose
    @SerializedName("company")
    private Company mCompany;

    @Expose
    @SerializedName("tags")
    private List<Tag> mTagList;

    @Expose
    @SerializedName("startDate")
    private String mStartDate;

    @Expose
    @SerializedName("endDate")
    private String mEndDate;

    @Expose
    @SerializedName("description")
    private String mDescription;

    // ---------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    // ---------------------------------------------------------------------------------------------

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Company getCompany() {
        return mCompany;
    }

    public List<Tag> getTagList() {
        return mTagList;
    }

    public String getStartDate() {
        return formatDate(mStartDate);
    }

    public String getEndDate() {
        return formatDate(mEndDate);
    }

    public String getDescription() {
        return mDescription;
    }

    // ---------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------------------------------------------------------

    private String formatDate(String date) {

        return date.substring(6,8) + "/" + date.substring(4,6) + "/" + date.substring(0,4);
    }

    // ---------------------------------------------------------------------------------------------
    // PARCELABLE METHODS
    // ---------------------------------------------------------------------------------------------

    protected Project(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mCompany = in.readParcelable(Company.class.getClassLoader());
        mStartDate = in.readString();
        mEndDate = in.readString();
        mDescription = in.readString();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeParcelable(mCompany, flags);
        dest.writeString(mStartDate);
        dest.writeString(mEndDate);
        dest.writeString(mDescription);
    }
}
