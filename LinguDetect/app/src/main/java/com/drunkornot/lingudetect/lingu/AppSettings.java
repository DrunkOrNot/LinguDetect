package com.drunkornot.lingudetect.lingu;

import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateRemoteModel;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AppSettings {

    private AppSettings() {
        InitializeLanguages();
    }

    static private AppSettings instance;

    static public AppSettings Instance() {
        if(instance == null)
            instance = new AppSettings();
        return instance;
    }

    //
    // Language region
    //

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
}
