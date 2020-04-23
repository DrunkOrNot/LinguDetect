package com.drunkornot.lingudetect.lingu;

import java.util.ArrayList;

public class History {

    ArrayList<Result> history = new ArrayList<>();

    public History() {

    }

    public void Add(Result result) {
        history.add(result);
    }

    public void AddIfNew(Result result) {
        if(!history.contains(result))
            history.add(result);
    }

    public Result GetLastResult() {
        if (HasLastResult()) {
            return history.get(history.size() - 1);
        } else {
            throw new NullPointerException("Tried to get last result when the history is empty");
        }
    }

    public boolean HasLastResult() {
        if (history.size() == 0) {
            return false;
        }
        return true;
    }

}
