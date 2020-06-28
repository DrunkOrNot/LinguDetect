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

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static DatabaseReference mDatabase;
    private static List<IUserDataChangeListener> listeners = new ArrayList<IUserDataChangeListener>();

    public static boolean DoesUserExist(UserData userData) {
        return true;
    }

    public static void PostData(UserData userData) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child(AppSettings.Instance().GetCurrentUser().GetUsersID()).setValue(userData);
    }

    public static void GetData(String userID) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Object query = mDatabase.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserData userData = dataSnapshot.getValue(UserData.class);
                for (IUserDataChangeListener listener : listeners) {
                    listener.onUserDataReceivedFromDatabase(userData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                for (IUserDataChangeListener listener : listeners) {
                    listener.onUserDataReceivedFromDatabase(null);
            }
        }
    });
    }

    public static void AddListener(IUserDataChangeListener listener) { listeners.add(listener); }
}
