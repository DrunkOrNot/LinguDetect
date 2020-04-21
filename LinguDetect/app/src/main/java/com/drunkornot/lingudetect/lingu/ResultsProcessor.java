package com.drunkornot.lingudetect.lingu;

import android.graphics.RectF;

import com.drunkornot.lingudetect.tflite.Classifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsProcessor {
    Speaker speaker;
    private List<IPromoteResultsListener> listeners = new ArrayList<IPromoteResultsListener>();
    private Translator translator;
    private Boolean combineResults;
    private float minConfidence = 0.7f;

    public ResultsProcessor() {
        translator = new Translator();
        combineResults = false;
    }

    public void AddListener(IPromoteResultsListener listener) {
        listeners.add(listener);
    }

    public void EnableCombined(Boolean enable) {
        combineResults = enable;
        if(enable) {
            new Thread() {
                public void run() {
                    long timeout = System.currentTimeMillis() + combineTimeout;
                    while(System.currentTimeMillis() <= timeout && combineResults ) {
                    }
                    combineResults = false;
                }
            }.start();
        }

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

        result.learningText = translator.Translate(result.GetKeyName(), result.GetLearningLang());
        result.nativeText = translator.Translate(result.GetKeyName(), result.GetNativeLang());

        if(combineResults) {
            PromoteCombinedResult(result);
        }
        else {
            PromoteResult(result);

        }
        AppSettings.Instance().GetHistory().Add(result);
    }

    private void PromoteResult(Result result) {
        for (IPromoteResultsListener listener : listeners) {
            listener.onPromoteResult(result);
        }
        AppSettings.Instance().GetHistory().Add(result);
    }

    private void PromoteCombinedResult(Result result) {
        Result finalResult = Combiner.Combine(AppSettings.Instance().GetHistory().GetLastResult(), result);
        finalResult.nativeText = translator.Translate(finalResult.GetKeyName(), finalResult.GetNativeLang());
        finalResult.learningText = translator.Translate(finalResult.GetKeyName(), finalResult.GetLearningLang());

        for (IPromoteResultsListener listener : listeners) {
                listener.onPromoteCombinedResult(AppSettings.Instance().GetHistory().GetLastResult(), result, finalResult );
        }
        combineResults = false;
        AppSettings.Instance().GetHistory().Add(result);
        //TODO Combined History
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
        // If it is combined result, time restrictions does not apply
        if(combineResults)
            return true;

        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastFired) <= resultDuration) {
            return false;
        }
        return true;
    }

    public float getMinConfidence() {
        return minConfidence;
    }

    //region Timer
    private static int combineTimeout = 5000;
    private long lastFired = 0;
    private long resultDuration = 5000;

    //endregion
}
