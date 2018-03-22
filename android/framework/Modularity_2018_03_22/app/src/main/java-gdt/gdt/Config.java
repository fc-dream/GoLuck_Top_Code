package gdt;

import android.content.Context;
import android.widget.Toast;

import com.qq.gdt.action.ActionType;
import com.qq.gdt.action.GDTAction;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：luck on 2018/3/20 18:29
 * 邮箱：fc_dream@163.com
 * Modularity
 */
public class Config {
    // 广点通sdk初始
    public static void initGDT(Context application){
        Toast.makeText(application,"gdt 正式代码 initGDT",Toast.LENGTH_LONG);
//        if(!PermissionsUtil.isPermissionNotGranted(application,android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            GDTAction.init(application, "yourUserActionSetID", "yourAppSecretKey"); // 第一个参数是Context上下文，第二个参数是您在DMP上获得的行为数据源ID，第三个参数是您在DMP上获得AppSecretKey
            GDTAction.logAction(ActionType.START_APP); // 每次初始化时要上报启动行为，SDK内部会自动识别这是否为用户首次启动App并上报激活行为和启动行为
//        }
    }

    // 上传数据
    public static void logActionGDT(){
        // 用户发生购物行为时，可以用GDTAction.logAction上报用户的这次行为，并将价格等行为参数一起带上
        JSONObject actionParam = new JSONObject();
        try {
            actionParam.put("price", 6800);
            actionParam.put("name", "Pixel 2 XL");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GDTAction.logAction(ActionType.PURCHASE, actionParam);
    }

    // 需要在Launcher Activity的onResume方法中调用上报App启动行为。
    public static void logActionGDTstartApp(){
//        if(!PermissionsUtil.isPermissionNotGranted(ScreenManager.getInstance(),android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            GDTAction.logAction(ActionType.START_APP);
//        }
        myGDT();
    }

    private static void myGDT(){

    }
}
