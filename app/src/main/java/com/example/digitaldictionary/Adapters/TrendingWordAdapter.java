package com.example.digitaldictionary.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitaldictionary.Models.TrendingWords;
import com.example.digitaldictionary.R;

import java.util.List;

public class TrendingWordAdapter extends RecyclerView.Adapter<TrendingWordAdapter.TrendingWordViewHolder> {

    private Context context;
    private List<TrendingWords> trendingWordList;

    // Constructor
    public TrendingWordAdapter(Context context, List<TrendingWords> trendingWordList) {
        this.context = context;
        this.trendingWordList = trendingWordList;
    }

    @NonNull
    @Override
    public TrendingWordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_word_recyclerview, parent, false);
        return new TrendingWordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingWordViewHolder holder, int position) {
        TrendingWords trendingWord = trendingWordList.get(position);
        holder.textWord.setText(trendingWord.getWord());
        holder.textDefinition.setText(trendingWord.getDefinition());
        holder.textPronunciation.setText(trendingWord.getPronunciation());
        holder.textExample.setText(trendingWord.getExample());
        holder.imageWord.setImageResource(trendingWord.getImageResource());

        // Handle pronunciation audio click
        holder.imagePronunciationAudio.setOnClickListener(v -> {
            // Implement audio playback functionality here
            Toast.makeText(context, "Play pronunciation for: " + trendingWord.getWord(), Toast.LENGTH_SHORT).show();
            // Example: Use MediaPlayer to play audio
        });

        // Handle item click to navigate to word details
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Clicked on: " + trendingWord.getWord(), Toast.LENGTH_SHORT).show();
            // Implement navigation to word detail screen
        });
    }

    @Override
    public int getItemCount() {
        return trendingWordList.size();
    }

    // ViewHolder Class
    public static class TrendingWordViewHolder extends RecyclerView.ViewHolder {
        ImageView imageWord;
        TextView textWord, textDefinition, textPronunciation, textExample;
        ImageView imagePronunciationAudio;

        public TrendingWordViewHolder(@NonNull View itemView) {
            super(itemView);
            imageWord = itemView.findViewById(R.id.image_word);
            textWord = itemView.findViewById(R.id.text_word);
            textDefinition = itemView.findViewById(R.id.text_definition);
            textPronunciation = itemView.findViewById(R.id.text_pronunciation);
            textExample = itemView.findViewById(R.id.text_example);
            imagePronunciationAudio = itemView.findViewById(R.id.image_pronunciation_audio);
        }
    }
}

