package com.example.al_amanah.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.al_amanah.Helperclasses.SliderAdapter;
import com.example.al_amanah.R;
import com.example.al_amanah.user.UserDashboard;

public class OnBoarding extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dots_Layout;
     TextView [] dots;
    SliderAdapter sliderAdapter;
    Button login,signup;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        viewPager=findViewById(R.id.slider);
        dots_Layout=findViewById(R.id.dots);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);
        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
              adddots(0);
               viewPager.addOnPageChangeListener(changeListener);
    }
       private void adddots(int position)
       {
        dots=new TextView[4];
        dots_Layout.removeAllViews();
        for(int i=0;i<dots.length;i++){

            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dots_Layout.addView(dots[i]);
        }
             if(dots.length>0){
                 dots[position].setTextColor(getResources().getColor(R.color.dots));
             }
       }
    public void skip(View view) {
        startActivity(new Intent(this, UserDashboard.class));
        finish();
    }
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
         adddots(position);
            if (position == 0) {
                login.setVisibility(View.INVISIBLE);
                signup.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                login.setVisibility(View.INVISIBLE);
                signup.setVisibility(View.INVISIBLE);
            } else if (position == 2) {
                login.setVisibility(View.INVISIBLE);
                signup.setVisibility(View.INVISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.side_anim);
                login.setAnimation(animation);
                signup.setAnimation(animation);
                login.setVisibility(View.VISIBLE);
                signup.setVisibility(View.VISIBLE);
            }



        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void loginpage(View view) {
        login.setBackgroundColor(getResources().getColor(R.color.dots));
        startActivity(new Intent(this, UserDashboard.class));
        finish();

    }
}
