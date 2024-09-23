package com.example.digitaldictionary.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.digitaldictionary.Models.TrendingWords;
import com.example.digitaldictionary.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteWordAdapter extends RecyclerView.Adapter<FavoriteWordAdapter.FavoriteWordViewHolder> implements Filterable {

    private Context context;
    private List<TrendingWords> favoriteWordList;
    private List<TrendingWords> favoriteWordListFull; // For filtering

    public FavoriteWordAdapter(Context context, List<TrendingWords> favoriteWordList) {
        this.context = context;
        this.favoriteWordList = favoriteWordList;
        this.favoriteWordListFull = new ArrayList<>(favoriteWordList);
    }

    @NonNull
    @Override
    public FavoriteWordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_word, parent, false);
        return new FavoriteWordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteWordViewHolder holder, int position) {
        TrendingWords word = favoriteWordList.get(position);
        holder.textWord.setText(word.getWord());
        holder.textDefinition.setText(word.getDefinition());
        holder.textPronunciation.setText(word.getPronunciation());
        holder.textExample.setText(word.getExample());

        holder.imagePronunciation.setOnClickListener(v -> {
            // Implement pronunciation audio playback if available
        });

        holder.buttonRemoveFavorite.setOnClickListener(v -> {
            // Remove the word from favorites
            removeFavoriteWord(position);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteWordList.size();
    }

    // Method to remove a favorite word
    private void removeFavoriteWord(int position){
        // Implement your data removal logic here (e.g., remove from database)
        favoriteWordList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, favoriteWordList.size());

        // Optionally, update the full list for filtering
        favoriteWordListFull.remove(position);
    }

    // Filter implementation for search functionality
    @Override
    public Filter getFilter() {
        return favoriteFilter;
    }

    private Filter favoriteFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<TrendingWords> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(favoriteWordListFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(TrendingWords word : favoriteWordListFull){
                    if(word.getWord().toLowerCase().contains(filterPattern)){
                        filteredList.add(word);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            favoriteWordList.clear();
            favoriteWordList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    // ViewHolder Class
    public class FavoriteWordViewHolder extends RecyclerView.ViewHolder{

        TextView textWord, textDefinition, textPronunciation, textExample;
        ImageView imagePronunciation;
        ImageButton buttonRemoveFavorite;

        public FavoriteWordViewHolder(@NonNull View itemView) {
            super(itemView);

            textWord = itemView.findViewById(R.id.text_word);
            textDefinition = itemView.findViewById(R.id.text_definition);
            textPronunciation = itemView.findViewById(R.id.text_pronunciation);
            textExample = itemView.findViewById(R.id.text_example);
            imagePronunciation = itemView.findViewById(R.id.image_pronunciation);
            buttonRemoveFavorite = itemView.findViewById(R.id.button_remove_favorite);
        }
    }
}

