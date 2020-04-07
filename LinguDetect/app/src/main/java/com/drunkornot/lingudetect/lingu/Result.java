package com.drunkornot.lingudetect.lingu;

public class Result {
    private String keyName;
    public String nativeText;
    public String learningText;
    private String nativeLang;
    private String learningLang;

    public Result(String keyName, String nativeLang, String learningLang) {
        this.keyName = keyName;
        this.nativeLang = nativeLang;
        this.learningLang = learningLang;
        nativeText = new String();
        learningText = new String();
    }

    public String GetKeyName() {
        return keyName;
    }

    public String GetNativeLang() {
        return nativeLang;
    }

    public String GetLearningLang() {
        return learningLang;
    }

    public String GetNativeText() {
        return nativeText;
    }

    public String GetLearningText() {
        return learningText;
    }
}

