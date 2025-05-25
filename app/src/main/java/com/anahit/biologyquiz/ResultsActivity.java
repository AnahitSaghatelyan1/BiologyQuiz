package com.anahit.biologyquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import com.anahit.biologyquiz.R;

public class ResultsActivity extends AppCompatActivity {
    private TextView scoreText;
    private RecyclerView resultsRecyclerView;
    private MaterialButton retryButton;
    private MaterialButton menuButton;
    private ResultsAdapter adapter;
    private ArrayList<Question> questions;
    private ArrayList<Integer> userAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Получаем данные из интента
        questions = (ArrayList<Question>) getIntent().getSerializableExtra("questions");
        userAnswers = (ArrayList<Integer>) getIntent().getSerializableExtra("userAnswers");

        initializeViews();
        setupRecyclerView();
        updateScore();
        setupButtons();
    }

    @SuppressLint("WrongViewCast")
    private void initializeViews() {
        scoreText = findViewById(R.id.scoreText);
        resultsRecyclerView = findViewById(R.id.resultsRecyclerView);
        retryButton = findViewById(R.id.retryButton);
        menuButton = findViewById(R.id.menuButton);
    }

    private void setupRecyclerView() {
        adapter = new ResultsAdapter(questions, userAnswers);
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultsRecyclerView.setAdapter(adapter);
    }

    private void updateScore() {
        int correctAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (userAnswers.get(i) == questions.get(i).getCorrectAnswerIndex()) {
                correctAnswers++;
            }
        }
        scoreText.setText(String.format("Правильных ответов: %d из %d", correctAnswers, questions.size()));
    }

    private void setupButtons() {
        retryButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("questions", questions);
            startActivity(intent);
            finish();
        });

        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}