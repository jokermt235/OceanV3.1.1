package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.oceanv311.Adapters.SizeChooseAdapter;
import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;

import java.util.ArrayList;
import java.util.Map;

public class SizeChooseActivity extends AppCompatActivity {
    private static String TAG = "SizeChooseActivity";
    private RecyclerView sizeList;
    private ProgressBar progressBar;
    private Button applyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_choose);
        sizeList = findViewById(R.id.sizeChooseList);
        progressBar = findViewById(R.id.sizeChooseProgressBar);
        applyButton = findViewById(R.id.sizeChooseApplyBtn);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void loadLocal(){
        progressBar.setVisibility(View.VISIBLE);
        SimpleLoader.filter("size", new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                super.onResult(arrayList);
                if(!arrayList.isEmpty()){
                    SizeChooseAdapter sizeChooseAdapter = new SizeChooseAdapter(arrayList, getApplicationContext());
                    sizeList.setAdapter(sizeChooseAdapter);
                    Log.d(TAG, arrayList.toString());
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
