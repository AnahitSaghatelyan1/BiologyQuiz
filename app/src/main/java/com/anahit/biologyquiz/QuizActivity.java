package com.anahit.biologyquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView questionText, scoreText, timerTextView;
    private Button[] answerButtons;
    private int currentQuestionIndex = 0;
    private List<Question> questions = new ArrayList<>();
    private int score = 0;
    private static final int TIMER_DURATION = 10; // seconds
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Add exit button click listener
        ImageButton exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> finish());

        initViews();
        loadQuestion();
    }

    private void initViews() {
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        timerTextView = findViewById(R.id.timer_text);

        answerButtons = new Button[4];
        answerButtons[0] = findViewById(R.id.answer1Button);
        answerButtons[1] = findViewById(R.id.answer2Button);
        answerButtons[2] = findViewById(R.id.answer3Button);
        answerButtons[3] = findViewById(R.id.answer4Button);

        // Initialize questions
        questions.add(new Question(
                "What is the primary source of energy for cells?",
                new ArrayList<>(List.of("Glucose", "Starch", "Protein", "Lipid")),
                0
        ));
        questions.add(new Question(
                "What is the process of cell division called?",
                new ArrayList<>(List.of("Photosynthesis", "Mitosis", "Meiosis", "Diffusion")),
                1
        ));
        questions.add(new Question(
                "Where in the cell does protein synthesis occur?",
                new ArrayList<>(List.of("Nucleus", "Mitochondria", "Ribosomes", "Lysosomes")),
                2
        ));
        questions.add(new Question(
                "Which gas is released by plants during photosynthesis?",
                new ArrayList<>(List.of("Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen")),
                1
        ));
        questions.add(new Question(
                "What is DNA?",
                new ArrayList<>(List.of("Protein", "Carbohydrate", "Nucleic acid", "Lipid")),
                2
        ));
        questions.add(new Question(
                "Which organ is responsible for breathing in humans?",
                new ArrayList<>(List.of("Liver", "Lungs", "Kidneys", "Stomach")),
                1
        ));
        questions.add(new Question(
                "What transports oxygen in the blood?",
                new ArrayList<>(List.of("Hemoglobin", "Glucose", "Cholesterol", "Insulin")),
                0
        ));
        questions.add(new Question(
                "What is the process of converting glucose into energy called?",
                new ArrayList<>(List.of("Photosynthesis", "Respiration", "Fermentation", "Transcription")),
                1
        ));
        questions.add(new Question(
                "Which type of cells lacks a nucleus?",
                new ArrayList<>(List.of("Eukaryotes", "Prokaryotes", "Neurons", "Leukocytes")),
                1
        ));
        questions.add(new Question(
                "What regulates metabolism in the body?",
                new ArrayList<>(List.of("Enzymes", "Vitamins", "Minerals", "Lipids")),
                0
        ));

        View.OnClickListener answerListener = view -> {
            Button clickedButton = (Button) view;
            if (timer != null) timer.cancel(); // Cancel timer on answer
            checkAnswer(clickedButton.getText().toString());
        };

        for (Button button : answerButtons) {
            button.setOnClickListener(answerListener);
        }
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionText.setText(currentQuestion.question);
            
            List<String> answers = currentQuestion.answers;
            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setText(answers.get(i));
            }

            startTimer();
        } else {
            Toast.makeText(this, "Quiz Finished! Final Score: " + score, Toast.LENGTH_LONG).show();
            for (Button button : answerButtons) {
                button.setEnabled(false);
            }
            timerTextView.setText(""); // Clear timer display
        }
    }

    private void checkAnswer(String selectedAnswer) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.answers.get(currentQuestion.correctAnswerIndex);

        if (selectedAnswer.equals(correctAnswer)) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong! Correct: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        scoreText.setText("Score: " + score);
        loadQuestion();
    }

    private void startTimer() {
        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(TIMER_DURATION * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time: " + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Time: 0s");
                Toast.makeText(QuizActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                currentQuestionIndex++;
                loadQuestion();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) timer.cancel();
    }
}
