package com.drunkornot.lingudetect.lingu;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.drunkornot.lingudetect.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {

    private static DatabaseReference mDatabase;

    public static UserData GetData(UserData userData) {
        return userData;
    }

    public static boolean DoesUserExist(UserData userData) {
        return true;
    }

    public static int PostData(UserData userData) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(AppSettings.Instance().GetCurrentUser().GetUsersID()).setValue("hello2").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
        return 0;
    }
}
