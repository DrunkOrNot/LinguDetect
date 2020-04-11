package com.drunkornot.lingudetect.lingu;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class Speaker {

    private TextToSpeech instance;

    public Speaker(Context context) {
        Locale locale = new Locale.Builder().setLanguage(AppSettings.Instance().GetCurrentUser().GetUsersLearningLanguage()).build();

        instance = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    instance.setLanguage(locale);
                }
            }
        });
    }
    // TODO
    // public TrySpeak();

}
