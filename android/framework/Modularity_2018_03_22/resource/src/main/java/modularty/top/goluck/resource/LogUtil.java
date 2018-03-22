package modularty.top.goluck.resource;

import android.util.Log;



public class LogUtil {

    public static final String TAG = "util";
    public static final int NONE = 0;
    public static final int ERROR_ONLY = 1;
    public static final int ERROR_WARN = 2;
    public static final int ERROR_WARN_INFO = 3;
    public static final int ERROR_WARN_INFO_DEBUG = 4;
    private static final int LOGGING_LEVEL = true ? ERROR_WARN_INFO_DEBUG : NONE;

    public static void e(String msg) {
        if (LOGGING_LEVEL >= ERROR_ONLY) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg, Throwable e) {
        if (LOGGING_LEVEL >= ERROR_ONLY) {
            Log.e(TAG, msg, e);
        }
    }

    public static void w(String msg) {
        if (LOGGING_LEVEL >= ERROR_WARN) {
            Log.w(TAG, msg);
        }
    }

    public static void w(String msg, Throwable e) {
        if (LOGGING_LEVEL >= ERROR_WARN) {
            Log.w(TAG, msg, e);
        }
    }

    public static void i(String msg) {
        if (LOGGING_LEVEL >= ERROR_WARN_INFO) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String msg, Throwable e) {
        if (LOGGING_LEVEL >= ERROR_WARN_INFO) {
            Log.i(TAG, msg, e);
        }
    }

    public static void d(String msg) {
        if (LOGGING_LEVEL >= ERROR_WARN_INFO_DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String msg, Throwable e) {
        if (LOGGING_LEVEL >= ERROR_WARN_INFO_DEBUG) {
            Log.d(TAG, msg, e);
        }
    }
}