package com.example.oceanv311.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.oceanv311.Adapters.CategoryChooseAdapter;
import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoryChooseActivity extends AppCompatActivity {
    private static String TAG = "CategoryChooseActivity";
    private static int CATEGORY_REQUEST_CODE = 2;
    private Toolbar toolbar;
    private Button discardButton;
    private Button applyButton;
    private ProgressBar progressBar;
    private ListView categoryList;
    ArrayList<Map<String,Object>> checkedList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_choose);
        toolbar = findViewById(R.id.categoryChooseToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = findViewById(R.id.categoryChooseProgressBar);
        discardButton = findViewById(R.id.categoryChooseDiscardBtn);
        categoryList = findViewById(R.id.categoryChooseList);
        applyButton   = findViewById(R.id.categoryChooseApplyBtn);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryChooseAdapter adapter  = (CategoryChooseAdapter) categoryList.getAdapter();
                checkedList.clear();
                for(int i=0;i<adapter.getCount(); i++){
                    Map<String, Object> map = (Map<String,Object>)adapter.getItem(i);
                    Log.d(TAG,map.toString());
                    if(map.get("checked") != null){
                        if((boolean)map.get("checked")){
                            Log.d(TAG, (String) map.get("name"));
                            checkedList.add(map);
                        }
                    }
                }
                Intent intent = new Intent();
                String checked[] = new String[checkedList.size()];
                for(int i=0;i<checkedList.size();i++){
                    checked[i] = (String)checkedList.get(i).get("name");
                }
                intent.putExtra("checkedItems",checked);
                setResult(CATEGORY_REQUEST_CODE, intent);
                finish();
            }
        });
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
