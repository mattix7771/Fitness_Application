package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Exercises_Arms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_arms);

        //Animations

        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        AnimationDrawable anim = new AnimationDrawable();
        anim.addFrame(getDrawable(R.drawable.bicept_curl1), 1000);
        anim.addFrame(getDrawable(R.drawable.bicept_curl2), 1000);
        iv1.setImageDrawable(anim);
        anim.start();

        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        AnimationDrawable anim1 = new AnimationDrawable();
        anim1.addFrame(getDrawable(R.drawable.hammer_curl1), 1000);
        anim1.addFrame(getDrawable(R.drawable.hammer_curl2), 1000);
        iv2.setImageDrawable(anim1);
        anim1.start();

        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        AnimationDrawable anim2 = new AnimationDrawable();
        anim2.addFrame(getDrawable(R.drawable.triceps_extension1), 1000);
        anim2.addFrame(getDrawable(R.drawable.triceps_extension2), 1000);
        iv3.setImageDrawable(anim2);
        anim2.start();

        ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
        AnimationDrawable anim3 = new AnimationDrawable();
        anim3.addFrame(getDrawable(R.drawable.tricept_fliback1), 1000);
        anim3.addFrame(getDrawable(R.drawable.tricept_fliback2), 1000);
        iv4.setImageDrawable(anim3);
        anim3.start();
    }
}