package com.anahit.biologyquiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionManager {
    private final List<Question> questions;
    private int currentQuestionIndex;
    private int score;

    public QuestionManager() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;
        initializeQuestions();
    }

    private void initializeQuestions() {
        // Добавляем вопросы по биологии
        questions.add(new Question(
                "Какая молекула является основным источником энергии для клетки?",
                "АТФ",
                new String[]{"АТФ", "ДНК", "РНК", "Белок"}
        ));

        questions.add(new Question(
                "Какой процесс обеспечивает поступление кислорода в клетку?",
                "Диффузия",
                new String[]{"Диффузия", "Осмос", "Активный транспорт", "Эндоцитоз"}
        ));

        questions.add(new Question(
                "Какие органеллы отвечают за синтез белка?",
                "Рибосомы",
                new String[]{"Рибосомы", "Митохондрии", "Лизосомы", "Аппарат Гольджи"}
        ));

        questions.add(new Question(
                "Какой тип деления клетки приводит к образованию половых клеток?",
                "Мейоз",
                new String[]{"Мейоз", "Митоз", "Амитоз", "Эндомитоз"}
        ));

        questions.add(new Question(
                "Какое вещество является основным компонентом клеточной мембраны?",
                "Фосфолипиды",
                new String[]{"Фосфолипиды", "Белки", "Углеводы", "Нуклеиновые кислоты"}
        ));

        // Перемешиваем вопросы
        Collections.shuffle(questions);
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public boolean moveToNextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            return true;
        }
        return false;
    }

    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionIndex + 1;
    }

    public static class Question {
        private final String questionText;
        private final String correctAnswer;
        private final String[] options;

        public Question(String questionText, String correctAnswer, String[] options) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.options = options;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public String[] getOptions() {
            return options;
        }
    }
}