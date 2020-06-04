package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oceanv311.R;
import com.example.oceanv311.Views.Components.CustomBottomMenu;

public class ProfileSettingsActivity extends AppActivity {
    public static  String NAME  = "ProfileSettingsActivity";
    private LinearLayout bottomMenu;
    private TextView profileSettingsEditLink;
    private TextView profileSettingsPlaceLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        CustomBottomMenu customBottomMenu = new CustomBottomMenu(this);
        bottomMenu  = findViewById(R.id.profileSettingsBottomMenuView);
        bottomMenu.addView(customBottomMenu.getView());
        profileSettingsEditLink = findViewById(R.id.profileSettingsEditLink);
        profileSettingsEditLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileFormActivity.class));
            }
        });
        profileSettingsPlaceLink = findViewById(R.id.profileSettingsPlaceLink);
        profileSettingsPlaceLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfilePlaceActivity.class));
            }
        });
    }
}