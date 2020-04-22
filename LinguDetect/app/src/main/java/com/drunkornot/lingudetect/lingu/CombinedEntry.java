package com.drunkornot.lingudetect.lingu;

public class CombinedEntry {
    private String summand1;
    private String summand2;
    private String product;

    public CombinedEntry(String summand1, String summand2, String product) {
        this.summand1 = summand1;
        this.summand2 = summand2;
        this.product = product;
    }


    public Boolean HasSummand(String summand) {
        if (summand.equalsIgnoreCase(summand1) || summand.equalsIgnoreCase(summand2)) {
            return true;
        } else {
            return false;
        }
    }

    public String GetProduct(){
        return product;
    }
}
