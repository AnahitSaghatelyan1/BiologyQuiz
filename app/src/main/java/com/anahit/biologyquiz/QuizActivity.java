package com.anahit.biologyquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private TextView questionText, scoreText;
    private Button[] answerButtons;
    private int currentQuestionIndex = 0;
    private int score = 0;

    String[][] questions = {
            {"What is the powerhouse of the cell?", "Nucleus", "Mitochondria", "Ribosome", "Chloroplast", "B"},
            {"Which vitamin is produced when skin is exposed to sunlight?", "Vitamin A", "Vitamin B12", "Vitamin C", "Vitamin D", "D"},
            {"Which blood cells fight infection?", "Red cells", "White cells", "Platelets", "Plasma", "B"},
            {"Which organ filters blood in the human body?", "Liver", "Heart", "Kidney", "Lungs", "C"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initViews();
        loadQuestion();
    }

    private void initViews() {
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);

        answerButtons = new Button[4];
        answerButtons[0] = findViewById(R.id.answer1Button);
        answerButtons[1] = findViewById(R.id.answer2Button);
        answerButtons[2] = findViewById(R.id.answer3Button);
        answerButtons[3] = findViewById(R.id.answer4Button);

        View.OnClickListener answerListener = view -> {
            Button clickedButton = (Button) view;
            checkAnswer(clickedButton.getText().toString());
        };

        for (Button button : answerButtons) {
            button.setOnClickListener(answerListener);
        }
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionText.setText(questions[currentQuestionIndex][0]);
            answerButtons[0].setText(questions[currentQuestionIndex][1]);
            answerButtons[1].setText(questions[currentQuestionIndex][2]);
            answerButtons[2].setText(questions[currentQuestionIndex][3]);
            answerButtons[3].setText(questions[currentQuestionIndex][4]);
        } else {
            Toast.makeText(this, "Quiz Finished! Final Score: " + score, Toast.LENGTH_LONG).show();
            for (Button button : answerButtons) {
                button.setEnabled(false);
            }
        }
    }

    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = "";

        switch (questions[currentQuestionIndex][5]) {
            case "A": correctAnswer = questions[currentQuestionIndex][1]; break;
            case "B": correctAnswer = questions[currentQuestionIndex][2]; break;
            case "C": correctAnswer = questions[currentQuestionIndex][3]; break;
            case "D": correctAnswer = questions[currentQuestionIndex][4]; break;
        }

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
}