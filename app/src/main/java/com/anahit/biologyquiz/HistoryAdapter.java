package com.anahit.biologyquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<QuizResult> results;

    public HistoryAdapter(List<QuizResult> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        QuizResult result = results.get(position);
        holder.quizIdText.setText("Quiz ID: " + result.getQuizId());
        holder.scoreText.setText("Score: " + result.getScore() + "%");
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView quizIdText;
        TextView scoreText;

        HistoryViewHolder(View itemView) {
            super(itemView);
            quizIdText = itemView.findViewById(R.id.quizIdText);
            scoreText = itemView.findViewById(R.id.scoreText);
        }
    }
}