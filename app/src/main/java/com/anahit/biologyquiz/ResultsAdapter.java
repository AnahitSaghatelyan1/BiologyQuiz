package com.anahit.biologyquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultViewHolder> {
    private ArrayList<Question> questions;
    private ArrayList<Integer> userAnswers;

    public ResultsAdapter(ArrayList<Question> questions, ArrayList<Integer> userAnswers) {
        this.questions = questions;
        this.userAnswers = userAnswers;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Question question = questions.get(position);
        int userAnswer = userAnswers.get(position);
        boolean isCorrect = userAnswer == question.getCorrectAnswerIndex();

        holder.questionText.setText(question.getQuestionText());
        holder.userAnswerText.setText(question.getAnswers().get(userAnswer));
        holder.correctAnswerText.setText(question.getAnswers().get(question.getCorrectAnswerIndex()));

        if (isCorrect) {
            holder.resultText.setText("Правильно");
            holder.resultText.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_dark));
        } else {
            holder.resultText.setText("Неправильно");
            holder.resultText.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        TextView userAnswerText;
        TextView correctAnswerText;
        TextView resultText;

        ResultViewHolder(View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionText);
            userAnswerText = itemView.findViewById(R.id.userAnswerText);
            correctAnswerText = itemView.findViewById(R.id.correctAnswerText);
            resultText = itemView.findViewById(R.id.resultText);
        }
    }
}