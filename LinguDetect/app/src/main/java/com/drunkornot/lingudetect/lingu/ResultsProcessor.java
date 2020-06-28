package com.drunkornot.lingudetect.lingu;

import android.graphics.RectF;

import com.drunkornot.lingudetect.tflite.Classifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsProcessor {
    private List<IPromoteResultsListener> listeners = new ArrayList<IPromoteResultsListener>();
    private Boolean combineResults;
    private float minConfidence = 0.7f;

    public ResultsProcessor() {
        combineResults = false;
    }

    public void AddListener(IPromoteResultsListener listener) {
        listeners.add(listener);
    }

    public void EnableCombined(Boolean enable) {
        combineResults = enable;
    }

    void DesignateResult(Classifier.Recognition classifierResult) {

        if (!CanPromoteResult()) {
            return;
        }
        lastFired = System.currentTimeMillis();

        //Create Result object
        Result result = new Result(classifierResult.getTitle(),
                AppSettings.Instance().GetCurrentUser().GetUsersNativeLanguage(),
                AppSettings.Instance().GetCurrentUser().GetUsersLearningLanguage());

        if(combineResults) {
            PromoteCombinedResult(result);
        }
        else {
            PromoteResult(result);
        }

        AppSettings.Instance().LogResult(result);
    }

    private void PromoteResult(Result result) {
        for (IPromoteResultsListener listener : listeners) {
            listener.onPromoteResult(result);
        }
    }

    private void PromoteCombinedResult(Result result) {
        Result finalResult = Combiner.Combine(AppSettings.Instance().GetResultLog().GetLastResult(), result);

        for (IPromoteResultsListener listener : listeners) {
                listener.onPromoteCombinedResult(AppSettings.Instance().GetResultLog().GetLastResult(), result, finalResult );
        }
        combineResults = false;

        //TODO Combined History
    }

    public void ProcessResults(List<Classifier.Recognition> results) {
        List<Classifier.Recognition> recognized = results.stream().filter(res -> res.getConfidence() > minConfidence).collect(Collectors.toList());

        if (recognized.size() == 0) {
            return;
        } else if (recognized.size() == 1) {
            DesignateResult(recognized.get(0));
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
            DesignateResult(bestResult);
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

    public float GetMinConfidence() {
        return minConfidence;
    }

    //region Timer
    private long lastFired = 0;
    private long resultDuration = 5000;

    //endregion
}
