package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Exercises_Chest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_chest);

        //Animations

        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        AnimationDrawable anim = new AnimationDrawable();
        anim.addFrame(getDrawable(R.drawable.barbell_bench_press1), 1000);
        anim.addFrame(getDrawable(R.drawable.barbell_bench_press2), 1000);
        iv1.setImageDrawable(anim);
        anim.start();

        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        AnimationDrawable anim1 = new AnimationDrawable();
        anim1.addFrame(getDrawable(R.drawable.push_up1), 1000);
        anim1.addFrame(getDrawable(R.drawable.push_up2), 1000);
        iv2.setImageDrawable(anim1);
        anim1.start();

        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        AnimationDrawable anim2 = new AnimationDrawable();
        anim2.addFrame(getDrawable(R.drawable.deadlift1), 1000);
        anim2.addFrame(getDrawable(R.drawable.deadlift2), 1000);
        iv3.setImageDrawable(anim2);
        anim2.start();

        ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
        AnimationDrawable anim3 = new AnimationDrawable();
        anim3.addFrame(getDrawable(R.drawable.bent_over_row1), 1000);
        anim3.addFrame(getDrawable(R.drawable.bent_over_row2), 1000);
        iv4.setImageDrawable(anim3);
        anim3.start();
    }
}