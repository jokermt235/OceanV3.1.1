package com.example.oceanv311.Views;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.example.oceanv311.Adapters.FeedListAdapter;
import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;
import com.example.oceanv311.Views.Components.CustomBottomMenu;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppActivity {
    public static String NAME = "FeedActivity";
    private RecyclerView feedPostView;
    private ImageButton addPost;
    private LinearLayout bottomMenu;
    private FeedListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        CustomBottomMenu customBottomMenu = new CustomBottomMenu(this);
        bottomMenu  = findViewById(R.id.feedBottomMenuView);
        bottomMenu.addView(customBottomMenu.getView());
        feedPostView = findViewById(R.id.feedPostView);
        feedPostView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocal();
    }

    private void  loadLocal(){
        SimpleLoader.filter("post",new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                super.onResult(arrayList);
                FeedListAdapter adapter  = new FeedListAdapter(arrayList, getApplicationContext());
                feedPostView.setAdapter(adapter);
                Log.d(NAME , arrayList.toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
