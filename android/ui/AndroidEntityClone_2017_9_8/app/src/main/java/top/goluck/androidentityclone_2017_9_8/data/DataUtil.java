package top.goluck.androidentityclone_2017_9_8.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import top.goluck.androidentityclone_2017_9_8.entity.ClassInfo;
import top.goluck.androidentityclone_2017_9_8.entity.SchoolInfo;
import top.goluck.androidentityclone_2017_9_8.entity.StudentInfo;

/**
 * 作者：luck on 2017/9/8 18:40
 * 邮箱：fc_dream@163.com
 * AndroidEntityClone_2017_9_8
 */
public class DataUtil  {

    public DataUtil() {
    }

    public static SchoolInfo getSchoolInfo() {
        SchoolInfo data = new SchoolInfo();
        data.setSchoolID(1);
        data.setSchoolName("测试第一大学");
        data.setClassInfos(getClassInfos());
        return data;
    }

    public static ArrayList<ClassInfo> getClassInfos() {
        ArrayList datas = new ArrayList();

        for(int i = 0; i < 1; ++i) {
            ClassInfo data = new ClassInfo();
            data.setClassID(i + 1);
            data.setClassName("(第" + (2017 + i) + "级)");
            data.setStudentInfos(getStudentInfos());
            datas.add(data);
        }

        return datas;
    }

    public static List<StudentInfo> getStudentInfos() {
        ArrayList datas = new ArrayList();

        for(int i = 0; i < 1; ++i) {
            StudentInfo data = new StudentInfo();
            data.setStudentID(i + 1);
            data.setStudentName("Android" + (i + 1) + "号学员");
            data.setStudentScore((float)((new Random(50L)).nextInt() + 50));
            datas.add(data);
        }

        return datas;
    }

    public static SchoolInfo updateSchoolInfo(SchoolInfo schoolInfo) {
        if(schoolInfo != null) {
            schoolInfo.setSchoolID(1);
            schoolInfo.setSchoolName("我是修改后的测试第一大学");
            ClassInfo classInfo = (ClassInfo)schoolInfo.getClassInfos().get(0);
            classInfo.setClassName("我是修改后的" + classInfo.getClassName());
            schoolInfo.getClassInfos().set(0, classInfo);
        }

        return schoolInfo;
    }
}