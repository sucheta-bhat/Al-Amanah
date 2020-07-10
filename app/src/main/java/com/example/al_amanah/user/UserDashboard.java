package com.example.al_amanah.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.al_amanah.R;
import com.example.al_amanah.common.OnBoarding;

public class UserDashboard extends AppCompatActivity {
ImageButton move_to_on,on_btn;
EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        move_to_on=findViewById(R.id.movetoon);
        on_btn=findViewById(R.id.onbtn);
        phone=findViewById(R.id.phoneno);
    }

    public void back(View view) {
        startActivity(new Intent(this, OnBoarding.class));
        finish();
    }

    public void onbthfun(View view) {
       on_btn.setVisibility(View.VISIBLE);
       move_to_on.setVisibility(View.INVISIBLE);
    }

    public void otp(View view) {

        String number = phone.getText().toString().trim();
        if (number.isEmpty() || number.length() < 10) {
            phone.setError("Valid number is required");
            phone.requestFocus();
            return;
        }
        String phno = "+91"  + number;
        Intent intent = new Intent(UserDashboard.this, OtpScreen.class);
        intent.putExtra("phno", phno);
        startActivity(intent);
    }

}



