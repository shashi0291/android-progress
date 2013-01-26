package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.view.WindowManager;

public class AccelerometerTest extends Activity implements SensorEventListener
{
    TextView textView;
    StringBuilder builder = new StringBuilder();

    int screenRotation;
    static final int ACCELEROMETER_AXIS_SWAP[][] = {
        {1, -1, 0, 1}, // ROTATION_0
        {-1, -1, 1, 0}, // ROTATION_90
        {-1, 1, 0, 1}, // ROTATION_180
        {1, 1, 1, 0} // ROTATION_270
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);

        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0)
        {
            textView.setText("No accelerometer installed");
        }
        else
        {
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            if (!manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME))
            {
                textView.setText("Couldn't register sensor listener");
            }
        }
    }

    public void onResume()
    {
        WindowManager windowMgr = (WindowManager) getSystemService(Activity.WINDOW_SERVICE);
        // getOrientation was deprecated in Android 8 but is the same as
        // getRotation() which is the roation fromt he natural orientation
        // of the device
        screenRotation = windowMgr.getDefaultDisplay().getOrientation();
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        final int[] axis = ACCELEROMETER_AXIS_SWAP[screenRotation];
        float screenX = (float) axis[0] * event.values[axis[2]];
        float screenY = (float) axis[1] * event.values[axis[3]];
        float screenZ = event.values[2];

        builder.setLength(0);
        builder.append("x: ");
        builder.append(screenX);
        builder.append("y: ");
        builder.append(screenY);
        builder.append("z: ");
        builder.append(screenZ);
        textView.setText(builder.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        // nothing here, just stubbing for completeness sake
    }
}
