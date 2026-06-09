package com.quizapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quizapp.R;
import com.quizapp.models.Score;

import java.util.List;

/**
 * Adaptateur pour afficher la liste des scores dans le RecyclerView de HistoryActivity.
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private List<Score> scoreList; // Liste des scores à afficher

    public ScoreAdapter(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    /**
     * Crée une nouvelle vue pour un élément de la liste (item_score.xml).
     */
    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    /**
     * Lie les données d'un score à la vue (ViewHolder).
     */
    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score score = scoreList.get(position);
        holder.tvScore.setText("Score: " + score.getScore());
        holder.tvDate.setText(score.getDate());
    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    /**
     * Classe interne pour maintenir les références des vues de chaque élément.
     */
    public static class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView tvScore, tvDate;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvScore = itemView.findViewById(R.id.tvScoreItem);
            tvDate = itemView.findViewById(R.id.tvDateItem);
        }
    }
}
