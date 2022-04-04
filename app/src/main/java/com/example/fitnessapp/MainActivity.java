package com.example.fitnessapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Variable initialization

    // Number of calories textview
    TextView tv_mainCalNum;
    // Code to request permission
    int ACTIVITY_PERMISSION_CODE = 1;
    // Sensor manager
    SensorManager sensorManager;
    // Step sensor
    Sensor stepSensor;
    // General variables
    boolean running = false;
    float steps = 0f;
    float prevsteps = 0f;
    int cal = 0;

    /** on creation of main activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request activity setup
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION},1);

        //Buttons setup
        Button btn_exercise = (Button) findViewById(R.id.button_exercises);
        btn_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start Exercise activity
                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
            }
        });

        Button btn_foodlog = (Button) findViewById(R.id.button_foodlog);
        btn_foodlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start Foof log activity for result and sensing cal variable to it
                Intent i = new Intent(MainActivity.this, FoodLogActivity.class);
                i.putExtra("cal", cal);
                startActivityForResult(i, 1);
            }
        });

        Button btn_bmi = (Button) findViewById(R.id.button_bmi);
        btn_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start BMICalc activity
                startActivity(new Intent(MainActivity.this, BMICalcActivity.class));
            }
        });

        //SensorManager setup
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Load data from last use
        loadData();
        //Method to reset steps
        resetSteps();

        //Instantiation of calorie value
        tv_mainCalNum = (TextView) findViewById(R.id.tv_mainCalNum);
        //Set calorie value from last session or from food log activity
        tv_mainCalNum.setText(String.valueOf(cal));
    }

    /** Resuming sensor state/ Initialization of sensor
     */
    @Override
    protected void onResume() {
        super.onResume();

        //Sensor is running
        running = true;

        //StepSensor setup/ get specific sensor
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        //Check if device has step sensor
        if (stepSensor == null) {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show();
        } else {
            //Setup SensorListener
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    /** Upon sensor update
     *
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

        //get textview
        TextView tv_stepsTaken = (TextView) findViewById(R.id.stepsTaken_tv);

        //if the sensor is running
        if (running) {
            //Retrive number of total steps done from Sensor
            steps = event.values[0];

            //Get actual value of current steps, covert to string and display
            int newSteps = ((int) steps) - ((int) prevsteps);
            String currentStepsString = String.valueOf(newSteps);

            tv_stepsTaken.setText(currentStepsString);

        }
    }



    /** Reset step counter
     */
    protected void resetSteps() {

        //Step counter button
        TextView tv_stepsTaken = (TextView) findViewById(R.id.stepsTaken_tv);
        tv_stepsTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Long press to reset steps", Toast.LENGTH_SHORT).show();
            }
        });
        //Step counter long press button
        tv_stepsTaken.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Steps are saved to prevsteps
                prevsteps = steps;
                //Textview is resetted
                tv_stepsTaken.setText("0");

                //save data
                saveData();

                return true;
            }
        });
    }

    /** Method to save data once the app is closed
     */
    protected void saveData() {

        //Save data from session using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("key", Context.MODE_PRIVATE);
        //Edit pair
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("steps", prevsteps);
        editor.putInt("cals", cal);
        editor.apply();
    }

    /** Method to load data from last session
     */
    protected void loadData() {

        //Retrieve data from session using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("key", Context.MODE_PRIVATE);
        float savedNumber = sharedPreferences.getFloat("steps", 0f);
        int savedCals = sharedPreferences.getInt("cals", 0);

        //Save retrieved value back to var
        prevsteps = savedNumber;
        cal = savedCals;
    }

    /** on sensor accuracy changed (required)
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    /** Method to check whether permission request is accepted
     *
     * @param requestCode request permission code
     * @param permissions list of permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Check for right permission
        if(requestCode == ACTIVITY_PERMISSION_CODE){
            //Check if permission was granted
            if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permission denied to read physical activity, certain features might not work", Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }

    /** Method get resukt from food log activity
     *
     * @param requestCode request permission code
     * @param resultCode result code from activity
     * @param data Intent in question
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //check whether the request code is from the correct activity
        if(requestCode == 1){
            //get correct value
            int new_cal = data.getIntExtra("cal", 0);
            //set and display value in textview
            cal = new_cal;
            saveData();
            tv_mainCalNum.setText(String.valueOf(cal));
        }
    }
}