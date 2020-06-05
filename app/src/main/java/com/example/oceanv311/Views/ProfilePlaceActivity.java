package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.oceanv311.Adapters.ProfilePlaceAdapter;
import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;
import com.example.oceanv311.Views.Components.CustomBottomMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Map;

public class ProfilePlaceActivity extends AppActivity {
    private LinearLayout bottomMenu;
    private Toolbar  toolbar;
    private FloatingActionButton addNewPlaceButton;
    private ListView profilePlaceList;
    private ProgressBar progressBar;
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
        profilePlaceList = findViewById(R.id.profilePlaceList);
        progressBar = findViewById(R.id.profilePlaceProgressBar);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void loadLocal(){
        progressBar.setVisibility(View.VISIBLE);
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        SimpleLoader.filter("place","phone",user.getPhoneNumber(), new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                super.onResult(arrayList);
                if(!arrayList.isEmpty()) {
                    profilePlaceList.setAdapter(new ProfilePlaceAdapter(getApplicationContext(), arrayList));
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocal();
    }
}