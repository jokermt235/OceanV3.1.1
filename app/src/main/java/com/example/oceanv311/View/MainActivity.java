package com.example.oceanv311.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;

import java.util.ArrayList;
import java.util.Map;

import static com.example.oceanv311.Modules.Auth.isSigned;

public class MainActivity extends AppCompatActivity {
    private static  String  TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isSigned()){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }else {
            SimpleLoader.filter("user", new OnFilterResult(){
                @Override
                public void onResult(ArrayList<Map<String, Object>> arrayList) {
                    super.onResult(arrayList);
                    Log.d(TAG,arrayList.toString());
                    if(!arrayList.isEmpty()){
                        startActivity(new Intent(getApplicationContext(), FeedActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        finish();
                    }
                }
            });
        }
    }
}
