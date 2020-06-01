package com.example.oceanv311.Views;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.oceanv311.R;

import org.apache.commons.lang3.StringUtils;

public class ChooseImageActivity extends AppActivity {

    public static String NAME = "ChooseImageActivity";
    private static int GALLERY_REQUEST_CODE= 1;
    private static int CATEGORY_REQUEST_CODE = 2;
    private EditText category;
    private ChooseImageActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);
        //Init category  multiple input
        category = findViewById(R.id.chooseImageCategory);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivityForResult(new Intent(getApplicationContext(), CategoryChooseActivity.class), CATEGORY_REQUEST_CODE);
            }
        });
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE){
        }

        if(requestCode == CATEGORY_REQUEST_CODE){
            if( data.getStringArrayExtra("checkedItems") != null) {
                Log.d(NAME,data.getStringArrayExtra("checkedItems").toString());
                String cats = StringUtils.join(data.getStringArrayExtra("checkedItems"), ',');
                category.setText(cats);
                Log.d(NAME, cats);
            }
        }
    }
}
