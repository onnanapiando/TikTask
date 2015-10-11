package com.mvp.buckler.timepressured;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddDurationActivity extends Activity {

    private EditText durationHours, durationMinutes, durationSeconds;
    int hoursTime, minutesTime, secondsTime;
    private TextView textViewShowTime; // will show the time
    private CountDownTimer countDownTimer; // built in android class
    // CountDownTimer
    private long totalTimeCountInMilliseconds; // total count down time in
    // milliseconds
    private long timeBlinkInMilliseconds; // start time of start blinking
    private boolean blink; // controls the blinking .. on and off

    public Button buttonStartTime;
    public Button buttonStopTime;

    public Button DoneAddingDuration;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addduration_layout);

        buttonStartTime = (Button) findViewById(R.id.btnStartTime);
        buttonStopTime = (Button) findViewById(R.id.btnStopTime);
        textViewShowTime = (TextView) findViewById(R.id.tvTimeCount);
        durationHours = (EditText) findViewById(R.id.durationHours);
        durationMinutes = (EditText) findViewById(R.id.durationMinutes);
        durationSeconds = (EditText) findViewById(R.id.durationSeconds);

        DoneAddingDuration = (Button) findViewById(R.id.inputTimeDuration);
    }

    View.OnClickListener myOnlyhandler = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.inputTimeDuration:
                    Intent mainIntent = new Intent(AddDurationActivity.this,CreateTaskActivity.class);
                    AddDurationActivity.this.startActivity(mainIntent);
                    AddDurationActivity.this.finish();


                case R.id.btnStartTime:
                    textViewShowTime.setTextAppearance(getApplicationContext(),
                            R.style.normalText);
                    setTimer();
                    buttonStopTime.setVisibility(View.VISIBLE);
                    buttonStartTime.setVisibility(View.GONE);
                    durationHours.setVisibility(View.GONE);
                    durationHours.setText("");

                    durationMinutes.setVisibility(View.GONE);
                    durationMinutes.setText("");

                    durationSeconds.setVisibility(View.GONE);
                    durationSeconds.setText("");
                    startTimer();

                    break;
                case R.id.btnStopTime:
                    // it was the second button
                    countDownTimer.cancel();
                    buttonStartTime.setVisibility(View.VISIBLE);
                    buttonStopTime.setVisibility(View.GONE);
                    durationHours.setVisibility(View.VISIBLE);
                    durationMinutes.setVisibility(View.VISIBLE);
                    durationSeconds.setVisibility(View.VISIBLE);
            }
        }


        public void setTimer() {
            int hoursTime = 0;
            int minutesTime = 0;
            int secondsTime = 0;

            int counttotal = 0;

            if (!durationHours.getText().toString().equals("")) {
                hoursTime = Integer.parseInt(durationHours.getText().toString());
            } else {
                counttotal = counttotal + 1;
            }
            //else {
            ///	Toast.makeText(MainActivity.this, "Please Enter Hours...",
            //			Toast.LENGTH_LONG).show();

            totalTimeCountInMilliseconds = totalTimeCountInMilliseconds + (60 * 60 * hoursTime * 1000);

            //timeBlinkInMilliseconds = 30 * 1000;

            if (!durationMinutes.getText().toString().equals("")) {
                minutesTime = Integer.parseInt(durationMinutes.getText().toString());
            } else {
                counttotal = counttotal + 1;
            }
            //else {
            //	Toast.makeText(MainActivity.this, "Please Enter Minutes...",
            //			Toast.LENGTH_LONG).show();

            totalTimeCountInMilliseconds = totalTimeCountInMilliseconds + (60 * minutesTime * 1000);

            //timeBlinkInMilliseconds = 30 * 1000;


            if (!durationSeconds.getText().toString().equals("")) {
                secondsTime = Integer.parseInt(durationSeconds.getText().toString());
                if (secondsTime < 30) {
                    Toast.makeText(AddDurationActivity.this, "Seconds should at least greater that 30",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                counttotal = counttotal + 1;
            }

            totalTimeCountInMilliseconds = totalTimeCountInMilliseconds + (secondsTime * 1000);

            timeBlinkInMilliseconds = 30 * 1000;

            if (counttotal == 3) {

                Toast.makeText(AddDurationActivity.this, "Please input time",
                        Toast.LENGTH_LONG).show();

            }
        }


        private void startTimer() {
            countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
                // 500 means, onTick function will be called at every 500
                // milliseconds

                @Override
                public void onTick(long leftTimeInMilliseconds) {
                    long seconds = leftTimeInMilliseconds / 1000;

                    if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                        textViewShowTime.setTextAppearance(getApplicationContext(),
                                R.style.blinkText);
                        // change the style of the textview .. giving a red
                        // alert style

                        if (blink) {
                            textViewShowTime.setVisibility(View.VISIBLE);
                            // if blink is true, textview will be visible
                        } else {
                            textViewShowTime.setVisibility(View.INVISIBLE);
                        }

                        blink = !blink; // toggle the value of blink
                    }

                    //textViewShowTime.setText(String.format("%02d", seconds / 60)
                    //+ ":" + String.format("%02d", seconds % 60));

                    textViewShowTime.setText(String.format("%02d:%02d:%02d", seconds / 3600,
                            (seconds % 3600) / 60, (seconds % 60)));

                    // format the textview to show the easily readable format

                }

                @Override
                public void onFinish() {
                    // this function will be called when the timecount is finished
                    textViewShowTime.setText("Time up!");
                    textViewShowTime.setVisibility(View.VISIBLE);
                    buttonStartTime.setVisibility(View.VISIBLE);
                    buttonStopTime.setVisibility(View.GONE);
                    durationHours.setVisibility(View.VISIBLE);
                    durationMinutes.setVisibility(View.VISIBLE);
                    durationSeconds.setVisibility(View.VISIBLE);
                }

            }.start();

        }
    };


    public void ToCreateTaskFromAddDuration(View view){
        Intent mainIntent = new Intent(AddDurationActivity.this,CreateTaskActivity.class);
        AddDurationActivity.this.startActivity(mainIntent);
        AddDurationActivity.this.finish();
    }

    public void GoTotaskActivityFromAddDuration(View v){
        Intent mainIntent = new Intent(AddDurationActivity.this,CountDownTimerActivity.class);
        AddDurationActivity.this.startActivity(mainIntent);
        AddDurationActivity.this.finish();
    }
}