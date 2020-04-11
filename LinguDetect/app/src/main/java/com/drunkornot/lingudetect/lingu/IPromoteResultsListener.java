package com.drunkornot.lingudetect.lingu;

public interface IPromoteResultsListener {
    public void onPromoteResult(Result result);

    // summand2 and combinedResult can be null
    public void onPromoteCombinedResult(Result summand1, Result summand2, Result combinedResult);

}

