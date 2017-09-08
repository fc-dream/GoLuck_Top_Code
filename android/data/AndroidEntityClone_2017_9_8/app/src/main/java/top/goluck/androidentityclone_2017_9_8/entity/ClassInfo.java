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
public class ClassInfo implements Cloneable, Serializable, Parcelable {
    private int classID;
    private String className;
    private List<StudentInfo> studentInfos;
    public static final Creator<ClassInfo> CREATOR = new Creator() {
        public ClassInfo createFromParcel(Parcel in) {
            return new ClassInfo(in);
        }

        public ClassInfo[] newArray(int size) {
            return new ClassInfo[size];
        }
    };

    public ClassInfo() {
    }

    protected ClassInfo(Parcel in) {
        this.classID = in.readInt();
        this.className = in.readString();
        this.studentInfos = in.createTypedArrayList(StudentInfo.CREATOR);
    }

    public int getClassID() {
        return this.classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<StudentInfo> getStudentInfos() {
        return this.studentInfos;
    }

    public void setStudentInfos(List<StudentInfo> studentInfos) {
        this.studentInfos = studentInfos;
    }

    public String toString() {
        return "classID=" + this.classID + "\nclassName=\'" + this.className + '\'' + "\ntudentInfos=" + this.studentInfos;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.classID);
        parcel.writeString(this.className);
        parcel.writeTypedList(this.studentInfos);
    }

    protected ClassInfo clone() {
        ClassInfo classInfo = null;

        try {
            classInfo = (ClassInfo)super.clone();
            ArrayList e = new ArrayList();

            for(int i = 0; i < this.studentInfos.size(); ++i) {
                e.add(((StudentInfo)this.studentInfos.get(i)).clone());
            }

            classInfo.studentInfos = e;
        } catch (CloneNotSupportedException var4) {
            var4.printStackTrace();
        }

        return classInfo;
    }
}