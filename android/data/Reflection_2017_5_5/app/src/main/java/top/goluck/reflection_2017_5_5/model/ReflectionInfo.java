package top.goluck.reflection_2017_5_5.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：luck on 2017/5/4 17:07
 * 邮箱：fc_dream@163.com
 * Reflection_2017_5_5
 */
public class ReflectionInfo extends BaseReflectionInfo implements Parcelable {

    private String name;
    private String sex;
    protected boolean isnew;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.sex);
        dest.writeByte(this.isnew ? (byte) 1 : (byte) 0);
    }

    public ReflectionInfo() {
    }
    public ReflectionInfo(String name) {
        this.name = name;
    }


    public ReflectionInfo(int id) {
        super(id);
    }


    protected ReflectionInfo(Parcel in) {
        this.name = in.readString();
        this.sex = in.readString();
        this.isnew = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ReflectionInfo> CREATOR = new Parcelable.Creator<ReflectionInfo>() {
        @Override
        public ReflectionInfo createFromParcel(Parcel source) {
            return new ReflectionInfo(source);
        }

        @Override
        public ReflectionInfo[] newArray(int size) {
            return new ReflectionInfo[size];
        }
    };
}
