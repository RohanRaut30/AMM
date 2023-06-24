package com.example.amm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash_activity extends AppCompatActivity {

    private static int timer = 4000;

    ImageView imageView;
    Animation upperanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash_activity.this, MainActivity.class);
                startActivity(i);
            }
        }, 3000); // you can increase or decrease the timelimit of your screen






    }
}