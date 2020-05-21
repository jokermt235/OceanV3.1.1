package com.example.oceanv311.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.oceanv311.R;

public class FeedActivity extends AppCompatActivity {
    private RecyclerView feedPostView;
    private ImageButton addPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }
}
