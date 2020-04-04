package com.drunkornot.lingudetect.lingu;

import java.util.Locale;

public class Result {
    private String keyName;
    public String nativeText;
    public String translatedText;
    private String nativeLang;
    private String translatedLang;

    public Result(String keyName, String nativeLang, String translatedLang) {
        this.keyName = keyName;
        this.nativeLang = nativeLang;
        this.translatedLang = translatedLang;
        nativeText = new String();
        translatedText = new String();
    }

    public String GetKeyName() {
        return keyName;
    }

    public String GetNativeLang() {
        return nativeLang;
    }

    public String GetTranslatedLang() {
        return translatedLang;
    }

    public String GetNativeText() {
        return nativeText;
    }

    public String GetTranslatedText() {
        return translatedText;
    }
}
