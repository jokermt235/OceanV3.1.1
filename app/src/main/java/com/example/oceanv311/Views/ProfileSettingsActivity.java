package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.oceanv311.R;
import com.example.oceanv311.Views.Components.CustomBottomMenu;

public class ProfileSettingsActivity extends AppActivity {
    public static  String NAME  = "ProfileSettingsActivity";
    private LinearLayout bottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        CustomBottomMenu customBottomMenu = new CustomBottomMenu(this);
        bottomMenu  = findViewById(R.id.profileSettingsBottomMenuView);
        bottomMenu.addView(customBottomMenu.getView());
    }
}