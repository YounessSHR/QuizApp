package com.quizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.quizapp.R;
import com.quizapp.utils.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    // Déclaration des éléments de l'interface utilisateur
    private TextView tvBestScore;
    private Button btnStart, btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison des variables avec les vues du layout XML
        tvBestScore = findViewById(R.id.tvBestScore);
        btnStart = findViewById(R.id.btnStart);
        btnHistory = findViewById(R.id.btnHistory);

        // Récupération et affichage du meilleur score depuis les SharedPreferences
        updateBestScoreDisplay();

        // Action du bouton "Commencer" : Lance QuizActivity
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        // Action du bouton "Historique" : Lance HistoryActivity
        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }

    // On utilise onResume pour mettre à jour le meilleur score 
    // si l'utilisateur revient de ResultActivity après avoir battu son record
    @Override
    protected void onResume() {
        super.onResume();
        updateBestScoreDisplay();
    }

    /**
     * Récupère le meilleur score via SharedPrefManager et l'affiche.
     */
    private void updateBestScoreDisplay() {
        int bestScore = SharedPrefManager.getInstance(this).getBestScore();
        tvBestScore.setText("Meilleur Score : " + bestScore);
    }
}
