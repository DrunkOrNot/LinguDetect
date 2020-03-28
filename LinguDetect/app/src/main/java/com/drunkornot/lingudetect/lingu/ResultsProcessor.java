package com.drunkornot.lingudetect.lingu;

import android.speech.tts.TextToSpeech;

import com.drunkornot.lingudetect.tflite.Classifier;

import java.util.List;

public class ResultsProcessor {
    TxtToSpeechWrapper speaker;

    public ResultsProcessor() {

    }

    public void ProcessResults(List<Classifier.Recognition> results, float minConfidence) {
//        for (final Classifier.Recognition result : results) {
//            if (result.getConfidence() >= minConfidence) {
//                if(!speaker.isSpeaking())
//                    speaker.speak(result.getTitle(), TextToSpeech.QUEUE_FLUSH, null, "troll");
//        }
    }

    }
