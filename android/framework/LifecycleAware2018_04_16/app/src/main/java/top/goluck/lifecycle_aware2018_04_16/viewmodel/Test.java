package top.goluck.lifecycle_aware2018_04_16.viewmodel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 作者：luck on 2018/4/17 14:52
 * 邮箱：fc_dream@163.com
 * LifecycleAware2018_04_16
 */
@Entity(tableName = "tests")
public class Test {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "name")
    public String name;

    public Test(){
    }

    public Test(int id){
        this.id = id;
    }

    public Test(int id,String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
