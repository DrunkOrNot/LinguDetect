package com.drunkornot.lingudetect.lingu;

public interface IPromoteResultsListener {
    public void onPromoteResult(Result result);

    public void onPromoteCombinedResult(Result summand1, Result summand2, Result result);

}

