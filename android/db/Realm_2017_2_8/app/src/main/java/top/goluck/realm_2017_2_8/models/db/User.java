package top.goluck.realm_2017_2_8.models.db;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 作者：luck on 2017/2/7 17:42
 * 邮箱：fc_dream@163.com
 * Realm
 */
public class User<T extends User>  extends RealmObject {

    @PrimaryKey //这里是设置主键 不支持自增列
    private int userId;
    @Required//这里的意思是不允许存入为null的值
    private String userName;
    @Ignore //这里的意思是忽略的属性，将不保存该属性到数据库
    private String userPwd;
    private boolean userSex;
//    // 这里集合数据需用RealmList对象
//    private RealmList<User> users;

//    此外 @Index 会为字段增加搜索索引。这会导致插入速度变慢，同时数据文件体积有所增加，但能加速查询。因此建议仅在需要加速查询时才添加索引。目前仅支持索引的属性类型包括：String、byte、short、int、long、boolean和Date
//    主键 (primary keys)
//    @PrimaryKey 可以用来定义字段为主键，该字段类型必须为字符串（String）或整数（short、int 或 long）以及它们的包装类型（Short、Int 或 Long）。不可以存在多个主键。使用支持索引的属性类型作为主键同时意味着为该字段建立索引。
//    当创建 Realm 对象时，所有字段会被设置为默认值。为了避免与具有相同主键的另一个对象冲突，建议创建一个 standalone 对象，为字段的赋值，然后用 copyToRealm() 方法将该对象复制到 Realm。
//    主键的存在意味着可以使用 createOrUpdate() 方法，它会用此主键尝试寻找一个已存在的对象，如果对象存在，就更新该对象；反之，它会创建一个新的对象。
//    使用主键会对性能产生影响。创建和更新对象将会慢一点，而查询则会变快。很难量化这些性能的差异，因为性能的改变跟您数据库的大小息息相关。
//    Realm.createObject()会返回一个所有字段被设置为默认值的新对象。如果该模型类存在主键，那么有可能返回对象的主键的默认值与其它已存在的对象冲突。建议创建一个独立的（standalone）Realm 对象，并给其主键赋值，然后调用 copyToRealm() 来避免冲突。


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

//    public RealmList<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(RealmList<User> users) {
//        this.users = users;
//    }

    public boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(boolean userSex) {
        this.userSex = userSex;
    }
}
