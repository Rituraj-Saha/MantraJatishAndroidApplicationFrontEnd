package com.technologiyagroup.matrajayotish.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.technologiyagroup.matrajayotish.BuildConfig;

import org.jetbrains.annotations.NotNull;

public class Logger {

    public static final String DIALOG_REGISTER = "dialog_register";

    public static String log(String tag, String value){
        if (BuildConfig.DEBUG)
            Log.d(tag,value);
        return tag + " val: " + value;
    }
    public static String logError(String tag, String value){
        if (BuildConfig.DEBUG)
            Log.e(tag,value);
        return tag + " val: " + value;
    }
    public static void logToServer(Activity ac, String TAG, String description){

    }

    public static void logToServerFinish(Activity ac, String TAG, String description) {

    }

    public static void logToast(Context context, String s) {
        if (BuildConfig.DEBUG && Constants.ALLOW_LOGS)
//        if (false)
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static void timberE(@NotNull String TAG, @NotNull String s) {
        if (BuildConfig.DEBUG)
            Log.e(TAG,s);
    }
}
