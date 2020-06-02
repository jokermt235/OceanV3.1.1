package com.example.oceanv311.Views;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oceanv311.R;

import org.apache.commons.lang3.StringUtils;

import java.awt.font.TextAttribute;

public class ChooseImageActivity extends AppActivity {

    public static String NAME = "ChooseImageActivity";
    private static int GALLERY_REQUEST_CODE= 1;
    private static int CATEGORY_REQUEST_CODE = 2;
    private static int SIZE_REQUEST_CODE = 3;
    private static int MIMAGE_REQUEST_CODE = 4;
    private EditText category;
    private EditText sizes;
    private ChooseImageActivity activity = this;
    private Button marketPhotoBtn;

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
        sizes = findViewById(R.id.chooseImageSizes);
        sizes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), SizeChooseActivity.class), SIZE_REQUEST_CODE);
            }
        });
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), GALLERY_REQUEST_CODE);
        marketPhotoBtn = findViewById(R.id.chooseImageMarketPhotoBtn);
        marketPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), MIMAGE_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode  == Activity.RESULT_OK){
                if(data.getClipData() != null){

                }
            }
        }

        if(requestCode == CATEGORY_REQUEST_CODE){
            if( data.getStringArrayExtra("checkedItems") != null) {
                Log.d(NAME,data.getStringArrayExtra("checkedItems").toString());
                String cats = StringUtils.join(data.getStringArrayExtra("checkedItems"), ',');
                category.setText(cats);
                Log.d(NAME, cats);
            }
        }
        if(requestCode == SIZE_REQUEST_CODE){
            if(data.getStringExtra("checkedItems") != null){
                sizes.setText(data.getStringExtra("checkedItems"));
            }
        }
    }
}
