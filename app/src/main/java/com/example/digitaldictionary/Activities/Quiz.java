package com.example.digitaldictionary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.digitaldictionary.Models.Question;
import com.example.digitaldictionary.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {
    private TextView tvQuestion;
    private AppCompatButton btnOption1, btnOption2, btnOption3, btnOption4;
    private ProgressBar progressBar;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        // Initialize Views
        tvQuestion = findViewById(R.id.tv_question);
        btnOption1 = findViewById(R.id.btn_option1);
        btnOption2 = findViewById(R.id.btn_option2);
        btnOption3 = findViewById(R.id.btn_option3);
        btnOption4 = findViewById(R.id.btn_option4);
        progressBar = findViewById(R.id.progress_bar);

        // Load Questions
        loadQuestions();
        displayQuestion();

        // Set Button Listeners
        View.OnClickListener optionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String selectedAnswer = clickedButton.getText().toString();
                checkAnswer(selectedAnswer);
            }
        };

        btnOption1.setOnClickListener(optionClickListener);
        btnOption2.setOnClickListener(optionClickListener);
        btnOption3.setOnClickListener(optionClickListener);
        btnOption4.setOnClickListener(optionClickListener);
    }
    private void loadQuestions() {
        questionList = new ArrayList<>();

        // Sample Questions
        questionList.add(new Question("Q1: Eloquent (Meaning)?", "Fluent or persuasive in speaking or writing.",
                new String[]{"Verbose", "Articulate", "Silent", "Shy"}, "Articulate"));

        questionList.add(new Question("Q2: Quote: 'To be, or not to be, that is the question.'",
                "Choose the correct word associated with this quote.",
                new String[]{"Existence", "Life", "Death", "Indecision"}, "Existence"));

        questionList.add(new Question("Q3: Serendipity (Sentence)?", "The occurrence of events by chance in a happy way.",
                new String[]{"Misfortune", "Coincidence", "Accident", "Fortuity"}, "Fortuity"));

        // Add more questions as needed

        // Shuffle questions to randomize
        Collections.shuffle(questionList);
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionIndex);
            tvQuestion.setText(currentQuestion.getQuestionText());

            // Shuffle options to randomize
            List<String> options = new ArrayList<>();
            Collections.addAll(options, currentQuestion.getOptions());
            Collections.shuffle(options);

            btnOption1.setText(options.get(0));
            btnOption2.setText(options.get(1));
            btnOption3.setText(options.get(2));
            btnOption4.setText(options.get(3));

            // Update ProgressBar
            int progress = (int) (((double) currentQuestionIndex / questionList.size()) * 100);
            progressBar.setProgress(progress);
        } else {
            // Quiz Finished
            Intent intent = new Intent(Quiz.this, Result.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("TOTAL", questionList.size());
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer(String selectedAnswer) {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong! Correct Answer: " + currentQuestion.getCorrectAnswer(), Toast.LENGTH_SHORT).show();
        }
        currentQuestionIndex++;
        displayQuestion();
    }
}