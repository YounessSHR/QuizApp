package com.quizapp.models;

/**
 * Modèle représentant un score enregistré dans l'historique.
 */
public class Score {
    private int id;        // Identifiant unique (auto-incrémenté par SQLite)
    private int score;     // Valeur du score obtenu
    private String date;   // Date de réalisation du quiz

    // Constructeur utilisé pour récupérer un score existant depuis la base de données
    public Score(int id, int score, String date) {
        this.id = id;
        this.score = score;
        this.date = date;
    }

    // Constructeur utilisé pour créer un nouveau score à enregistrer
    public Score(int score, String date) {
        this.score = score;
        this.date = date;
    }

    // Getters
    public int getId() { return id; }
    public int getScore() { return score; }
    public String getDate() { return date; }
}
