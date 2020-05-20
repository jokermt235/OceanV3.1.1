package com.example.oceanv311.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oceanv311.Modules.Auth;
import com.example.oceanv311.R;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class CodeConfirmActivity extends AppCompatActivity {

    private Button sendCodeBtn;
    private EditText codeConfirm1;
    private EditText codeConfirm2;
    private EditText codeConfirm3;
    private EditText codeConfirm4;
    private EditText codeConfirm5;
    private EditText codeConfirm6;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_confirm);
        sendCodeBtn = findViewById(R.id.codeConfirmLogin);
        codeConfirm1 = findViewById(R.id.codeConfirmCode1);
        codeConfirm2 = findViewById(R.id.codeConfirmCode2);
        codeConfirm3 = findViewById(R.id.codeConfirmCode3);
        codeConfirm4 = findViewById(R.id.codeConfirmCode4);
        codeConfirm5 = findViewById(R.id.codeConfirmCode5);
        codeConfirm6 = findViewById(R.id.codeConfirmCode6);

        verificationId = getIntent().getStringExtra("verificationId");

        sendCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, codeConfirm1.getText().toString());
                Auth.signInWithPhoneCredentials(CodeConfirmActivity.this, phoneAuthCredential);
            }
        });
    }
}
