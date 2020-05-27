package com.example.oceanv311.View.Modals;

import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oceanv311.R;

public class CategoryModal {
    private static String TAG = "CategoryModal";
    private AppCompatActivity activity;
    private View dialogContent;
    private  AlertDialog.Builder chooseDialog;
    private AlertDialog dialog;

    public CategoryModal(final AppCompatActivity activity){
        this.activity = activity;
        chooseDialog = new AlertDialog.Builder(activity);
        chooseDialog.setView(getContentView());
        dialog = chooseDialog.create();
    }

    private View getContentView(){
        dialogContent =LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.category_choose_layout,null);
        return dialogContent;
    }

    public void show(){
        dialog.show();
    }
}
