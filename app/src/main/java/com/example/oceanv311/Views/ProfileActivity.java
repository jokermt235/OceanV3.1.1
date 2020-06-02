package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.oceanv311.R;
import com.example.oceanv311.Views.Components.CustomBottomMenu;

public class ProfileActivity extends AppActivity {

    public static  String NAME  = "ProfileActivity";
    private LinearLayout bottomMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        CustomBottomMenu customBottomMenu = new CustomBottomMenu(this);
        bottomMenu  = findViewById(R.id.profileBottomMenuView);
        bottomMenu.addView(customBottomMenu.getView());
    }
}
