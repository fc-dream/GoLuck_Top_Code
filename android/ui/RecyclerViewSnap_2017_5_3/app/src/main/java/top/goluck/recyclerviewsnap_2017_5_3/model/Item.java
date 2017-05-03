package top.goluck.recyclerviewsnap_2017_5_3.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：luck on 2017/5/1 12:50
 * 邮箱：fc_dream@163.com
 * DiffUtil_2017_5_1
 */
public class Item implements Parcelable {

    public static final String KEY_TITLE="KEY_TITLE";
    public static final String KEY_CONTENT="KEY_CONTENT";
    public static final String KEY_FOOTER="KEY_FOOTER";

    private int id;
    private String title;
    private String content;
    private String footer;

    public Item(int id, String title, String content, String footer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.footer = footer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.footer);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
        this.footer = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
