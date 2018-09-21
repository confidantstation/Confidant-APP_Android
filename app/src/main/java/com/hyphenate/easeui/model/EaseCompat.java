package com.hyphenate.easeui.model;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.WindowManager;


import com.stratagile.pnrouter.utils.RxFileTool;

import java.io.File;

/**
 * Created by zhangsong on 18-6-6.
 */

public class EaseCompat {

    public static Uri getUriForFile(Context context, File file) {
        Uri contentUri = RxFileTool.getUriForFile(context, file);
        return contentUri;
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", file);
        } else {
            return Uri.fromFile(file);
        }*/
    }

    public static int getSupportedWindowType() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            return WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
    }
}
