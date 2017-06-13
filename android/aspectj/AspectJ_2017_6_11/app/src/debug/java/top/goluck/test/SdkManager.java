package top.goluck.test;

import android.content.Context;

import com.facebook.stetho.Stetho;

public class SdkManager {
    public static void init(Context context) {
        Stetho.initializeWithDefaults(context);
    }
}
