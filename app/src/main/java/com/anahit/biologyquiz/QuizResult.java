package com.anahit.biologyquiz;

import java.io.Serializable;

public class QuizResult implements Serializable {
    private int quizId;
    private int score;

    public QuizResult(int quizId, int score) {
        this.quizId = quizId;
        this.score = score;
    }

    public int getQuizId() {
        return quizId;
    }

    public int getScore() {
        return score;
    }
}