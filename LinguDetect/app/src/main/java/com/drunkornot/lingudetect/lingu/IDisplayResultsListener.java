package com.drunkornot.lingudetect.lingu;

public interface IDisplayResultsListener {
    public void onDisplayResult(Result result);

    // summand2 and combinedResult can be null
    public void onDisplayCombinedResult(Result summand1, Result summand2, Result combinedResult);

}
