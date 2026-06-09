package com.quizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.quizapp.R;
import com.quizapp.database.DatabaseHelper;
import com.quizapp.models.Score;
import com.quizapp.utils.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Activité affichant le résultat final du quiz, gérant l'enregistrement du score
 * dans la base de données et la mise à jour du meilleur score.
 */
public class ResultActivity extends AppCompatActivity {

    private TextView tvFinalScore, tvBestScoreResult;
    private Button btnReplay, btnGoToHistory;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Liaison des vues
        tvFinalScore = findViewById(R.id.tvFinalScore);
        tvBestScoreResult = findViewById(R.id.tvBestScoreResult);
        btnReplay = findViewById(R.id.btnReplay);
        btnGoToHistory = findViewById(R.id.btnGoToHistory);

        dbHelper = new DatabaseHelper(this);

        // Récupération du score passé par QuizActivity via l'Intent
        int score = getIntent().getIntExtra("FINAL_SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);

        // Affichage du score final
        tvFinalScore.setText(score + " / " + totalQuestions);

        // Gestion du Meilleur Score via SharedPreferences
        SharedPrefManager prefManager = SharedPrefManager.getInstance(this);
        int bestScore = prefManager.getBestScore();

        // Si le score actuel est supérieur au record, on le sauvegarde
        if (score > bestScore) {
            prefManager.saveBestScore(score);
            bestScore = score;
        }
        tvBestScoreResult.setText("Meilleur Score : " + bestScore);

        // Enregistrement du score dans l'historique SQLite
        // Formatage de la date actuelle (ex: 25-12-2023 14:30)
        String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date());
        dbHelper.addScore(new Score(score, currentDate));

        // Bouton pour recommencer le quiz
        btnReplay.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            startActivity(intent);
            finish();
        });

        // Bouton pour consulter l'historique complet
        btnGoToHistory.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
}
