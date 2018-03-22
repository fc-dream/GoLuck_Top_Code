package gdt;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：luck on 2018/3/20 18:29
 * 邮箱：fc_dream@163.com
 * Modularity
 */
public class Config {
    // 广点通sdk初始
    public static void initGDT(Context application){
        Toast.makeText(application,"gdt 非正式代码 initGDT", Toast.LENGTH_LONG);
    }

    // 上传数据
    public static void logActionGDT(){
    }

    // 需要在Launcher Activity的onResume方法中调用上报App启动行为。
    public static void logActionGDTstartApp(){
    }
}