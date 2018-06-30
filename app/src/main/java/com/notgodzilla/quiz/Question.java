package com.notgodzilla.quiz;

public class Question {
    private int textResId;
    private boolean answerTrue;

    public int getTextResId() {
        return textResId;
    }

    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    public Question(int textResId, boolean answerTrue) {
        this.answerTrue = answerTrue;
        this.textResId = textResId;

    }
}


