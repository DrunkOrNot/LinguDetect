package com.drunkornot.lingudetect.lingu;

import com.google.firebase.auth.FirebaseUser;

public class UserData {

    public UserData() {
        history = new History();
    }

    private String userNativeLang;
    private String userLearningLang;
//    private FirebaseUser firebaseUser;

    private History history;

    // Provide BCP-47 code only
    public void SetUsersNativeLanguage(String lang) {
        if (AppSettings.Instance().IsValidBCP47Code(lang))
            userNativeLang = lang;
        else
            throw new IllegalArgumentException("Tried to set Users Native Language to: " + lang + " which is incorrect or unsupported");
    }

    // Provide BCP-47 code only
    public void SetUsersLearningLanguage(String lang) {
        if (AppSettings.Instance().IsValidBCP47Code(lang))
            userLearningLang = lang;
        else
            throw new IllegalArgumentException("Tried to set Users Learning Language to: " + lang + " which is incorrect or unsupported");
    }

    public String GetUsersNativeLanguage() {
        if (!userNativeLang.isEmpty())
            return userNativeLang;
        else
            throw new IllegalArgumentException("Users Native Language is not set");
    }

    public String GetUsersLearningLanguage() {
        if (!userLearningLang.isEmpty())
            return userLearningLang;
        else
            throw new IllegalArgumentException("Users Learning Language is not set");
    }

    public void AddToUserHistory(Result result) {
        history.AddIfNew(result);
    }

//    public FirebaseUser GetFirebaseUser() {
//        return firebaseUser;
//    }
//
//    public FirebaseUser SetFirebaseUser(FirebaseUser user) {
//        firebaseUser = user;
//    }
}
