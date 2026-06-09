package com.quizapp.models;

/**
 * Modèle représentant une question du quiz.
 */
public class Question {
    private String question;       // Texte de la question
    private String choix1;         // Option 1
    private String choix2;         // Option 2
    private String choix3;         // Option 3
    private String choix4;         // Option 4
    private int bonneReponse;      // Index de la bonne réponse (1, 2, 3 ou 4)

    public Question(String question, String choix1, String choix2, String choix3, String choix4, int bonneReponse) {
        this.question = question;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
        this.choix4 = choix4;
        this.bonneReponse = bonneReponse;
    }

    // Getters pour accéder aux données
    public String getQuestion() { return question; }
    public String getChoix1() { return choix1; }
    public String getChoix2() { return choix2; }
    public String getChoix3() { return choix3; }
    public String getChoix4() { return choix4; }
    public int getBonneReponse() { return bonneReponse; }
}
