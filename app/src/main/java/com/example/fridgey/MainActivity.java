package com.example.fridgey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadIn();


    }

    public void LoadIn(){
        ImageView sargarepa = findViewById(R.id.logo_img);
        TextView title = findViewById(R.id.title_fridgey);
        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotation);


        sargarepa.startAnimation(fadeIn);
        title.startAnimation(fadeIn);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("START ANIMACIJE", "onAnimationStart: pokrenut!");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


}

