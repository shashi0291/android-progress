package com.badlogic.androidgames.glbasics;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GLBasicsStarter extends ListActivity {

    String tests[] = {
            "GLSurfaceViewTest"
            };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            tests
            ));
    }

    @Override
    public void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
        String testName = tests[position];
        try {
            Class class_ = Class.forName("com.badlogic.androidgames.glbasics." + testName);
            Intent intent = new Intent(this, class_);
            startActivity(intent);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
