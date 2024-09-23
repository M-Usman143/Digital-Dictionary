package com.example.digitaldictionary.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "favorite_words")
public class FavouriteWord {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String word;
    private String definition;
    private String pronunciation;
    private String example;
    private int imageResource; // Drawable resource ID

    // Constructors
    public FavouriteWord(String word, String definition, String pronunciation, String example, int imageResource) {
        this.word = word;
        this.definition = definition;
        this.pronunciation = pronunciation;
        this.example = example;
        this.imageResource = imageResource;
    }

    // Getters and Setters

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getWord() { return word; }

    public void setWord(String word) { this.word = word; }

    public String getDefinition() { return definition; }

    public void setDefinition(String definition) { this.definition = definition; }

    public String getPronunciation() { return pronunciation; }

    public void setPronunciation(String pronunciation) { this.pronunciation = pronunciation; }

    public String getExample() { return example; }

    public void setExample(String example) { this.example = example; }

    public int getImageResource() { return imageResource; }

    public void setImageResource(int imageResource) { this.imageResource = imageResource; }
}
