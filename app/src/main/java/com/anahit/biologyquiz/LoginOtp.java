package com.anahit.biologyquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anahit.biologyquiz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginOtp extends AppCompatActivity {
    Button verifyOtpButton, resendLinkButton;
    FirebaseAuth mAuth;
    TextView otpInstructionText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);

        verifyOtpButton = findViewById(R.id.verifyOtpButton);
        otpInstructionText = findViewById(R.id.otpInstructionText);
        mAuth = FirebaseAuth.getInstance();

        verifyOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    user.reload().addOnCompleteListener(task -> {
                        if (user.isEmailVerified()) {
                            Toast.makeText(LoginOtp.this, "Email verification successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginOtp.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginOtp.this, "Email not verified. Please check your email.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(LoginOtp.this, "User not found. Please log in again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}