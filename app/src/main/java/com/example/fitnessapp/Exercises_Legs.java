package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Exercises_Legs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_legs);

        //Animations

        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        AnimationDrawable anim = new AnimationDrawable();
        anim.addFrame(getDrawable(R.drawable.leg_press1), 1000);
        anim.addFrame(getDrawable(R.drawable.leg_press2), 1000);
        iv1.setImageDrawable(anim);
        anim.start();

        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        AnimationDrawable anim1 = new AnimationDrawable();
        anim1.addFrame(getDrawable(R.drawable.leg_curl1), 1000);
        anim1.addFrame(getDrawable(R.drawable.leg_curl2), 1000);
        iv2.setImageDrawable(anim1);
        anim1.start();

        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        AnimationDrawable anim2 = new AnimationDrawable();
        anim2.addFrame(getDrawable(R.drawable.squat1), 1000);
        anim2.addFrame(getDrawable(R.drawable.squat2), 1000);
        iv3.setImageDrawable(anim2);
        anim2.start();
    }
}