package com.drunkornot.lingudetect.lingu;

import com.drunkornot.lingudetect.tflite.Classifier;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class ResultsProcessor {
    SpeakerWrapper speaker;
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

    private List<IDisplayResultsListener> listeners = new ArrayList<IDisplayResultsListener>();
    private Translator translator;

    private Boolean combineResults;

    public ResultsProcessor() {
        translator = new Translator();
        combineResults = false;
    }

    public void AddListener(IDisplayResultsListener listener) {
        listeners.add(listener);
    }

    public void EnableCombined(Boolean enable) {
        combineResults = enable;
    }

    void PromoteResult(Classifier.Recognition result) {
        Task<String> task = translator.Translate(result.getTitle());
        while(!task.isComplete()) {
            // TODO I need to do it more civilized way
        }
        String resStr = task.getResult();
        Result res = new Result();
        res.keyName = resStr;
        //TODO not actual implementation; this is quick test
        for(IDisplayResultsListener listener : listeners) {
            listener.onDisplayResult(res);
        }
        //speaker.TrySpeak();

    }

    public void ProcessResults(List<Classifier.Recognition> results, float minConfidence) {
        // Decide if any of the results should be promoted
        // And promote the choosen one
        PromoteResult(results.get(1));


//        for (final Classifier.Recognition result : results) {
//            if (result.getConfidence() >= minConfidence) {
//                if(!speaker.isSpeaking())
//                    speaker.speak(result.getTitle(), TextToSpeech.QUEUE_FLUSH, null, "troll");
//        }
    }

    }
