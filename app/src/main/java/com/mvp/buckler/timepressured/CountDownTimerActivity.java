package com.mvp.buckler.timepressured;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by onnana on 10/9/15.
 */
public class CountDownTimerActivity extends AddDurationActivity   {

    private Button buttonStartTime, buttonStopTime;
    private EditText durationHours, durationMinutes, durationSeconds;
    int hoursTime, minutesTime, secondsTime;
    private TextView textViewShowTime; // will show the time
    private CountDownTimer countDownTimer; // built in android class
    // CountDownTimer
    private long totalTimeCountInMilliseconds; // total count down time in
    // milliseconds
    private long timeBlinkInMilliseconds; // start time of start blinking
    private boolean blink; // controls the blinking .. on and off

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntimer_layout);


    }
    View.OnClickListener myOnlyhandler = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnStartTime:
                    textViewShowTime.setTextAppearance(getApplicationContext(),
                            R.style.normalText);
                    startTimer();
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

                textViewShowTime.setText(String.format("%02d:%02d:%02d", seconds/ 3600,
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
}