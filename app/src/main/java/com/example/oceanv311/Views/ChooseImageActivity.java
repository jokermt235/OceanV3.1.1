package com.example.oceanv311.Views;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.oceanv311.Callbacks.ChooseImage.ChooseImageSaveClick;
import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthCredential;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Map;

public class ChooseImageActivity extends AppActivity {

    public static String NAME = "ChooseImageActivity";
    private static int GALLERY_REQUEST_CODE= 1;
    private static int CATEGORY_REQUEST_CODE = 2;
    private static int SIZE_REQUEST_CODE = 3;
    private static int MIMAGE_REQUEST_CODE = 4;
    private EditText chooseImageName;
    private EditText chooseImagePrice;
    private EditText chooseImagePriceSale;
    private EditText chooseImageCloth;
    private EditText category;
    private EditText sizes;
    private ChooseImageActivity activity = this;
    private Button marketPhotoBtn;
    private Spinner chooseImageSalePlace;
    private LinearLayout postFormSaveBtn;
    private ArrayList<Uri> images = new ArrayList<>();
    private ArrayList<Uri> marketImages = new ArrayList<>();
    private ProgressBar progressBar;

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
        chooseImageSalePlace = findViewById(R.id.chooseImageSalePlace);
        chooseImageName = findViewById(R.id.chooseImageName);
        chooseImagePrice =  findViewById(R.id.chooseImagePrice);
        chooseImagePriceSale = findViewById(R.id.chooseImagePriceSale);
        chooseImageCloth = findViewById(R.id.chooseImageCloth);
        postFormSaveBtn = findViewById(R.id.postFormSaveBtn);
        postFormSaveBtn.setOnClickListener(new ChooseImageSaveClick(this));
        progressBar  = findViewById(R.id.progressBar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode  == Activity.RESULT_OK){
                if(data.getClipData() != null){
                    int n = data.getClipData().getItemCount();
                    Log.d(NAME, data.getClipData().toString());
                    images = new ArrayList<>();
                    for(int i=0;i < n;i++){
                        images.add(data.getClipData().getItemAt(i).getUri());
                    }

                }else  if(data.getData() != null){
                    Log.d(NAME, data.getData().toString());
                    images = new ArrayList<>();
                    images.add(data.getData());
                }
            }
        }

        if(requestCode == MIMAGE_REQUEST_CODE){
            if(resultCode  == Activity.RESULT_OK){
                if(data.getClipData() != null){
                    int n = data.getClipData().getItemCount();
                    Log.d(NAME, data.getClipData().toString());
                    marketImages = new ArrayList<>();
                    for(int i=0;i < n;i++){
                        marketImages.add(data.getClipData().getItemAt(i).getUri());
                    }
                }else  if(data.getData() != null){
                    Log.d(NAME, "The single data chosen");
                    marketImages = new ArrayList<>();
                    marketImages.add(data.getData());
                }
            }
        }

        if(requestCode == CATEGORY_REQUEST_CODE){
            if( data.getStringArrayExtra("checkedItems") != null) {
                Log.d(NAME,data.getStringArrayExtra("checkedItems").toString());
                String cats = StringUtils.join(data.getStringArrayExtra("checkedItems"), ',');
                category.setText(cats);
            }
        }
        if(requestCode == SIZE_REQUEST_CODE){
            if(data.getStringExtra("checkedItems") != null){
                sizes.setText(data.getStringExtra("checkedItems"));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDefaults();
    }

    public ArrayList<Uri> getImages(){
        return images;
    }

    public  ArrayList<Uri> getMarketImages(){
        return marketImages;
    }

    public EditText getChooseImageName(){
        return chooseImageName;
    }

    public EditText getChooseImagePrice(){
        return chooseImagePrice;
    }

    public EditText getChooseImagePriceSale(){
        return  chooseImagePriceSale;
    }

    public EditText getChooseImageCloth(){
        return chooseImageCloth;
    }

    public EditText getCategory(){
        return  category;
    }

    public EditText getSizes(){
        return  sizes;
    }

    public ProgressBar getProgressBar(){
        return progressBar;
    }

    public Spinner getChooseImageSalePlace() {
        return chooseImageSalePlace;
    }

    private void initDefaults(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        SimpleLoader.filter("place","phone", user.getPhoneNumber(), new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                super.onResult(arrayList);
                if(!arrayList.isEmpty()){
                    SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), arrayList, android.R.layout.simple_list_item_1,
                            new String[]{"name"},
                            new int[]{android.R.id.text1});
                    chooseImageSalePlace.setAdapter(adapter);
                }
            }
        });
    }
}
