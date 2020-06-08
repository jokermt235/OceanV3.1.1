package com.example.oceanv311.Views;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Callbacks.PlaceForm.OnSaveClick;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;
import com.example.oceanv311.Views.Components.CustomBottomMenu;

import java.util.ArrayList;
import java.util.Map;


public class PlaceFormActivity extends AppActivity {
    private static String TAG = "PlaceFormActivity";
    private LinearLayout bottomMenu;
    private Toolbar toolbar;
    private LinearLayout saveButton;
    private EditText placeFormMarketName;
    private EditText placeFormRow;
    private EditText placeFormPavilion;
    private EditText placeFormFloor;
    private EditText placeFormWAPhone;
    private EditText placeFormTGPhone;
    private ProgressBar progressBar;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_form);
        bottomMenu  = findViewById(R.id.placeFormBottomMenuView);
        CustomBottomMenu customBottomMenu = new CustomBottomMenu(this);
        bottomMenu.addView(customBottomMenu.getView());
        toolbar = findViewById(R.id.placeFormToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        saveButton = findViewById(R.id.placeFormSaveBtn);
        saveButton.setOnClickListener(new OnSaveClick(this));
        placeFormMarketName = findViewById(R.id.placeFormMarketName);
        placeFormRow = findViewById(R.id.placeFormRow);
        placeFormPavilion = findViewById(R.id.placeFormPavilion);
        placeFormFloor = findViewById(R.id.placeFormFloor);
        placeFormWAPhone = findViewById(R.id.placeFormWA);
        placeFormTGPhone = findViewById(R.id.placeFormTG);
        progressBar = findViewById(R.id.placeFormProgressBar);
    }

    public EditText getPlaceFormMarketName(){
        return  placeFormMarketName;
    }

    public EditText getPlaceFormPavilion(){
        return  placeFormPavilion;
    }

    public EditText getPlaceFormRow(){
        return  placeFormRow;
    }

    public EditText getPlaceFormFloor(){
        return placeFormFloor;
    }

    public EditText getPlaceFormWAPhone(){
        return placeFormWAPhone;
    }

    public EditText getPlaceFormTGPhone(){
        return placeFormTGPhone;
    }

    public ProgressBar getProgressBar(){
        return progressBar;
    }

    public void setPath(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        try {
            String uid = intent.getStringExtra("uid");
            if(uid == null){
                toolbar.setTitle("Новое место");
            }else{
                toolbar.setTitle(R.string.place_edit_toolbar_title);
                SimpleLoader.filter("place","uid", uid, new OnFilterResult(){
                    @Override
                    public void onResult(ArrayList<Map<String, Object>> arrayList) {
                        super.onResult(arrayList);
                        if(!arrayList.isEmpty()){
                            Map<String, Object> place = arrayList.get(0);
                            placeFormFloor.setText((String)place.get("floor"));
                            placeFormRow.setText((String)place.get("row"));
                            placeFormMarketName.setText((String)place.get("market"));
                            placeFormPavilion.setText((String)place.get("pavilion"));
                            placeFormTGPhone.setText((String)place.get("TGPhone"));
                            placeFormWAPhone.setText((String)place.get("WAPhone"));
                            setPath((String)place.get("_ref"));
                        }
                    }
                });
            }
        }catch (Exception e){

        }
    }
}