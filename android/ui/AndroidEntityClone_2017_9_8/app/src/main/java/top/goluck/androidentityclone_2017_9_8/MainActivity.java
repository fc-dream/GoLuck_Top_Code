package top.goluck.androidentityclone_2017_9_8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import top.goluck.androidentityclone_2017_9_8.data.DataUtil;
import top.goluck.androidentityclone_2017_9_8.entity.ClassInfo;
import top.goluck.androidentityclone_2017_9_8.entity.SchoolInfo;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private SchoolInfo mSchoolInfo1 = DataUtil.getSchoolInfo();
    private SchoolInfo mSchoolInfo2 = mSchoolInfo1.clone();
    private SchoolInfo mSchoolInfo3;
    private SchoolInfo mSchoolInfo4;
    private ArrayList<ClassInfo> mClassInfos1 = DataUtil.getClassInfos(),mClassInfos2;

    public MainActivity() {
        this.mSchoolInfo2 = this.mSchoolInfo1.clone();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);


        Bundle mBundle1 = new Bundle();
        mBundle1.putParcelable("SchoolInfo", this.mSchoolInfo1);
        this.mSchoolInfo3 = mBundle1.getParcelable("SchoolInfo");


//        Bundle mBundle2 = new Bundle();
//        mBundle2.putSerializable("SchoolInfo",mSchoolInfo1);//存
//        mSchoolInfo2 = (SchoolInfo) mBundle2.getSerializable("SchoolInfo");//取

        Bundle mBundle3 = new Bundle();
        mBundle3.putParcelableArrayList("ClassInfos", mClassInfos1);// 数据 存
        mClassInfos2 = mBundle3.getParcelableArrayList("ClassInfos");//数据 取

        try {
            ByteArrayOutputStream e = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(e);
            oos.writeObject(this.mSchoolInfo1);
            ByteArrayInputStream bais = new ByteArrayInputStream(e.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            this.mSchoolInfo4 = (SchoolInfo)ois.readObject();
        } catch (IOException var7) {
            var7.printStackTrace();
        } catch (ClassNotFoundException var8) {
            var8.printStackTrace();
        }

        this.text1 = (TextView)this.findViewById(R.id.text1);
        this.text2 = (TextView)this.findViewById(R.id.text2);
        this.text1.setText("mSchoolInfo1对象：\n" + this.mSchoolInfo1 + "\n\nmSchoolInfo1对象的Cloneable对象mSchoolInfo2=" + this.mSchoolInfo2 + "\n\nmSchoolInfo1对象的Parcelable对象mSchoolInfo3=" + this.mSchoolInfo3 + "\n\nmSchoolInfo1对象的Serializable对象mSchoolInfo3=" + this.mSchoolInfo4);
        this.text2.setText("照理说修改后的对象都不会再受原始改变而改变，见证奇迹吧！\n\n修改后的mSchoolInfo1对象：\n" + DataUtil.updateSchoolInfo(this.mSchoolInfo1).toString() + "\n\nmSchoolInfo1对象的Cloneable对象mSchoolInfo2=" + this.mSchoolInfo2 + "\n\nmSchoolInfo1对象的Parcelable对象mSchoolInfo3=" + this.mSchoolInfo3 + "\n\nmSchoolInfo1对象的Serializable对象mSchoolInfo3=" + this.mSchoolInfo4);
    }
}