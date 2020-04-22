package com.drunkornot.lingudetect.lingu;

public class Result {
    private String nativeText;
    private String learningText;
    private String keyName;
    private String nativeLang;
    private String learningLang;

    public Result(String keyName, String nativeLang, String learningLang) {
        this.keyName = keyName;
        this.nativeLang = nativeLang;
        this.learningLang = learningLang;
        nativeText = Translator.Translate(keyName, nativeLang);
        learningText = Translator.Translate(keyName, learningLang);
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

