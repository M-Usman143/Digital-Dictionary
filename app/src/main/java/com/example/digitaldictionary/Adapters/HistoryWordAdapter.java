package com.example.digitaldictionary.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitaldictionary.Models.TrendingWords;
import com.example.digitaldictionary.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryWordAdapter extends RecyclerView.Adapter<HistoryWordAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<TrendingWords> historyWordsList;
    private List<TrendingWords> historyWordsListFull;
    private OnEmptyStateListener emptyStateListener;


    public HistoryWordAdapter(Context context, List<TrendingWords> historyWordsList, OnEmptyStateListener emptyStateListener) {
        this.context = context;
        this.historyWordsList = historyWordsList;
        this.historyWordsListFull = new ArrayList<>(historyWordsList);
        this.emptyStateListener = emptyStateListener;

        // Notify the listener if the list is empty
        if (historyWordsList.isEmpty()) {
            emptyStateListener.onEmptyStateChanged(true);
        } else {
            emptyStateListener.onEmptyStateChanged(false);
        }
    }
    public interface OnEmptyStateListener {
        void onEmptyStateChanged(boolean isEmpty);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history_word, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrendingWords word = historyWordsList.get(position);
        holder.textViewWord.setText(word.getWord());
        holder.textViewMeaning.setText(word.getDefinition());

        holder.buttonRemove.setOnClickListener(v -> {
            historyWordsList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, historyWordsList.size());
            emptyStateListener.onEmptyStateChanged(historyWordsList.isEmpty());
        });
    }

    @Override
    public int getItemCount() {
        return historyWordsList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<TrendingWords> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(historyWordsListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (TrendingWords word : historyWordsListFull) {
                        if (word.getWord().toLowerCase().contains(filterPattern)) {
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
                historyWordsList.clear();
                historyWordsList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewWord;
        TextView textViewMeaning;
        Button buttonRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.text_view_word);
            textViewMeaning = itemView.findViewById(R.id.text_view_meaning);
            buttonRemove = itemView.findViewById(R.id.button_remove);
        }
    }
}

