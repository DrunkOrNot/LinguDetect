package com.drunkornot.lingudetect.lingu;

import com.drunkornot.lingudetect.tflite.Classifier;

import java.util.ArrayList;
import java.util.List;

public class ResultsProcessor {
    SpeakerWrapper speaker;
    // TODO
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

    public ResultsProcessor() {

    }

    public void AddListener(IDisplayResultsListener listener) {
        listeners.add(listener);
    }

    void PromoteResult(Result result) {
        //TODO actual implementation; this is quick test
        for(IDisplayResultsListener listener : listeners) {
            listener.onDisplayResult(result);
        }
    }
    public void ProcessResults(List<Classifier.Recognition> results, float minConfidence) {
        PromoteResult(new Result());
        // Decide if any of the results should be promoted

//        for (final Classifier.Recognition result : results) {
//            if (result.getConfidence() >= minConfidence) {
//                if(!speaker.isSpeaking())
//                    speaker.speak(result.getTitle(), TextToSpeech.QUEUE_FLUSH, null, "troll");
//        }
    }

    }
