package com.lipin.Model;

public class AllExerciseModel {
    int numberOfQuestions;
    int questionsTime;
    boolean autoSave;

    public AllExerciseModel(){
        this.autoSave = true;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getQuestionsTime() {
        return questionsTime;
    }

    public void setQuestionsTime(int questionsTime) {
        this.questionsTime = questionsTime;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }
}
