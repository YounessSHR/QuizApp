package com.quizapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Gestionnaire des SharedPreferences pour stocker les données persistantes simples 
 * comme le meilleur score. Utilise le pattern Singleton.
 */
public class SharedPrefManager {
    private static final String PREF_NAME = "QuizPrefs";
    private static final String KEY_BEST_SCORE = "best_score";
    private SharedPreferences sharedPreferences;
    private static SharedPrefManager instance;

    private SharedPrefManager(Context context) {
        // Initialisation des SharedPreferences en mode privé
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Retourne l'instance unique du gestionnaire.
     */
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * Sauvegarde le nouveau meilleur score.
     */
    public void saveBestScore(int score) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_BEST_SCORE, score);
        editor.apply(); // apply() est asynchrone et plus performant que commit()
    }

    /**
     * Récupère le meilleur score enregistré (0 par défaut).
     */
    public int getBestScore() {
        return sharedPreferences.getInt(KEY_BEST_SCORE, 0);
    }
}
