package com.mvp.buckler.timepressured;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xdionie on 10/7/2015.
 */
public class CreateTaskActivity  extends Activity {

    private RadioButton radioTypeButton;
    private RadioGroup radiotypeGroup;
    public Button addTask;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createtask_layout);

        TextView createtask =  (TextView) findViewById(R.id.CreateTaskTextView);
        TextView work =  (TextView) findViewById(R.id.WorkTextView);
        TextView progress =  (TextView) findViewById(R.id.ProgressTextView);

        Button addDuration = (Button) findViewById(R.id.addDuration);
        Button addTask = (Button) findViewById(R.id.addTask);

        //addListenerOnButton();
    }

    public void GoToTaskActivity(View v){
        Intent mainIntent = new Intent(CreateTaskActivity.this,TaskActivity.class);
        CreateTaskActivity.this.startActivity(mainIntent);
        CreateTaskActivity.this.finish();
    }


    public void GoToAddDurationPage(View v){
        Intent mainIntent = new Intent(CreateTaskActivity.this,AddDurationActivity.class);
        CreateTaskActivity.this.startActivity(mainIntent);
        CreateTaskActivity.this.finish();
    }

    public void GoHomeFromCreateTask(View v){
        Intent mainIntent = new Intent(CreateTaskActivity.this,HomeActivity.class);
        CreateTaskActivity.this.startActivity(mainIntent);
        CreateTaskActivity.this.finish();
    }

    public void addListenerOnButton() {

        radiotypeGroup  = (RadioGroup) findViewById(R.id.radioType);
        //addTask = (Button) findViewById(R.id.addTask);

        addTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radiotypeGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioTypeButton  = (RadioButton) findViewById(selectedId);

                Toast.makeText(CreateTaskActivity.this,
                        radioTypeButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }
}
