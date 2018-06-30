package com.notgodzilla.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;

    private Toast falseToast;
    private Toast correctToast;

    private TextView questionTextView;
    private int currentQuestionIndex = 0;


    private Question[] questionBank = {
           new Question(R.string.question_australia, true),
           new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = questionBank[currentQuestionIndex].getTextResId();
        questionTextView.setText(  question);

        correctToast = Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
        correctToast.setGravity(Gravity.TOP, Gravity.CENTER, Gravity.CENTER);

        falseToast = Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
        falseToast.setGravity(Gravity.TOP, Gravity.CENTER, Gravity.CENTER);

        trueButton = (Button) findViewById(R.id.true_button);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionIndex = (currentQuestionIndex +1)& questionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void checkAnswer(boolean userInput) {
        boolean userGaveCorrectAnswer = questionBank[currentQuestionIndex].isAnswerTrue() && userInput;
        if (userGaveCorrectAnswer) {
            correctToast.show();
        } else {
            falseToast.show();
        }
    }

    private void updateQuestion() {
     int question = questionBank[currentQuestionIndex].getTextResId();
     questionTextView.setText(question);
    }




}
