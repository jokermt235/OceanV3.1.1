package com.example.oceanv311.Modules;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Callbacks.OnSavedResult;
import com.example.oceanv311.Callbacks.PlaceForm.OnUpdateDocument;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class SimpleLoader {
    public static String TAG = "SimpleLoader";
    public static void update(final  String collection,String documentStrRef, Map<String, Object> data, final OnUpdateDocument result){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            data.put("phone",user.getPhoneNumber());
            data.put("uid", UUID.randomUUID().toString());
            db.collection(collection).document(documentStrRef)
                    .update(data)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            result.updated(true);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
    }
    public static void save(final  String collection, Map<String, Object> data, final OnSavedResult result){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            data.put("phone",user.getPhoneNumber());
            data.put("name",data.get("name"));
            data.put("added", new Date().getTime());
            data.put("uid", UUID.randomUUID().toString());
            db.collection(collection)
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            result.onSave(true);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
    }


    public static void filter(final  String collection, final OnFilterResult result){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            db.collection(collection).orderBy("added", Query.Direction.DESCENDING)
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG + "_" + collection, "Loaded success");
                        ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
                        QuerySnapshot querySnapshot = task.getResult();
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            arrayList.add(document.getData());
                            Log.d(TAG, document.getData().toString());
                        }
                        result.onResult(arrayList);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                }
            });
        }
    }
    public static void filter(final  String collection, String key, Object value ,final OnFilterResult result){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            db.collection(collection).whereEqualTo(key, value ).orderBy("added", Query.Direction.DESCENDING)
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG + "_" + collection, "Loaded success");
                        ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
                        QuerySnapshot querySnapshot = task.getResult();
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            Map<String, Object> map = document.getData();
                            map.put("_ref", document.getId());
                            arrayList.add(map);
                            Log.d(TAG, document.getData().toString());
                        }
                        result.onResult(arrayList);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                }
            });
        }
    }
}
