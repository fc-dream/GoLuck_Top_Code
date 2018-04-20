package top.goluck.lifecycle_aware2018_04_16.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import top.goluck.lifecycle_aware2018_04_16.viewmodel.Test;

@Dao
public interface TestDao {

    @Query("select * from tests where name like :name")
    Flowable<List<Test>> getTests(String name);
    @Query("select * from tests")
    Flowable<List<Test>> getAllTests();

   @Insert
    long insert(Test test);

   @Delete
   int delete(Test test);

   @Update
   int updateUser(Test test);

   @Update
   int updateUser(List<Test> tests);

}