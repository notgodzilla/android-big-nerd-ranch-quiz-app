package com.notgodzilla.quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.notgodziila.quiz.answer_is_true";
    private boolean answerIsTrue;
    private TextView answerTextView;
    private Button showAnswerButton;

    private static final String TAG = "CheatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        answerTextView = (TextView) findViewById(R.id.answer_text_view);
        showAnswerButton = (Button) findViewById(R.id.show_answer_button);

        setButtonListeners();

    }

    private void setButtonListeners () {
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("onClick", TAG);
                if(answerIsTrue) {
                    answerTextView.setText(R.string.true_button);
                } else {
                    answerTextView.setText(R.string.false_button);

                }
            }
        });
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }
}
