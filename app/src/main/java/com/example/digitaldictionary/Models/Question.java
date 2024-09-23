package com.example.digitaldictionary.Models;


public class Question {
    private String questionText;
    private String meaningOrPrompt;
    private String[] options;
    private String correctAnswer;

    public Question(String questionText, String meaningOrPrompt, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.meaningOrPrompt = meaningOrPrompt;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getMeaningOrPrompt() {
        return meaningOrPrompt;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

