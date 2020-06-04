package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.oceanv311.R;
import com.example.oceanv311.Views.Components.CustomBottomMenu;

public class ProfilePlaceActivity extends AppActivity {
    private LinearLayout bottomMenu;
    private Toolbar  toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_place);
        bottomMenu  = findViewById(R.id.profilePlaceBottomMenuView);
        CustomBottomMenu customBottomMenu = new CustomBottomMenu(this);
        bottomMenu.addView(customBottomMenu.getView());
        toolbar = findViewById(R.id.profilePlaceToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}