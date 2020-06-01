package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oceanv311.Modules.Auth;
import com.example.oceanv311.R;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class CodeConfirmActivity extends AppCompatActivity {

    private TextView codeConfirmLabel;
    private TextView sendCodeBtn;
    private EditText codeConfirm;
    private String verificationId;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_confirm);
        phone = getIntent().getStringExtra("phone");
        //Init codeConfirm EditText
        sendCodeBtn = findViewById(R.id.codeConfirmLogin);
        codeConfirm = findViewById(R.id.codeConfirmCode);

        //Init codeConfirmLabel TextView

        codeConfirmLabel = findViewById(R.id.codeConfirmLabel);
        codeConfirmLabel.setText(String.format(getResources().getString(R.string.code_info), phone));

        verificationId = getIntent().getStringExtra("verificationId");

        sendCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeConfirmString = codeConfirm.getText().toString();

                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, codeConfirmString);
                Auth.signInWithPhoneCredentials(CodeConfirmActivity.this, phoneAuthCredential);
            }
        });
    }
}
