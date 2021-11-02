package net.minimgun.quizframework.models.quiz.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import net.minimgun.quizframework.models.quiz.interfaces.QuizSettings;

@Entity
public class QuizSettingsImpl implements QuizSettings {

    private @Id @GeneratedValue long id;
    private int defaultTime;
    private boolean removePointsForInvalidAnswers;
    private int pointsToRemove;
    private int pointsToAdd;

    public QuizSettingsImpl(int defaultTime, boolean removePointsForInvalidAnswers, int pointsToRemove,
            int pointsToAdd) {
        super();
        this.defaultTime = defaultTime;
        this.removePointsForInvalidAnswers = removePointsForInvalidAnswers;
        this.pointsToRemove = pointsToRemove;
        this.pointsToAdd = pointsToAdd;
    }

    public QuizSettingsImpl() {
        super();
    }

    @Override
    public int getDefaultTime() {
        return defaultTime;
    }

    @Override
    public boolean isRemovePointsForInvalidAnswers() {
        return removePointsForInvalidAnswers;
    }

    @Override
    public int getPointsToRemove() {
        return pointsToRemove;
    }

    @Override
    public int getPointsToAdd() {
        return pointsToAdd;
    }

    public void setDefaultTime(int defaultTime) {
        this.defaultTime = defaultTime;
    }

    public void setRemovePointsForInvalidAnswers(boolean removePointsForInvalidAnswers) {
        this.removePointsForInvalidAnswers = removePointsForInvalidAnswers;
    }

    public void setPointsToRemove(int pointsToRemove) {
        this.pointsToRemove = pointsToRemove;
    }

    public void setPointsToAdd(int pointsToAdd) {
        this.pointsToAdd = pointsToAdd;
    }

}
