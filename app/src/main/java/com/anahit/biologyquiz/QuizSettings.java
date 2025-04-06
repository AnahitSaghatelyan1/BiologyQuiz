package com.anahit.biologyquiz;

import android.content.Context;
import android.content.SharedPreferences;

public class QuizSettings {
    private static final String PREF_NAME = "QuizSettings";
    private static final String KEY_QUESTIONS_COUNT = "questionsCount";
    private static final String KEY_SHOW_HINTS = "showHints";
    private static final String KEY_SHOW_EXPLANATIONS = "showExplanations";
    private static final String KEY_DARK_THEME = "darkTheme";

    private final SharedPreferences preferences;
    private int questionsCount;
    private boolean showHints;
    private boolean showExplanations;
    private boolean darkTheme;

    public QuizSettings(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        loadSettings();
    }

    private void loadSettings() {
        questionsCount = preferences.getInt(KEY_QUESTIONS_COUNT, 10);
        showHints = preferences.getBoolean(KEY_SHOW_HINTS, true);
        showExplanations = preferences.getBoolean(KEY_SHOW_EXPLANATIONS, true);
        darkTheme = preferences.getBoolean(KEY_DARK_THEME, false);
    }

    public void saveSettings() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_QUESTIONS_COUNT, questionsCount);
        editor.putBoolean(KEY_SHOW_HINTS, showHints);
        editor.putBoolean(KEY_SHOW_EXPLANATIONS, showExplanations);
        editor.putBoolean(KEY_DARK_THEME, darkTheme);
        editor.apply();
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
        saveSettings();
    }

    public boolean isShowHints() {
        return showHints;
    }

    public void setShowHints(boolean showHints) {
        this.showHints = showHints;
        saveSettings();
    }

    public boolean isShowExplanations() {
        return showExplanations;
    }

    public void setShowExplanations(boolean showExplanations) {
        this.showExplanations = showExplanations;
        saveSettings();
    }

    public boolean isDarkTheme() {
        return darkTheme;
    }

    public void setDarkTheme(boolean darkTheme) {
        this.darkTheme = darkTheme;
        saveSettings();
    }
}