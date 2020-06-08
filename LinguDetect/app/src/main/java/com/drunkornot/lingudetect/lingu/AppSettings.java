package com.drunkornot.lingudetect.lingu;

import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AppSettings {

    private AppSettings() {
        InitializeLanguages();
        resultLog = new History();
    }

    static private AppSettings instance;

    static public AppSettings Instance() {
        if(instance == null)
            instance = new AppSettings();
        return instance;
    }

    //region Languages

    private TreeMap<String, String> languageCodeToName;

    private void InitializeLanguages() {
        // Fill languageCodeToName Map
        Set<Integer> languages = FirebaseTranslateLanguage.getAllLanguages();
        languageCodeToName = new TreeMap<String, String>();
        for(Integer language : languages) {
            String code = FirebaseTranslateLanguage.languageCodeForLanguage(language);
            languageCodeToName.put(code, GetLanguageNameForCode(code));
        }
    }

    // Returns language name
    public ArrayList<String> GetAvailableLanguageNames() {
        ArrayList<String> languageNames = new ArrayList<String>();
        for(Map.Entry<String, String> entry : languageCodeToName.entrySet()) {
            languageNames.add(entry.getValue());
        }
        return languageNames;
    }

    // Returns BCP-47 code for name
    public String GetLanguageCodeForName(String name) {
        for(Map.Entry<String, String> entry : languageCodeToName.entrySet()) {
            if(entry.getValue().equals(name))
                return entry.getKey();
        }
        return new String();
    }

    // Returns locale name for BCP-47 code
    public String GetLanguageNameForCode(String code) {
        return Locale.forLanguageTag(code).getDisplayName();
    }

    public Boolean IsValidBCP47Code(String code) {
        for(Map.Entry<String, String> entry : languageCodeToName.entrySet()) {
            if(entry.getKey().equals(code))
                return true;
        }
        return false;
    }
    //endregion

    //region UserData
    public enum AuthType {
        None,
        Anonymous,
        Google
    }

    private UserData userData;
    private AuthType authType;

    public void ChangeCurrentUser(String userID, AuthType authType) {
        this.authType = authType;
        userData = new UserData(userID);
    }

    public UserData GetCurrentUser() {

        if (userData != null)
            return userData;
        else
            throw new NullPointerException("Current user is not set");
    }

    public AuthType GetCurrentUserAuthType() {
        if(IsUserSignedIn())
            return authType;
        else
            throw new NullPointerException("Cannot get auth type: Current user is not set");
    }

    public boolean IsUserSignedIn() {
        return userData == null ? false : true;
    }
    //endregion

    //region ResultLog
    private History resultLog;

    public History GetResultLog() {
        return resultLog;
    }
    public void LogResult(Result result) {
        resultLog.Add(result);
        GetCurrentUser().AddToUserHistory(result);
    }
    //endregion
}
