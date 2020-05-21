package com.example.oceanv311.Modules;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oceanv311.Callbacks.OnFilterResult;
import com.example.oceanv311.Callbacks.PhoneVerificationCallback;
import com.example.oceanv311.View.FeedActivity;
import com.example.oceanv311.View.UserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Auth {
    public static void signIn(AppCompatActivity activity, String phoneNumber){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                activity,               // Activity (for callback binding)
                new PhoneVerificationCallback(activity, phoneNumber));        // OnVerificationStateChangedCallbacks
    }

    public static void signUp(PhoneAuthCredential credential ){

    }

    public  static  boolean isSigned(){
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            return true;
        }
        return false;

    }

    public static  void signInWithPhoneCredentials(final AppCompatActivity activity, PhoneAuthCredential phoneAuthCredential){
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = task.getResult().getUser();
                    Toast.makeText(activity.getApplicationContext(), user.getUid(), Toast.LENGTH_LONG).show();
                    SimpleLoader.filter("user", new OnFilterResult(){
                        @Override
                        public void onResult(ArrayList<Map<String, Object>> arrayList) {
                            super.onResult(arrayList);
                            if(!arrayList.isEmpty()){
                                activity.startActivity(new Intent(activity.getApplicationContext(), FeedActivity.class));
                                activity.finish();
                            }else{
                                activity.startActivity(new Intent(activity.getApplicationContext(), UserActivity.class));
                                activity.finish();
                            }
                        }
                    });
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI

                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
        });
    }
}
