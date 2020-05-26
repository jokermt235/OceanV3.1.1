package com.example.oceanv311.View.Components;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oceanv311.R;
import com.example.oceanv311.View.AppActivity;
import com.example.oceanv311.View.ChooseImageActivity;
import com.example.oceanv311.View.FeedActivity;

public class CustomBottomMenu {
    private  AppCompatActivity activity;
    private  Context context;
    private View view;
    private ImageButton homeButton;
    private ImageButton searchButton;
    private ImageButton postButton;
    private ImageButton myRepostButton;
    private ImageButton profileButton;

    public View getView(){
        return  view;
    }

    public CustomBottomMenu(final AppActivity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        view = LayoutInflater.from(context).inflate(R.layout.simple_menu_bottom_layout,null);
        // Init homeButton
        homeButton = view.findViewById(R.id.SimpleBottomMenuHomeBtn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!activity.NAME.equals("FeedActivity"))
                {
                    activity.startActivity(new Intent(context, FeedActivity.class));
                    activity.finish();
                }

            }
        });
        searchButton = view.findViewById(R.id.SimpleBottomMenuSearchBtn);
        //Init postButton
        postButton = view.findViewById(R.id.SimpleBottomMenuAddBtn);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!activity.NAME.equals("ChooseImageActivity"))
                {
                    activity.startActivity(new Intent(context, ChooseImageActivity.class));
                    activity.finish();
                }
            }
        });
        myRepostButton = view.findViewById(R.id.SimpleBottomMenuSavesBtn);
        profileButton = view.findViewById(R.id.SimpleBottomMenuProfileBtn);
    }


}
