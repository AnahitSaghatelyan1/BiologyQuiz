package com.anahit.biologyquiz;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StatsActivity extends AppCompatActivity {
    private QuizStats stats;
    private TextView totalQuizzesText;
    private TextView averageScoreText;
    private TextView bestScoreText;
    private RecyclerView historyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Add exit button click listener
        ImageButton exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> finish());

        stats = new QuizStats(this);
        initializeViews();
        setupRecyclerView();
        updateStats();
    }

    private void initializeViews() {
        totalQuizzesText = findViewById(R.id.totalQuizzesText);
        averageScoreText = findViewById(R.id.averageScoreText);
        bestScoreText = findViewById(R.id.bestScoreText);
        historyRecyclerView = findViewById(R.id.historyRecyclerView);
    }

    private void setupRecyclerView() {
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HistoryAdapter adapter = new HistoryAdapter(stats.getResults());
        historyRecyclerView.setAdapter(adapter);
    }

    private void updateStats() {
        int totalQuizzes = stats.getResults().size();
        float averageScore = stats.getAverageScore();
        int bestScore = stats.getBestScore();

        totalQuizzesText.setText(String.format("Всего квизов: %d", totalQuizzes));
        averageScoreText.setText(String.format("Средний результат: %.1f%%", averageScore));
        bestScoreText.setText(String.format("Лучший результат: %d%%", bestScore));
    }
}