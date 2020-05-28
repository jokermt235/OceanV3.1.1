package com.example.oceanv311.View.Modals;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;

import java.util.ArrayList;
import java.util.Map;

public class CategoryModal {
    private static String TAG = "CategoryModal";
    private AppCompatActivity activity;
    private View dialogContent;
    private  AlertDialog.Builder chooseDialog;
    private AlertDialog dialog;
    private ListView categoryList;
    private View view;

    public CategoryModal(final AppCompatActivity activity){
        this.activity = activity;
        chooseDialog = new AlertDialog.Builder(activity);
        view = getContentView();
        chooseDialog.setView(view);
        dialog = chooseDialog.create();
        categoryList  = view.findViewById(R.id.categoryChooseList);
        localLoad();
    }

    private View getContentView(){
        dialogContent = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.category_choose_layout,null);
        return dialogContent;
    }

    public void show(){
        dialog.show();
    }

    private void localLoad(){
        SimpleLoader.filter("category", new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                super.onResult(arrayList);
                Log.d(TAG, arrayList.toString());
                if(!arrayList.isEmpty()){
                    SimpleAdapter adapter = new SimpleAdapter(activity, arrayList, android.R.layout.simple_list_item_multiple_choice,
                            new String[]{"name"},
                            new int[]{android.R.id.text1});
                    categoryList.setAdapter(adapter);
                }
            }
        });
    }
}
