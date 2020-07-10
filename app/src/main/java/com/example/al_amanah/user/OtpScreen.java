package com.example.al_amanah.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.al_amanah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpScreen extends AppCompatActivity {
    PinView pin;
    ImageButton register,back;
    String codeBySystem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);
        pin = findViewById(R.id.pin);
        register = findViewById(R.id.next);
        back = findViewById(R.id.back);

        String phno = getIntent().getStringExtra("phno");
        sendCode(phno);
    }

    private void sendCode(String phno) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phno,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if(code!=null){
                        System.out.println("code"+code);
                        pin.setText(code);
                        verifyCode(code);

                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(OtpScreen.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential cr = PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(cr);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(OtpScreen.this,"Verfication Success",Toast.LENGTH_LONG).show();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(OtpScreen.this,"Verfication Failed! Try again",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    public void goToHomeFromOTP(View view) {
    }

    public void next(View view) {
        String code = pin.getText().toString();

        if(!code.isEmpty()){
            verifyCode(code);
            Intent i = new Intent(OtpScreen.this,End.class);
            startActivity(i);
        }
    }

    public void back(View view) {
        Intent i = new Intent(OtpScreen.this,UserDashboard.class);
        startActivity(i);
    }
}
