package com.notgodzilla.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button cheatButton;

    private TextView questionTextView;
    private int currentQuestionIndex = 0;

    private boolean userCheated = false;

    //Key for key-value pair stored in Bundle when saving
    private static final String KEY_INDEX = "index";
    private static final String TAG = "Quiz";

    private static final int REQUEST_KEY_CODE_CHEAT_ACTIVITY = 0;

    private Question[] questionBank = {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false)
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstance");
        outState.putInt(KEY_INDEX, currentQuestionIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = questionBank[currentQuestionIndex].getTextResId();
        questionTextView.setText(question);


        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (Button) findViewById(R.id.next_button);
        cheatButton = (Button) findViewById(R.id.cheat_button);

        setUpButtonListeners();
        updateQuestion();
    }

    private void setUpButtonListeners() {
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionIndex = (currentQuestionIndex + 1) & questionBank.length;
                updateQuestion();
            }
        });

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answer = questionBank[currentQuestionIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answer);
                startActivityForResult(i, REQUEST_KEY_CODE_CHEAT_ACTIVITY);
            }
        });
    }

    private void checkAnswer(boolean userInput) {
        boolean userGaveCorrectAnswer = questionBank[currentQuestionIndex].isAnswerTrue() && userInput;
        int messageRId = 0;

        if (userCheated) {
            messageRId = R.string.judgment_toast;
        } else if (userGaveCorrectAnswer && !userCheated) {
            messageRId = R.string.correct_toast;
        } else {
            messageRId = R.string.incorrect_toast;
        }

        Toast toast = Toast.makeText(this, messageRId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, Gravity.CENTER, Gravity.CENTER);
        toast.show();
    }

    private void updateQuestion() {
        int question = questionBank[currentQuestionIndex].getTextResId();
        questionTextView.setText(question);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_KEY_CODE_CHEAT_ACTIVITY) {
            if (data != null) {
                userCheated = CheatActivity.wasAnswerShown(data);
            }
        }

    }
}
