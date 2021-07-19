package net.minimgun.quizframework.models.quiz.interfaces;

public interface QuizSettings {

    int getDefaultTime();

    boolean isRemovePointsForInvalidAnswers();

    int getPointsToRemove();

    int getPointsToAdd();
}
