package com.demo.quizapp.Utils;

import android.util.Log;

public class Global {


    private static boolean logEnabled = true;

    public static void log(String tag, String msg) {
        if (logEnabled)
            Log.e(tag, msg);
    }
}
