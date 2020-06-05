package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.oceanv311.R;
import com.example.oceanv311.Views.Components.CustomBottomMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfilePlaceActivity extends AppActivity {
    private LinearLayout bottomMenu;
    private Toolbar  toolbar;
    private FloatingActionButton addNewPlaceButton;
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
        addNewPlaceButton = findViewById(R.id.profilePlaceAddBtn);
        addNewPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PlaceFormActivity.class));
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}