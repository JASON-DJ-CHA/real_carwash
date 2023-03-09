package com.jason.example.toyproject_carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;


public class intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        moveMain(1500);

    }

    private void moveMain(int sec){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(intro.this, LoginActivity.class);

                startActivity(intent);

                finish();
            }
        },sec);
    }

}