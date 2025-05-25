package com.anahit.biologyquiz;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    public String question;
    public List<String> answers;
    public int correctAnswerIndex;

    public Question(String question, List<String> answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}