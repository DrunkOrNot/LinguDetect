package com.drunkornot.lingudetect.lingu;

public class CombinedEntry {
    String summand1;
    String summand2;
    String result;

    public CombinedEntry(String summand1, String summand2, String result) {
        this.summand1 = summand1;
        this.summand2 = summand2;
        this.result = result;
    }


    public Boolean HasSummand(String summand) {
        if (summand.equalsIgnoreCase(summand1) || summand.equalsIgnoreCase(summand2)) {
            return true;
        } else {
            return false;
        }
    }

    public String GetResult(){
        return result;
    }
}
