/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.goluck.room_2017_12_24.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import top.goluck.room_2017_12_24.db.dao.BookDao;
import top.goluck.room_2017_12_24.db.dao.LoanDao;
import top.goluck.room_2017_12_24.db.dao.UserDao;
import top.goluck.room_2017_12_24.model.Book;
import top.goluck.room_2017_12_24.model.Loan;
import top.goluck.room_2017_12_24.model.User;

@Database(entities = {User.class, Book.class, Loan.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userModel();

    public abstract BookDao bookModel();

    public abstract LoanDao loanModel();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    // To simplify the codelab, allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()//运行在主线程查询
                    .addCallback(new Callback() {
                        @Override public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            // 创建数据后执行
                        }

                        @Override public void onOpen(@NonNull SupportSQLiteDatabase db) {
                            super.onOpen(db);
                            //打开数据库执行
                        }
                    })
                    .addMigrations(new Migration(1, 2) {//数据库升级可用
                        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
                            //从1版本升级到2版本
                        }
                    }, new Migration(2,3) {
                        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
                            //从2版本升级到3版本
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}