package com.drunkornot.lingudetect.lingu;

import android.graphics.RectF;

import com.drunkornot.lingudetect.tflite.Classifier;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsProcessor {
    Speaker speaker;
    // TODO This file is a test mess now; not actual implementation
    // Translator translator;
    // UserData userData;
    // List<Results> results
    //
    // void PromoteResult()
    // Translate it
    // Display it
    // Play on speaker
    // Update user dictionary
    //
    //
    // void CombineWithPrevious();

    private List<IPromoteResultsListener> listeners = new ArrayList<IPromoteResultsListener>();
    private Translator translator;
    private Boolean combineResults;
    private float minConfidence = 0.7f;
    private long lastFired = 0;
    private long resultDuration = 5000;

    public ResultsProcessor() {
        translator = new Translator();
        combineResults = false;
    }

    public void AddListener(IPromoteResultsListener listener) {
        listeners.add(listener);
    }

    public void EnableCombined(Boolean enable) {
        combineResults = enable;
    }

    void PromoteResult(Classifier.Recognition classifierResult) {

        if (!CanPromoteResult()) {
            return;
        }
        lastFired = System.currentTimeMillis();

        //Create Result object
        Result result = new Result(classifierResult.getTitle(),
                AppSettings.Instance().GetCurrentUser().GetUsersNativeLanguage(),
                AppSettings.Instance().GetCurrentUser().GetUsersLearningLanguage());

        // Fill Learning Language Info
        Task<String> translateToLearningTask = translator.Translate(result.GetKeyName(), result.GetLearningLang());
        while (!translateToLearningTask.isComplete()) {
            // TODO I need to do it more civilized way
        }
        String learningText = translateToLearningTask.getResult();
        result.learningText = learningText;

        // Fill Native Language Info
        Task<String> translateToNativeTask = translator.Translate(result.GetKeyName(), result.GetNativeLang());
        while (!translateToNativeTask.isComplete()) {
            // TODO I need to do it more civilized way
        }

        String nativeText = translateToNativeTask.getResult();
        result.nativeText = nativeText;


        for (IPromoteResultsListener listener : listeners) {
            listener.onPromoteResult(result);
        }
        //speaker.TrySpeak();

    }

    public void ProcessResults(List<Classifier.Recognition> results) {
        List<Classifier.Recognition> recognized = results.stream().filter(res -> res.getConfidence() > minConfidence).collect(Collectors.toList());

        if (recognized.size() == 0) {
            return;
        } else if (recognized.size() == 1) {
            PromoteResult(recognized.get(0));
        } else {
            float biggestArea = 0;
            Classifier.Recognition bestResult = new Classifier.Recognition("", "", 0.0f, new RectF());

            for (Classifier.Recognition result : recognized) {
                float area = result.getLocation().width() * result.getLocation().height();
                if (area > biggestArea) {
                    biggestArea = area;
                    bestResult = result;
                }
            }
            PromoteResult(bestResult);
        }
    }

    private boolean CanPromoteResult() {
        long currentTime = System.currentTimeMillis();

        if ((currentTime - lastFired) <= resultDuration) {
            return false;
        }
        return true;
    }

    public float getMinConfidence() {
        return minConfidence;
    }
}

//        for (final Classifier.Recognition result : results) {
//            if (result.getConfidence() >= minConfidence) {
//                if(!speaker.isSpeaking())
//                    speaker.speak(result.getTitle(), TextToSpeech.QUEUE_FLUSH, null, "troll");
//        }