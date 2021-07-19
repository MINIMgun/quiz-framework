package net.minimgun.quizframework.models.quiz.entities.answeroptions;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;

import net.minimgun.quizframework.enums.AnswerOptionType;
import net.minimgun.quizframework.models.quiz.interfaces.AnswerOption;

@Entity
public class OptionAnswerOption extends AnswerOption {

    private AnswerOptionType answerOptionType = AnswerOptionType.OPTION;
    @Lob
    private List<String> options;
    private String correctValue;

    public OptionAnswerOption(List<String> options, String correctValue) {
        super();
        this.options = options;
        this.correctValue = correctValue;
    }

    public OptionAnswerOption() {
        super();
    }

    public AnswerOptionType getAnswerOptionType() {
        return answerOptionType;
    }

    public boolean isCorrectValue(Object value) {
        return correctValue.equals(value);
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectValue() {
        return correctValue;
    }

    public void setCorrectValue(String correctValue) {
        this.correctValue = correctValue;
    }

    public void setAnswerOptionType(AnswerOptionType answerOptionType) {
        this.answerOptionType = answerOptionType;
    }
}
