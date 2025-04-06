package com.anahit.biologyquiz;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class QuizStats {
    private List<com.anahit.biologyquiz.QuizResult> results;

    public QuizStats(Context context) {
        // Load results from storage or initialize with sample data
        results = new ArrayList<>();
        // Sample data
        results.add(new com.anahit.biologyquiz.QuizResult(5, 80));
        results.add(new com.anahit.biologyquiz.QuizResult(4, 90));
        results.add(new com.anahit.biologyquiz.QuizResult(3, 70));
    }

    public List<com.anahit.biologyquiz.QuizResult> getResults() {
        return results;
    }

    public float getAverageScore() {
        if (results.isEmpty()) return 0;
        float totalScore = 0;
        for (com.anahit.biologyquiz.QuizResult result : results) {
            totalScore += result.getScore();
        }
        return totalScore / results.size();
    }

    public int getBestScore() {
        int bestScore = 0;
        for (com.anahit.biologyquiz.QuizResult result : results) {
            if (result.getScore() > bestScore) {
                bestScore = result.getScore();
            }
        }
        return bestScore;
    }
}