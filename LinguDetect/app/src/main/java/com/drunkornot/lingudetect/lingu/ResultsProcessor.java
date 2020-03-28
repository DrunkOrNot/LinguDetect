package com.drunkornot.lingudetect.lingu;

import com.drunkornot.lingudetect.tflite.Classifier;

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

    public ResultsProcessor() {

    }

    public void ProcessResults(List<Classifier.Recognition> results, float minConfidence) {
        // Decide if any of the results should be promoted

//        for (final Classifier.Recognition result : results) {
//            if (result.getConfidence() >= minConfidence) {
//                if(!speaker.isSpeaking())
//                    speaker.speak(result.getTitle(), TextToSpeech.QUEUE_FLUSH, null, "troll");
//        }
    }

    }
