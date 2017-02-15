package top.goluck.realm_2017_2_8.base.ui;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * 作者：luck on 2017/2/9 14:43
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        //自定义配置
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("Realm2017_2_8.realm")
                .schemaVersion(1)
//                数据库升级之后需要进行操作的方法 以下官方示例
//                .migration(new RealmMigration(){
//
//                    @Override
//                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
//                        RealmSchema schema = realm.getSchema();
//                        // Migrate to version 1: Add a new class.
//                        // Example:
//                        // public Person extends RealmObject {
//                        //     private String name;
//                        //     private int age;
//                        //     // getters and setters left out for brevity
//                        // }
//                        if (oldVersion == 0) {
//                            schema.create("Person")
//                                    .addField("name", String.class)
//                                    .addField("age", int.class);
//                            oldVersion++;
//                        }
//
//                        // Migrate to version 2: Add a primary key + object references
//                        // Example:
//                        // public Person extends RealmObject {
//                        //     private String name;
//                        //     @PrimaryKey
//                        //     private int age;
//                        //     private Dog favoriteDog;
//                        //     private RealmList<Dog> dogs;
//                        //     // getters and setters left out for brevity
//                        // }
//                        if (oldVersion == 1) {
//                            schema.get("Person")
//                                    .addField("id", long.class, FieldAttribute.PRIMARY_KEY)
//                                    .addRealmObjectField("favoriteDog", schema.get("Dog"))
//                                    .addRealmListField("dogs", schema.get("Dog"));
//                            oldVersion++;
//                        }
//                    }
//                })
//                不再抛异常，直接删除以前的数据构造版本号，会造成数据丢失。
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        Realm.init(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}
