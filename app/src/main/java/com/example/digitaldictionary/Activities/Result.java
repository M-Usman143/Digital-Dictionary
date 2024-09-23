package com.example.digitaldictionary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.digitaldictionary.R;

public class Result extends AppCompatActivity {
    private TextView tvResult;
    private Button btnRestart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvResult = findViewById(R.id.tv_result);
        btnRestart = findViewById(R.id.btn_restart);

        // Get score from intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        int total = intent.getIntExtra("TOTAL", 0);

        tvResult.setText("Your Score: " + score + "/" + total);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restartIntent = new Intent(Result.this, Quiz.class);
                startActivity(restartIntent);
                finish();
            }
        });
    }
}