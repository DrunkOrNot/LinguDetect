package com.drunkornot.lingudetect.lingu;

import java.util.Locale;

public class Result {
    public String keyName;
    public String nativeName;
    public String translatedName;
    public Locale nativeLang;
    public Locale translateLang;

    //TODO empty arg constructor for tests - delete later
    public Result() {

    }
    public Result(String keyName, String nativeName, String translatedName, Locale nativeLang, Locale translateLang) {
        this.keyName = keyName;
        this.nativeName = nativeName;
        this.translatedName = translatedName;
        this.nativeLang = nativeLang;
        this.translateLang = translateLang;
    }
}
