package com.example.oceanv311.Callbacks.PlaceForm;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.oceanv311.Callbacks.OnSavedResult;
import com.example.oceanv311.Modules.SimpleLoader;
import com.example.oceanv311.Views.PlaceFormActivity;
import com.example.oceanv311.Views.ProfilePlaceActivity;

import java.util.HashMap;
import java.util.Map;

public class OnSaveClick implements View.OnClickListener {
    private static String TAG = "PlaceForm.OnSaveClick";
    private PlaceFormActivity activity;
    private ProgressBar progressBar;
    @Override
    public void onClick(View v) {
        Log.d(TAG, "Save complex button clicked");
        final Map<String , Object> place  = new HashMap<>();
        place.put("row", activity.getPlaceFormRow().getText().toString());
        place.put("market", activity.getPlaceFormMarketName().getText().toString());
        place.put("pavilion", activity.getPlaceFormPavilion().getText().toString());
        place.put("floor", activity.getPlaceFormFloor().getText().toString());
        place.put("WAPhone", activity.getPlaceFormWAPhone().getText().toString());
        place.put("TGPhone", activity.getPlaceFormTGPhone().getText().toString());
        String name = place.get("market").toString() + " рк , "+
                place.get("row").toString() + "р / " +
                place.get("pavilion").toString() +  "п / " +
                place.get("floor").toString()  + "э";
        place.put("name", name);
        progressBar = activity.getProgressBar();
        if(activity.getPath() == null){
            SimpleLoader.save("place",place,new OnSavedResult(){
                @Override
                public void onSave(boolean result) {
                    super.onSave(result);
                    if(result){
                        activity.startActivity(new Intent(activity.getApplicationContext(), ProfilePlaceActivity.class));
                        progressBar.setVisibility(View.GONE);
                        activity.finish();
                    }
                }
            });
        }else{
            try {
                SimpleLoader.update("place",activity.getPath(),place,new OnUpdateDocument(){
                    @Override
                    public void updated(boolean status) {
                        super.updated(status);
                        if(status){
                            activity.startActivity(new Intent(activity.getApplicationContext(), ProfilePlaceActivity.class));
                            progressBar.setVisibility(View.GONE);
                            activity.finish();
                        }
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        progressBar.setVisibility(View.VISIBLE);

    }

    public OnSaveClick(PlaceFormActivity activity){
        this.activity = activity;
    }
}
