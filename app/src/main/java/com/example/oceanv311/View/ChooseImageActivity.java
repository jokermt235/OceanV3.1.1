package com.example.oceanv311.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.oceanv311.R;
import com.example.oceanv311.View.Modals.CategoryModal;

public class ChooseImageActivity extends AppActivity {

    public static String NAME = "ChooseImageActivity";
    private static int GALLERY_REQUEST_CODE= 1;
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
                 startActivity(new Intent(getApplicationContext(), CategoryChooseActivity.class));
            }
        });
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE){

        }
    }
}
