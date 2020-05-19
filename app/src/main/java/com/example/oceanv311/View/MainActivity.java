package com.example.oceanv311.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.oceanv311.R;

import static com.example.oceanv311.Modules.Auth.isSigned;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isSigned()){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    }
}
