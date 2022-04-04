package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //Initialization of variables
        ScrollView sv = (ScrollView) findViewById(R.id.scrollView);
        Button btn_buildMuscle = (Button) findViewById(R.id.btn_buildMuscle);
        Button btn_loseFat = (Button) findViewById(R.id.btn_loseFat);
        Button btn_arms = (Button) findViewById(R.id.btn_arms);
        Button btn_shoulders = (Button) findViewById(R.id.btn_shoulders);
        Button btn_chest = (Button) findViewById(R.id.btn_chestBack);
        Button btn_abs = (Button) findViewById(R.id.btn_abs);
        Button btn_legs = (Button) findViewById(R.id.btn_legs);
        Button btn_cardio = (Button) findViewById(R.id.btn_cardio);

        //Buttons that start respective activities

        btn_buildMuscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Exercises_BuildMuscle.class));
            }
        });

        btn_loseFat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Exercises_LoseFat.class));
            }
        });

        btn_arms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Exercises_Arms.class));
            }
        });

        btn_shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Exercises_Shoulders.class));
            }
        });

        btn_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Exercises_Chest.class));
            }
        });

        btn_abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Exercises_Abs.class));
            }
        });

        btn_legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Exercises_Legs.class));
            }
        });

        btn_cardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Exercises_Cardio.class));
            }
        });
    }
}