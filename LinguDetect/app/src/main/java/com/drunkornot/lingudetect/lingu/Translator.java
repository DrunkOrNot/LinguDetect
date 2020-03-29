package com.drunkornot.lingudetect.lingu;

import androidx.annotation.NonNull;

import com.drunkornot.lingudetect.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;

public class Translator {

    public Translator() {

    }

    public Task<String> Translate(String text) {
        int sourceLangCode =
                FirebaseTranslateLanguage.languageForLanguageCode("en");
        int targetLangCode =
                FirebaseTranslateLanguage.languageForLanguageCode("pl");
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(sourceLangCode)
                .setTargetLanguage(targetLangCode)
                .build();
        final FirebaseTranslator translator =
                FirebaseNaturalLanguage.getInstance().getTranslator(options);
        return translator.downloadModelIfNeeded().continueWithTask(
                new Continuation<Void, Task<String>>() {
                    @Override
                    public Task<String> then(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            return translator.translate(text);
                        } else {
                            Exception e = task.getException();
                            if (e == null) {
                                e = new Exception("Unknown error");
                            }
                            return Tasks.forException(e);
                        }
    }});

    }
}
