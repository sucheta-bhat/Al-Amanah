package com.example.al_amanah.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.content.Intent;
import com.example.al_amanah.R;
import com.example.al_amanah.user.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    private static int splash_timer=5000;

    ImageView backgroundimage;
    Animation sideanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        backgroundimage=findViewById(R.id.backgroundimage);
        sideanim= AnimationUtils.loadAnimation(this,R.anim.side_anim);
        backgroundimage.setAnimation(sideanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             Intent intent=new Intent(getApplicationContext(), OnBoarding.class);
             startActivity(intent);
            finish();
            }
        },splash_timer);

    }
}
