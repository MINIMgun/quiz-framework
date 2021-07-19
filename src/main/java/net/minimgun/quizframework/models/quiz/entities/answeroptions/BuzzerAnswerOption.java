package net.minimgun.quizframework.models.quiz.entities.answeroptions;

import javax.persistence.Entity;

import net.minimgun.quizframework.enums.AnswerOptionType;
import net.minimgun.quizframework.models.quiz.interfaces.AnswerOption;

@Entity
public class BuzzerAnswerOption extends AnswerOption {

    private AnswerOptionType answerOptionType = AnswerOptionType.BUZZER;

    public BuzzerAnswerOption() {
        super();
    }

    public AnswerOptionType getAnswerOptionType() {
        return answerOptionType;
    }

    public boolean isCorrectValue(Object value) {
        return true;
    }

    public void setAnswerOptionType(AnswerOptionType answerOptionType) {
        this.answerOptionType = answerOptionType;
    }
}
