package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.oceanv311.R;
import com.example.oceanv311.Views.Components.CustomBottomMenu;

public class ProfileActivity extends AppActivity {

    public static  String NAME  = "ProfileActivity";
    private LinearLayout bottomMenu;
    private ImageButton settingsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        CustomBottomMenu customBottomMenu = new CustomBottomMenu(this);
        bottomMenu  = findViewById(R.id.profileBottomMenuView);
        bottomMenu.addView(customBottomMenu.getView());
        settingsBtn = findViewById(R.id.profileSettingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileSettingsActivity.class));
            }
        });
    }
}
