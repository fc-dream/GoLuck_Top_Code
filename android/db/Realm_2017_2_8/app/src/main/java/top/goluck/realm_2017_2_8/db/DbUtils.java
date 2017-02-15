package top.goluck.realm_2017_2_8.db;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import top.goluck.realm_2017_2_8.models.db.User;

/**
 * 作者：luck on 2017/2/8 17:04
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public class DbUtils {

    private Realm realm;
    public DbUtils() {
        realm = Realm.getDefaultInstance();
    }

    // 复制数据到Realm 保存或者更新
    public <E extends RealmModel> void copyToRealmOrUpdate(E e){
        if(isaBoolean()) {
            //方法1：复制一个对象到Realm数据库
            realm.beginTransaction();
            realm.insert(e);//copyToRealmOrUpdate
            realm.commitTransaction();
            //方法2：直接添加一个新的
//            User user = realm.createObject(User.class); // Create a new object
//            user.setUserName("Luck");
//            realm.commitTransaction();
            //方法3：使用事务块
//            final User user = new User();
//            user.setUserName("Luck");
//            realm.executeTransaction(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.copyToRealm(user);
//                }
//            });
        }
    }


    // 复制数据到Realm 保存或者更新
    public <E extends RealmModel>  void copyToRealmOrUpdateByAsyn(final E e){
        if(isaBoolean()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insert(e);//copyToRealmOrUpdate
                }
            });
        }
    }

    public <T extends RealmModel> List<T> queryAllT(Class t){
        if(isaBoolean()) {
            RealmResults<T> users = (RealmResults<T>) realm.where(t).findAll();
            return realm.copyFromRealm(users);
        }
        return null;
    }

    /**
     * 关闭数据库操作对象
     */
    public void onClose(){
        if(isaBoolean()) {
            // Remember to close the Realm instance when done with it
            realm.close();
        }
    }

    private boolean isaBoolean() {
        return realm!=null;
    }


    public <T extends RealmModel> void update(T data) {
        if(isaBoolean()) {
            realm.insertOrUpdate(data);
        }
    }

    public void delete(User data) {
        if(isaBoolean()) {
            realm.beginTransaction();
            User user = realm.where(User.class).equalTo("userId", data.getUserId()).findFirst();
            user.deleteFromRealm();
            realm.commitTransaction();
        }
    }

}
