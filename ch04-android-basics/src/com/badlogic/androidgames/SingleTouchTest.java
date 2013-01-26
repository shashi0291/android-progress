package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class SingleTouchTest extends Activity implements OnTouchListener
{
    StringBuilder builder = new StringBuilder();
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("Touch and drag (on finger only)!");
        textView.setOnTouchListener(this);
        setContentView(textView);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        try
        {
            // necessary for Android 1.5 as it reports A TON of touches
            Thread.sleep(16);
        }
        catch (InterruptedException e)
        {
            // exit now if unexpectedly quit
            // report that the event was not processed
            return false;
        }
        finally
        {
            // if we got this far then process the event
            builder.setLength(0);
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    builder.append("down, ");
                    break;
                case MotionEvent.ACTION_MOVE:
                    builder.append("move, ");
                    break;
                case MotionEvent.ACTION_CANCEL:
                    builder.append("cancel, ");
                    break;
                case MotionEvent.ACTION_UP:
                    builder.append("up, ");
                    break;
            }
            builder.append(event.getX());
            builder.append(", ");
            builder.append(event.getY());
            String text = builder.toString();
            Log.d("SingleTouchTest", text);
            textView.setText(text);
            return true;
        }
    }
}
