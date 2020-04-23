package com.drunkornot.lingudetect.lingu;

import java.util.ArrayList;
import java.util.Arrays;

public class Combiner {

    static ArrayList<CombinedEntry> CombinedEntries = new ArrayList<>(Arrays.asList(
            new CombinedEntry("laptop", "mouse", "computer stand"),
            new CombinedEntry("cup", "mouse", "cracked screen"),
            new CombinedEntry("laptop", "mouse", "computer stand"),
            new CombinedEntry("TV", "remote", "change channel"),
            new CombinedEntry("apple", "banana", "fruit salad"),
            new CombinedEntry("orange", "bottle", "juice"),
            new CombinedEntry("laptop", "mouse", "computer set"),
            new CombinedEntry("vase", "flower", "decoration"),
            new CombinedEntry("book", "glasses", "read"),
            new CombinedEntry("knife", "banana", "cut"),
            new CombinedEntry("teddy bear", "person", "cuddle"),
            new CombinedEntry("knife", "person", "murder"),
            new CombinedEntry("cat", "person", "cat lover"),
            new CombinedEntry("skateboard", "", ""),
            new CombinedEntry("skateboard", "person", "skater"),
            new CombinedEntry("sink", "toilet", "bathroom"),
            new CombinedEntry("clock", "pottet plant", "interior furnishings"),
            new CombinedEntry("scisors", "book", "cut"),
            new CombinedEntry("bench", "person", "sit"),
            new CombinedEntry("suitcase", "person", "travel"),
            new CombinedEntry("bed", "person", "sleep"),
            new CombinedEntry("cup", "bottle", "drink"),
            new CombinedEntry("chair", "dining table", "dining room"),
            new CombinedEntry("toothbrush", "person", "brush teeth"),
            new CombinedEntry("keybord", "TV", "computer station")));

    private Combiner() {
    }

    public static Result Combine(Result summand1, Result summand2) {
        if (summand1.GetKeyName() == summand2.GetKeyName()){
            return null;
        }
        for (CombinedEntry entry : CombinedEntries) {
            if (entry.HasSummand(summand1.GetKeyName()) && entry.HasSummand(summand2.GetKeyName())) {
                return new Result(entry.GetProduct(), AppSettings.Instance().GetCurrentUser().GetUsersNativeLanguage(), AppSettings.Instance().GetCurrentUser().GetUsersLearningLanguage());
            }
        }
        return null;
    }
}
