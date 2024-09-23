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

import com.example.digitaldictionary.Adapters.FavoriteWordAdapter;
import com.example.digitaldictionary.Models.TrendingWords;
import com.example.digitaldictionary.R;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends AppCompatActivity {

    private RecyclerView recyclerViewFavoriteWords;
    private FavoriteWordAdapter favoriteWordAdapter;
    private List<TrendingWords> favoriteWordList;

    private LinearLayout layoutEmptyState;
    private EditText editTextSearchFavorites;

    private ImageView ivEmptyFavourites;
    private TextView tvEmptyFavourites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerViewFavoriteWords = findViewById(R.id.recyclerview_favorite_words);
        layoutEmptyState = findViewById(R.id.layout_empty_state);
        editTextSearchFavorites = findViewById(R.id.edittext_search_favorites);
        ivEmptyFavourites = findViewById(R.id.iv_empty_favourites);
        tvEmptyFavourites = findViewById(R.id.tv_empty_favourites);

        recyclerViewFavoriteWords.setHasFixedSize(true);
        recyclerViewFavoriteWords.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        favoriteWordList = new ArrayList<>();

        // Load favorite words from your data source
        loadFavoriteWords();

        // Initialize Adapter
        favoriteWordAdapter = new FavoriteWordAdapter(this, favoriteWordList);
        recyclerViewFavoriteWords.setAdapter(favoriteWordAdapter);

        // Check if favorites exist
        toggleEmptyState();

        // Implement search functionality
        editTextSearchFavorites.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                favoriteWordAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });
    }

    // Method to load favorite words (Replace with your data retrieval logic)
    private void loadFavoriteWords() {
        // Example: Adding sample favorite words
        favoriteWordList.add(new TrendingWords(
                "Ebullient",
                "Cheerful and full of energy.",
                "/iˈbʌliənt/",
                "Her ebullient personality made everyone around her feel joyful.",
                R.drawable.ic_word_placeholder
        ));

        favoriteWordList.add(new TrendingWords(
                "Serendipity",
                "The occurrence of events by chance in a happy or beneficial way.",
                "/ˌserənˈdɪpɪti/",
                "Finding that book in the library was pure serendipity.",
                R.drawable.ic_word_placeholder
        ));
    }

    // Method to toggle visibility between RecyclerView and Empty State
    private void toggleEmptyState() {
        if (favoriteWordList.isEmpty()) {
            recyclerViewFavoriteWords.setVisibility(View.GONE);
            layoutEmptyState.setVisibility(View.VISIBLE);
            ivEmptyFavourites.setVisibility(View.VISIBLE);
            tvEmptyFavourites.setVisibility(View.VISIBLE);
        } else {
            recyclerViewFavoriteWords.setVisibility(View.VISIBLE);
            layoutEmptyState.setVisibility(View.GONE);
            ivEmptyFavourites.setVisibility(View.GONE);
            tvEmptyFavourites.setVisibility(View.GONE);
        }
    }
}