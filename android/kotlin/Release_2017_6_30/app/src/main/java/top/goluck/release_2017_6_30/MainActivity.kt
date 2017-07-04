package top.goluck.release_2017_6_30

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.pm.PackageManager
import android.content.pm.ApplicationInfo



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        i("onCreate 我的channel值 = "+BuildConfig.channel)
        i("onCreate 我的UMENG_CHANNEL值 = "+getUMENG_CHANNEL())
    }

    fun i(msg:String){
        var msg = "------------------------$msg"
        Log.i("Main",msg);
    }

    fun getUMENG_CHANNEL(): String? {
        var appInfo: ApplicationInfo? = null
        try {
            appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return appInfo!!.metaData.getString("UMENG_CHANNEL")
    }
}
