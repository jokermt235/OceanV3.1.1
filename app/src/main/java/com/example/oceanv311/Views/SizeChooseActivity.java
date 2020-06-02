package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.oceanv311.Adapters.SizeChooseAdapter;
import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;

import java.util.ArrayList;
import java.util.Map;

public class SizeChooseActivity extends AppCompatActivity {
    private static String TAG = "SizeChooseActivity";
    private static int SIZE_REQUEST_CODE = 3;
    private RecyclerView sizeList;
    private ProgressBar progressBar;
    private Button applyButton;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_choose);
        sizeList = findViewById(R.id.sizeChooseList);
        progressBar = findViewById(R.id.sizeChooseProgressBar);
        applyButton = findViewById(R.id.sizeChooseApplyBtn);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SizeChooseAdapter adapter  = (SizeChooseAdapter)sizeList.getAdapter();
                String sizes = "";
                for(int i=0;i<sizeList.getLayoutManager().getItemCount();i++){
                    try {
                        if(adapter.getItems().get(i).get("checked") != null){
                            if((boolean)adapter.getItems().get(i).get("checked")){
                                sizes  = sizes + "," + adapter.getItems().get(i).get("name");
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if(sizes.length() > 0){
                    sizes = sizes.substring(1);
                }
                Log.d(TAG, sizes);
                Intent intent = new Intent();
                intent.putExtra("checkedItems",sizes);
                setResult(SIZE_REQUEST_CODE, intent);
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
