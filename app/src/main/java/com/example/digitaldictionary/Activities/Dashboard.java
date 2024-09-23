package com.example.digitaldictionary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitaldictionary.Adapters.TrendingWordAdapter;
import com.example.digitaldictionary.Models.TrendingWords;
import com.example.digitaldictionary.R;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    private RecyclerView recyclerViewTrendingWords;
    private TrendingWordAdapter trendingWordAdapter;
    private List<TrendingWords> trendingWordList;
    private LinearLayout favoutiteLayout , quizLayout , settingsLayout , historyLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        favoutiteLayout = findViewById(R.id.favouriteLayout);
        quizLayout = findViewById(R.id.quizzLayout);
        settingsLayout = findViewById(R.id.settingLayout);
        historyLayout = findViewById(R.id.historyLayout);

        addsampleData();

        favoutiteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Favorite.class);
                startActivity(intent);
            }
        });
        quizLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Quiz.class);
                startActivity(intent);
            }
        });
        historyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, History.class);
                startActivity(intent);
            }
        });
        settingsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Settings.class);
                startActivity(intent);
            }
        });



    }
    private void addsampleData(){

        recyclerViewTrendingWords = findViewById(R.id.recyclerview_trending_words);
        recyclerViewTrendingWords.setHasFixedSize(true);
        recyclerViewTrendingWords.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        trendingWordList = new ArrayList<>();
        trendingWordAdapter = new TrendingWordAdapter(this, trendingWordList);
        recyclerViewTrendingWords.setAdapter(trendingWordAdapter);
        // Add sample data to the list
        trendingWordList.add(new TrendingWords(
                "Ebullient",
                "Cheerful and full of energy.",
                "/iˈbʌliənt/",
                "Her ebullient personality made everyone around her feel joyful.",
                R.drawable.ic_favorite // Ensure this drawable exists
        ));

        trendingWordList.add(new TrendingWords(
                "Serendipity",
                "The occurrence of events by chance in a happy or beneficial way.",
                "/ˌserənˈdɪpɪti/",
                "Finding that book in the library was pure serendipity.",
                R.drawable.ic_favorite
        ));

        trendingWordList.add(new TrendingWords(
                "Resilience",
                "The capacity to recover quickly from difficulties.",
                "/rɪˈzɪliəns/",
                "Her resilience in the face of adversity was admirable.",
                R.drawable.ic_favorite
        ));

        trendingWordList.add(new TrendingWords(
                "Euphoria",
                "A feeling or state of intense excitement and happiness.",
                "/juːˈfɔːriə/",
                "Winning the championship filled him with euphoria.",
                R.drawable.ic_favorite
        ));

    }

}
