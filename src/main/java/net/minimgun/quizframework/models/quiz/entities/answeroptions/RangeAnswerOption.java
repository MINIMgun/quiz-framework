package net.minimgun.quizframework.models.quiz.entities.answeroptions;

import javax.persistence.Entity;

import net.minimgun.quizframework.enums.AnswerOptionType;
import net.minimgun.quizframework.models.quiz.interfaces.AnswerOption;

@Entity
public class RangeAnswerOption extends AnswerOption {

    private AnswerOptionType answerOptionType = AnswerOptionType.RANGE;
    private double correctValue;

    public RangeAnswerOption(double correctValue) {
        super();
        this.correctValue = correctValue;
    }

    public RangeAnswerOption() {
        super();
    }

    @Override
    public AnswerOptionType getAnswerOptionType() {
        return answerOptionType;
    }

    @Override
    public boolean isCorrectValue(Object value) {
        return true;
    }

    public double getCorrectValue() {
        return correctValue;
    }

    public void setCorrectValue(double correctValue) {
        this.correctValue = correctValue;
    }

    public void setAnswerOptionType(AnswerOptionType answerOptionType) {
        this.answerOptionType = answerOptionType;
    }
}
