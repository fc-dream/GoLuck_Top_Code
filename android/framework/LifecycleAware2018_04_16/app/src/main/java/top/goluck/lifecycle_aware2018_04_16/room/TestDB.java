package top.goluck.lifecycle_aware2018_04_16.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import top.goluck.lifecycle_aware2018_04_16.viewmodel.Test;

@Database(entities = {Test.class},version = 1,exportSchema = false)
public abstract class TestDB extends RoomDatabase {
    public abstract TestDao getTestDao();
}