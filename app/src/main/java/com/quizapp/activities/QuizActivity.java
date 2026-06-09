package com.quizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.quizapp.R;
import com.quizapp.models.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Activité principale du jeu qui gère l'affichage des questions et le calcul du score.
 */
public class QuizActivity extends AppCompatActivity {

    private TextView tvScore, tvQuestion;
    private RadioGroup rgChoices;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnNext;

    private List<Question> questionList; // Liste contenant toutes les questions du quiz
    private int currentQuestionIndex = 0; // Index de la question actuellement affichée
    private int score = 0;                 // Score de l'utilisateur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Liaison des vues
        tvScore = findViewById(R.id.tvScore);
        tvQuestion = findViewById(R.id.tvQuestion);
        rgChoices = findViewById(R.id.rgChoices);
        rb1 = findViewById(R.id.rbChoice1);
        rb2 = findViewById(R.id.rbChoice2);
        rb3 = findViewById(R.id.rbChoice3);
        rb4 = findViewById(R.id.rbChoice4);
        btnNext = findViewById(R.id.btnNext);

        // Initialisation de la liste de questions et affichage de la première
        loadQuestions();
        displayQuestion();

        // Gestion du clic sur le bouton "Suivant"
        btnNext.setOnClickListener(v -> {
            int selectedId = rgChoices.getCheckedRadioButtonId();
            
            // Vérification si une option est sélectionnée
            if (selectedId == -1) {
                Toast.makeText(this, "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show();
                return;
            }

            // Récupération de l'index de la réponse choisie (1 à 4)
            RadioButton selectedRadioButton = findViewById(selectedId);
            int answerIndex = rgChoices.indexOfChild(selectedRadioButton) + 1;

            // Vérification de la réponse
            if (answerIndex == questionList.get(currentQuestionIndex).getBonneReponse()) {
                score++;
                tvScore.setText("Score: " + score);
            }

            // Passage à la question suivante
            currentQuestionIndex++;

            if (currentQuestionIndex < questionList.size()) {
                displayQuestion();
            } else {
                // Si c'est la fin du quiz, on envoie les résultats à ResultActivity
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("FINAL_SCORE", score);
                intent.putExtra("TOTAL_QUESTIONS", questionList.size());
                startActivity(intent);
                finish(); // On ferme QuizActivity pour ne pas pouvoir y revenir avec le bouton "Retour"
            }
        });
    }

    /**
     * Charge une liste statique de questions.
     */
    private void loadQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("Quelle est la capitale de la France ?", "Londres", "Berlin", "Paris", "Madrid", 3));
        questionList.add(new Question("Quel est le plus grand océan du monde ?", "Atlantique", "Indien", "Arctique", "Pacifique", 4));
        questionList.add(new Question("Qui a peint la Joconde ?", "Van Gogh", "Picasso", "Léonard de Vinci", "Claude Monet", 3));
        questionList.add(new Question("Quelle est la planète la plus proche du soleil ?", "Vénus", "Mars", "Mercure", "Jupiter", 3));
        questionList.add(new Question("En quelle année a commencé la Seconde Guerre mondiale ?", "1914", "1939", "1945", "1918", 2));
    }

    /**
     * Affiche la question actuelle et ses choix de réponses.
     */
    private void displayQuestion() {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        tvQuestion.setText(currentQuestion.getQuestion());
        rb1.setText(currentQuestion.getChoix1());
        rb2.setText(currentQuestion.getChoix2());
        rb3.setText(currentQuestion.getChoix3());
        rb4.setText(currentQuestion.getChoix4());
        rgChoices.clearCheck(); // Décocher les boutons radio pour la nouvelle question
    }
}
