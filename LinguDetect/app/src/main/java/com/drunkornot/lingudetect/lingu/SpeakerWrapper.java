package com.drunkornot.lingudetect.lingu;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class SpeakerWrapper {

    private TextToSpeech instance;
    private boolean initialized = false;

    public SpeakerWrapper() {
    }

    public void Initialize(Context context) {
        Locale locale = new Locale.Builder().setLanguage(AppSettings.Instance().GetCurrentUser().GetUsersLearningLanguage()).build();

        instance = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    instance.setLanguage(locale);
                }
            }
        });
        initialized = true;
    }

    public boolean IsInitialized() {
        return initialized;
    }
    // TODO
    // public TrySpeak();
    // public SetLocale();

}
