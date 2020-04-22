package com.drunkornot.lingudetect.lingu;

import java.util.ArrayList;
import java.util.Arrays;

public class Combiner {

    static ArrayList<CombinedEntry> CombinedEntries = new ArrayList<>(Arrays.asList(
            new CombinedEntry("laptop", "mouse", "computer stand"),
            new CombinedEntry("cup", "mouse", "cracked screen")));

    private Combiner() {
    }

    public static Result Combine(Result summand1, Result summand2) {
        if (summand1.GetKeyName() == summand2.GetKeyName()){
            return null;
        }
        for (CombinedEntry entry : CombinedEntries) {
            if (entry.HasSummand(summand1.GetKeyName()) && entry.HasSummand(summand2.GetKeyName())) {
                return new Result(entry.GetResult(), AppSettings.Instance().GetCurrentUser().GetUsersNativeLanguage(), AppSettings.Instance().GetCurrentUser().GetUsersLearningLanguage());
            }
        }
        return null;
    }
}
