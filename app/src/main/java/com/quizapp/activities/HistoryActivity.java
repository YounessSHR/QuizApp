package com.quizapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quizapp.R;
import com.quizapp.adapters.ScoreAdapter;
import com.quizapp.database.DatabaseHelper;
import com.quizapp.models.Score;

import java.util.List;

/**
 * Activité affichant l'historique de tous les scores enregistrés dans la base de données.
 */
public class HistoryActivity extends AppCompatActivity {

    private RecyclerView rvHistory;
    private ScoreAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Initialisation du RecyclerView
        rvHistory = findViewById(R.id.rvHistory);
        // Utilisation d'un LinearLayoutManager pour un affichage en liste simple
        rvHistory.setLayoutManager(new LinearLayoutManager(this));

        // Initialisation de la base de données
        dbHelper = new DatabaseHelper(this);
        
        // Récupération de la liste des scores depuis SQLite
        List<Score> scoreList = dbHelper.getAllScores();

        // Configuration de l'adaptateur pour lier la liste au RecyclerView
        adapter = new ScoreAdapter(scoreList);
        rvHistory.setAdapter(adapter);
    }
}
