package com.drunkornot.lingudetect;

public class Result {
    public String LearningText;
    public String NativeText;

    public Result(String learningText, String nativeText){
        LearningText = learningText;
        NativeText = nativeText;
    }

    public String getLearningText() {
        return LearningText;
    }
    public String getNativeText() {
        return NativeText;
    }

}

