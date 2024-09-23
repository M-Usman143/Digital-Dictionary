package com.example.digitaldictionary.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitaldictionary.Adapters.HistoryWordAdapter;
import com.example.digitaldictionary.Models.TrendingWords;
import com.example.digitaldictionary.R;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    private RecyclerView recyclerViewHistoryWords;
    private HistoryWordAdapter historyWordAdapter;
    private List<TrendingWords> historyWordList;

    private LinearLayout layoutEmptyState;
    private EditText editTextSearchHistory;
    private ImageView ivEmptyHistory;
    private TextView tvEmptyHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        recyclerViewHistoryWords = findViewById(R.id.recyclerview_history_words);
        layoutEmptyState = findViewById(R.id.layout_empty_state);
        editTextSearchHistory = findViewById(R.id.edittext_search_history);
        ivEmptyHistory = findViewById(R.id.iv_empty_history);
        tvEmptyHistory = findViewById(R.id.tv_empty_history);

        recyclerViewHistoryWords.setHasFixedSize(true);
        recyclerViewHistoryWords.setLayoutManager(new LinearLayoutManager(this));

        historyWordList = new ArrayList<>();

        // Load history words
        loadHistoryWords();

        // Initialize Adapter
        historyWordAdapter = new HistoryWordAdapter(this, historyWordList, this::onEmptyStateChanged);
        recyclerViewHistoryWords.setAdapter(historyWordAdapter);


        // Implement search functionality
        editTextSearchHistory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                historyWordAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Method to load history words (Sample data)
    private void loadHistoryWords() {
        // Example: Adding sample history words
        historyWordList.add(new TrendingWords(
                "Ebullient",
                "Cheerful and full of energy.",
                "/iˈbʌliənt/",
                "Her ebullient personality made everyone around her feel joyful.",
                R.drawable.ic_word_placeholder
        ));

        historyWordList.add(new TrendingWords(
                "Serendipity",
                "The occurrence of events by chance in a happy or beneficial way.",
                "/ˌserənˈdɪpɪti/",
                "Finding that book in the library was pure serendipity.",
                R.drawable.ic_word_placeholder
        ));
    }


    public void onEmptyStateChanged(boolean isEmpty) {
        if (isEmpty) {
            recyclerViewHistoryWords.setVisibility(View.GONE);
            layoutEmptyState.setVisibility(View.VISIBLE);
            ivEmptyHistory.setVisibility(View.VISIBLE);
            tvEmptyHistory.setVisibility(View.VISIBLE);
        } else {
            recyclerViewHistoryWords.setVisibility(View.VISIBLE);
            layoutEmptyState.setVisibility(View.GONE);
            ivEmptyHistory.setVisibility(View.GONE);
            tvEmptyHistory.setVisibility(View.GONE);
        }
    }
    }
