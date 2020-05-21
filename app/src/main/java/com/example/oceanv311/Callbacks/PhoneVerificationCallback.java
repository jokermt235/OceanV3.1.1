package com.example.oceanv311.Callbacks;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oceanv311.Modules.Auth;
import com.example.oceanv311.View.CodeConfirmActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class PhoneVerificationCallback extends PhoneAuthProvider.OnVerificationStateChangedCallbacks {
    private  AppCompatActivity activity;
    private  String verificationId;
    private  String phone;
    public PhoneVerificationCallback(AppCompatActivity activity, String phone){
        this.activity = activity;
        this.phone = phone;
    }
    @Override
    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
        Auth.signInWithPhoneCredentials(this.activity , phoneAuthCredential);
        //Toast.makeText(activity.getApplicationContext(),"Verification passed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onVerificationFailed(@NonNull FirebaseException e) {
        Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCodeSent(@NonNull String verificationId,
                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
        Intent intent = new Intent(activity, CodeConfirmActivity.class);
        intent.putExtra("verificationId", verificationId);
        intent.putExtra("phone", phone);
        activity.startActivity(intent);
        this.verificationId = verificationId;
        activity.finish();

    }
}
