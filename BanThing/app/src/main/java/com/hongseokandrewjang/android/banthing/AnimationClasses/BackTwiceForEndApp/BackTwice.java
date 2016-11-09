package com.hongseokandrewjang.android.banthing.AnimationClasses.BackTwiceForEndApp;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by HongSeok on 2016-11-09.
 */

public class BackTwice {
    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackTwice(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

}
