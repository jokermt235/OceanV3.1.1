package com.example.oceanv311.Callbacks.ChooseImage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.example.oceanv311.Callbacks.OnSavedResult;
import com.example.oceanv311.Modules.ImageUploader;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.Views.ChooseImageActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChooseImageSaveClick implements View.OnClickListener {
    private static  String TAG = "ChooseImageSaveClick";
    ChooseImageActivity activity;
    public ChooseImageSaveClick(ChooseImageActivity activity){
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {
        activity.getProgressBar().setVisibility(View.VISIBLE);
        Map<String, Object> post = new HashMap<>();
        post.put("name", activity.getChooseImageName().getText().toString());
        post.put("price", Integer.parseInt(activity.getChooseImagePrice().getText().toString()));
        post.put("priceSale", Integer.parseInt(activity.getChooseImagePriceSale().getText().toString()));
        post.put("cloth", activity.getChooseImageCloth().getText().toString());
        post.put("categories", activity.getCategory().getText().toString());
        post.put("sizes", activity.getSizes().getText().toString());
        post.put("posted", true);
        post.put("re_posted", false);
        post.put("new", true);
        SimpleLoader.save("post",post,new OnSavedResult(){
            @Override
            public void onSave(boolean result,String documentId, String uid) {
                super.onSave(result, documentId, uid);
                if(result && documentId != null){
                    ArrayList<Bitmap> images = new ArrayList<>();
                    Log.d(TAG, activity.getImages().toString());
                    for(Uri image: activity.getImages()){
                        try {
                            images.add(getBitmapFromUri(image));
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }
                    ImageUploader.uploadImages("post",images,uid);
                    ArrayList<Bitmap> marketImages = new ArrayList<>();
                    for(Uri image: activity.getMarketImages()){
                        try {
                            marketImages.add(getBitmapFromUri(image));
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }
                    ImageUploader.uploadImages("post_market",marketImages,uid);
                    activity.getProgressBar().setVisibility(View.GONE);
                }
            }
        });
    }

    private Bitmap getBitmapFromUri (Uri eUri) throws IOException
    {
        return MediaStore.Images.Media.getBitmap(activity.getContentResolver(), eUri);
    }
}
