package com.quizapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.quizapp.models.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe gérant la base de données SQLite pour stocker l'historique des scores.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Configuration de la base de données
    private static final String DATABASE_NAME = "QuizDB";
    private static final int DATABASE_VERSION = 1;

    // Table "Scores" et ses colonnes
    private static final String TABLE_SCORES = "Scores";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Création de la table lors du premier lancement.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORES_TABLE = "CREATE TABLE " + TABLE_SCORES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SCORE + " INTEGER,"
                + COLUMN_DATE + " TEXT" + ")";
        db.execSQL(CREATE_SCORES_TABLE);
    }

    /**
     * Mise à jour de la structure de la base de données.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    /**
     * Ajoute un nouveau score dans la base de données.
     */
    public void addScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score.getScore()); // Enregistre la valeur du score
        values.put(COLUMN_DATE, score.getDate());   // Enregistre la date
        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    /**
     * Récupère tous les scores de la base de données par ordre décroissant (du plus récent au plus ancien).
     */
    public List<Score> getAllScores() {
        List<Score> scoreList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SCORES + " ORDER BY " + COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Parcours du curseur pour remplir la liste
        if (cursor.moveToFirst()) {
            do {
                Score score = new Score(
                        cursor.getInt(0), // id
                        cursor.getInt(1), // score
                        cursor.getString(2) // date
                );
                scoreList.add(score);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scoreList;
    }
}
