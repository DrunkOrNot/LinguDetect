package com.drunkornot.lingudetect.lingu;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class History extends RecyclerView.Adapter {

    public ArrayList<Result> history = new ArrayList<>();

    public History() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void Add(Result result) {
        history.add(result);
    }

    public void AddIfNew(Result result) {
        if(!history.stream().anyMatch(element -> result.GetKeyName().equals(element.GetKeyName())))
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

    public ArrayList<Result> GetHistoryAsList() {
        return history;
    }

}
