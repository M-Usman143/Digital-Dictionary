package com.example.digitaldictionary.Models;

public class TrendingWords {
    private String word;
    private String definition;
    private String pronunciation;
    private String example;
    private int imageResource; // Optional: Image resource for the word

    public TrendingWords(String word, String definition, String pronunciation, String example, int imageResource) {
        this.word = word;
        this.definition = definition;
        this.pronunciation = pronunciation;
        this.example = example;
        this.imageResource = imageResource;
    }

    // Getters and Setters
    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getExample() {
        return example;
    }

    public int getImageResource() {
        return imageResource;
    }
}


