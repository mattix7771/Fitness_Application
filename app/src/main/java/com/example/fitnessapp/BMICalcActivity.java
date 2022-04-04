package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalcActivity extends AppCompatActivity {

    /** Upon creation of activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalc);

        //Initialization of layout elements
        TextView height_unit = (TextView) findViewById(R.id.height_unit);
        TextView weight_unit = (TextView) findViewById(R.id.weight_unit);
        RadioButton metric = (RadioButton) findViewById(R.id.rb_metric);
        RadioButton imperial = (RadioButton) findViewById(R.id.rb_imperial);
        Button calculate = (Button) findViewById(R.id.btn_calculate);
        TextView result = (TextView) findViewById(R.id.tv_result);
        EditText height_input = (EditText) findViewById(R.id.TextNumberHeight);
        EditText weight_input = (EditText) findViewById(R.id.TextNumberWeight);
        TextView link = (TextView) findViewById(R.id.tv_link);

        //set link to textview
        link.setMovementMethod(LinkMovementMethod.getInstance());

        //set startic unit
        metric.setChecked(true);
        imperial.setChecked(false);

        //On click of metric radio button
        metric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Turn off opposite radio button
                imperial.setChecked(false);
                //Change units
                height_unit.setText("m");
                weight_unit.setText("kg");
            }
        });

        //On click of imperial radio button
        imperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Turn off opposite radio button
                metric.setChecked(false);
                //Change units
                height_unit.setText("in");
                weight_unit.setText("lbs");
            }
        });

        //On click of calculate button - compute result
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Variable initialization
                double bmi = 0;
                String bmiString = "";

                //Check for user input
                if(height_input.getText().length() < 1 || weight_input.getText().length() < 1){
                    Toast.makeText(getApplicationContext(), "Not all fields have been filled", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Check if metric radio button is checked
                else if(metric.isChecked()){
                    //get int from textview
                    int height = Integer.parseInt(height_input.getText().toString());
                    int weight = Integer.parseInt(weight_input.getText().toString());

                    //Formula - weight/height^2
                    //Calculate bmi
                    double h = height*height;
                    bmi = Math.round((weight/h*10000)*100.0)/100.0;
                    bmiString = Double.toString(bmi);
                }
                //Check if imperial radio button is checked
                else if(imperial.isChecked()){
                    //get int from textview
                    int height = Integer.parseInt(height_input.getText().toString());
                    int weight = Integer.parseInt(weight_input.getText().toString());

                    //Formula BMI = (weight x 703)/(height^2)
                    //Calculate bmi
                    double h = height*height;
                    bmi = Math.round((weight*703/h)*100.0)/100.0;
                    bmiString = Double.toString(bmi);
                }
                //Check BMI range and display according text to textview
                if(bmi < 13){
                    result.setText("BMI - " + bmiString + " " + getApplicationContext().getString(R.string.bmi_extUnder));
                }
                else if(bmi <= 17){
                    result.setText("BMI - " + bmiString + " " + getApplicationContext().getString(R.string.bmi_under));
                }
                else if(bmi == 18 || bmi == 19){
                    result.setText("BMI - " + bmiString + " " + getApplicationContext().getString(R.string.bmi_underHealthy));
                }
                else if(bmi <= 24){
                    result.setText("BMI - " + bmiString + " " + getApplicationContext().getString(R.string.bmi_healthy));
                }
                else if(bmi <= 29){
                    result.setText("BMI - " + bmiString + " " + getApplicationContext().getString(R.string.bmi_over));
                }
                else if(bmi <= 39){
                    result.setText("BMI - " + bmiString + " " + getApplicationContext().getString(R.string.bmi_obese));
                }
                else if(bmi >= 40){
                    result.setText("BMI - " + bmiString + " " + getApplicationContext().getString(R.string.bmi_extObese));
                }


            }
        });
    }
}