package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oceanv311.Callbacks.OnSavedResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;

import java.util.HashMap;
import java.util.Map;

public class UserActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userRow;
    private EditText userPlace;
    private EditText userFloor;
    private EditText userWAPhone;
    private EditText userTGPhone;
    private Button userSaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //Init userName EditText
        userName  = findViewById(R.id.userNameInput);
        //Init userRow EditText
        userRow = findViewById(R.id.userRow);
        //Init userPlace EditText
        userPlace = findViewById(R.id.userPlace);
        //Init userFloor EditText
        userFloor = findViewById(R.id.userFloor);
        //Init userWAPhone EditText
        userWAPhone = findViewById(R.id.userWAPhone);
        //Init userTGPhone EditText
        userTGPhone = findViewById(R.id.userTGPhone);
        //Init userSaveBtn Button
        userSaveBtn = findViewById(R.id.userSaveBtn);
        userSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                user.put("name", userName.getText().toString());
                user.put("row", userRow.getText().toString());
                user.put("place", userPlace.getText().toString());
                user.put("floor", userFloor.getText().toString());
                user.put("phoneWA", userWAPhone.getText().toString());
                user.put("phoneTG", userTGPhone.getText().toString());
                user.put("market","Садавод");
                SimpleLoader.save("user",user, new OnSavedResult(){
                    @Override
                    public void onSave(boolean result) {
                        super.onSave(result);
                        if(result){
                            startActivity(new Intent(getApplicationContext(), FeedActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
