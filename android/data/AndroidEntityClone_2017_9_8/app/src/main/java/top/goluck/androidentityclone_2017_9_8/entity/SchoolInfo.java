package top.goluck.androidentityclone_2017_9_8.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：luck on 2017/9/8 18:41
 * 邮箱：fc_dream@163.com
 * AndroidEntityClone_2017_9_8
 */
public class SchoolInfo  implements Cloneable, Serializable, Parcelable {
    private int schoolID;
    private String schoolName;
    private List<ClassInfo> classInfos;
    public static final Creator<SchoolInfo> CREATOR = new Creator() {
        public SchoolInfo createFromParcel(Parcel in) {
            return new SchoolInfo(in);
        }

        public SchoolInfo[] newArray(int size) {
            return new SchoolInfo[size];
        }
    };

    public SchoolInfo() {
    }

    protected SchoolInfo(Parcel in) {
        this.schoolID = in.readInt();
        this.schoolName = in.readString();
        this.classInfos = in.createTypedArrayList(ClassInfo.CREATOR);
    }

    public int getSchoolID() {
        return this.schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<ClassInfo> getClassInfos() {
        return this.classInfos;
    }

    public void setClassInfos(List<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }

    public String toString() {
        return "schoolID=" + this.schoolID + "\nschoolName=\'" + this.schoolName + '\'' + "\nclassInfos=" + this.classInfos;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.schoolID);
        parcel.writeString(this.schoolName);
        parcel.writeTypedList(this.classInfos);
    }

    public SchoolInfo clone() {
        SchoolInfo schoolInfo = null;

        try {
            schoolInfo = (SchoolInfo)super.clone();
            ArrayList e = new ArrayList();

            for(int i = 0; i < this.classInfos.size(); ++i) {
                e.add(((ClassInfo)this.classInfos.get(i)).clone());
            }

            schoolInfo.classInfos = e;
        } catch (CloneNotSupportedException var4) {
            var4.printStackTrace();
        }

        return schoolInfo;
    }
}