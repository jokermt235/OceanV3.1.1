package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.oceanv311.Adapters.CategoryChooseAdapter;
import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;

import java.util.ArrayList;
import java.util.Map;

public class CategoryChooseActivity extends AppCompatActivity {
    private static String TAG = "CategoryChooseActivity";
    private Toolbar toolbar;
    private Button discardButton;
    private Button applyButton;
    private ProgressBar progressBar;
    private ListView categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_choose);
        toolbar = findViewById(R.id.categoryChooseToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = findViewById(R.id.categoryChooseProgressBar);
        discardButton = findViewById(R.id.categoryChooseDiscardBtn);
        applyButton   = findViewById(R.id.categoryChooseApplyBtn);
        categoryList = findViewById(R.id.categoryChooseList);
        loadLocal();
    }

    private void loadLocal(){
        progressBar.setVisibility(View.VISIBLE);
        SimpleLoader.filter("category", new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                super.onResult(arrayList);
                if(!arrayList.isEmpty()){
                    categoryList.setAdapter(new CategoryChooseAdapter(getApplicationContext(), arrayList));
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
