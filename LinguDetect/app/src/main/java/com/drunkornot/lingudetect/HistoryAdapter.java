package com.drunkornot.lingudetect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drunkornot.lingudetect.lingu.History;
import com.drunkornot.lingudetect.lingu.Result;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
// modifications:  ArrayList<Result> history;
    ArrayList<Result> history;

//modifications: public HistoryAdapter(History history) {this.history = history.GetHistoryAsList();}
    public HistoryAdapter(History history) {
    this.history = history.GetHistoryAsList();
}

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Result currentResult = history.get(position);

        holder.textLearning.setText(currentResult.GetLearningText());
        holder.textNative.setText(currentResult.GetNativeText());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        public TextView textNative;
        public TextView textLearning;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textNative = itemView.findViewById(R.id.txtNative);
            textLearning = itemView.findViewById(R.id.txtLearning);
        }
    }



    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);

        return new HistoryViewHolder(v);
    }


}
