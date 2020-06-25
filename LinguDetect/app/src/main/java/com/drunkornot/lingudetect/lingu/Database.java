package com.drunkornot.lingudetect.lingu;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.drunkornot.lingudetect.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Database {

    private static DatabaseReference mDatabase;

    public static boolean DoesUserExist(UserData userData) {
        return true;
    }

    public static void PostData(UserData userData) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child(AppSettings.Instance().GetCurrentUser().GetUsersID()).setValue(userData);
    }

    public static void GetData() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Object query = mDatabase.orderByChild(AppSettings.Instance().GetCurrentUser().GetUsersID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserData userData = dataSnapshot.getValue(UserData.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // ...
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                throw
//            }
//        });
    }
}
