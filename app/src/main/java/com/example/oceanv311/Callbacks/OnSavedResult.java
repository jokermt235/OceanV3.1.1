package com.example.oceanv311.Callbacks;

import com.google.firebase.firestore.DocumentId;

public class OnSavedResult {
    private boolean result;
    private String documentId;
    private String uid;
    public void onSave(boolean result){
        this.result = result;
    }

    public void onSave(boolean  result, String documentId){
        this.result = result;
        this.documentId = documentId;
    }

    public void onSave(boolean  result, String documentId, String uid){
        this.result = result;
        this.documentId = documentId;
        this.uid = uid;
    }
}
