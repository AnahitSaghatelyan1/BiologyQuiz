package com.anahit.biologyquiz;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private String questionText;
    private List<String> answers;
    private int correctAnswerIndex;

    public Question(String questionText, List<String> answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}