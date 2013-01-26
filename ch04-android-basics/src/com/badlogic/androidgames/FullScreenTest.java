package com.badlogic.androidgames;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public class FullScreenTest extends SingleTouchTest
{
    WakeLock wakeLock = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        // also prevent the screen from sleeping
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        super.onCreate(savedInstanceState);
    }

    protected void onResume()
    {
        super.onResume();
        if (wakeLock != null)
        {
            wakeLock.acquire();
        }
    }

    protected void onPause()
    {
        super.onPause();
        if (wakeLock != null)
        {
            wakeLock.release();
        }
    }


}
