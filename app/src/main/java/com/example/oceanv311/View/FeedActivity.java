package com.example.oceanv311.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.oceanv311.R;
import com.example.oceanv311.View.Components.CustomBottomMenu;

public class FeedActivity extends AppActivity {
    public static String NAME = "FeedActivity";
    private RecyclerView feedPostView;
    private ImageButton addPost;
    private LinearLayout bottomMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        CustomBottomMenu customBottomMenu = new CustomBottomMenu(this);
        bottomMenu  = findViewById(R.id.feedBottomMenuView);
        bottomMenu.addView(customBottomMenu.getView());
    }
}
