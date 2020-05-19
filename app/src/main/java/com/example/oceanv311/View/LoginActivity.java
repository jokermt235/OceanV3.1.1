package com.example.oceanv311.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oceanv311.Modules.Auth;
import com.example.oceanv311.R;

public class LoginActivity extends AppCompatActivity {

    private EditText phone;
    private Button loginButton;
    private LoginActivity activity  = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Init phone EditText
        phone = findViewById(R.id.loginPhone);
        // Init loginButton  Button
        loginButton = findViewById(R.id.login_submit_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.signIn( activity, phone.getText().toString());
            }
        });
    }
}
