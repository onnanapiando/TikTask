package com.mvp.buckler.timepressured;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xdionie on 10/6/2015.
 */
public class HomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);


        TextView createtask =  (TextView) findViewById(R.id.CreateTaskTextView);
        TextView work =  (TextView) findViewById(R.id.WorkTextView);
        TextView progress =  (TextView) findViewById(R.id.ProgressTextView);



    }


    public void GoToCreateTaskPage(View v){
        Intent mainIntent = new Intent(HomeActivity.this,CreateTaskActivity.class);
        HomeActivity.this.startActivity(mainIntent);
        HomeActivity.this.finish();
    }

    public void ToDisplayTasksFromHome(View view){
        Intent mainIntent = new Intent(HomeActivity.this,TaskActivity.class);
        HomeActivity.this.startActivity(mainIntent);
        HomeActivity.this.finish();
    }

    public void ToProgressFromHome(View view){
        Intent mainIntent = new Intent(HomeActivity.this,ProgressActivity.class);
        HomeActivity.this.startActivity(mainIntent);
        HomeActivity.this.finish();
    }
}
