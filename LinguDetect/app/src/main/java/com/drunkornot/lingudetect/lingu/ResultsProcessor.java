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

    void PromoteResult(Classifier.Recognition classifierResult) {

        //Create Result object
        Result result = new Result(classifierResult.getTitle(),
                AppSettings.Instance().GetCurrentUser().GetUsersNativeLanguage(),
                AppSettings.Instance().GetCurrentUser().GetUsersLearningLanguage());

        // Fill Learning Language Info
        Task<String> translateToLearningTask = translator.Translate(result.GetKeyName(), result.GetLearningLang());
        while(!translateToLearningTask.isComplete()) {
            // TODO I need to do it more civilized way
        }
        String learningText = translateToLearningTask.getResult();
        result.learningText = learningText;

        // Fill Native Language Info
        Task<String> translateToNativeTask = translator.Translate(result.GetKeyName(), result.GetNativeLang());
        while(!translateToNativeTask.isComplete()) {
            // TODO I need to do it more civilized way
        }
        String nativeText = translateToNativeTask.getResult();
        result.nativeText = nativeText;

        for(IDisplayResultsListener listener : listeners) {
            listener.onDisplayResult(result);
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
