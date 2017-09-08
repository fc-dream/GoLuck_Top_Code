package top.goluck.androidentityclone_2017_9_8.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 作者：luck on 2017/9/8 18:42
 * 邮箱：fc_dream@163.com
 * AndroidEntityClone_2017_9_8
 */
public class StudentInfo implements Cloneable, Serializable, Parcelable {
    private int studentID;
    private String studentName;
    private float studentScore;
    public static final Creator<StudentInfo> CREATOR = new Creator() {
        public StudentInfo createFromParcel(Parcel in) {
            return new StudentInfo(in);
        }

        public StudentInfo[] newArray(int size) {
            return new StudentInfo[size];
        }
    };

    public StudentInfo() {
    }

    protected StudentInfo(Parcel in) {
        this.studentID = in.readInt();
        this.studentName = in.readString();
        this.studentScore = in.readFloat();
    }

    public int getStudentID() {
        return this.studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getStudentScore() {
        return this.studentScore;
    }

    public void setStudentScore(float studentScore) {
        this.studentScore = studentScore;
    }

    public String toString() {
        return "studentID=" + this.studentID + "\nstudentName=\'" + this.studentName + '\'' + "\nstudentScore=" + this.studentScore;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.studentID);
        parcel.writeString(this.studentName);
        parcel.writeFloat(this.studentScore);
    }

    public StudentInfo clone() {
        StudentInfo studentInfo = null;

        try {
            studentInfo = (StudentInfo)super.clone();
        } catch (CloneNotSupportedException var3) {
            var3.printStackTrace();
        }

        return studentInfo;
    }
}