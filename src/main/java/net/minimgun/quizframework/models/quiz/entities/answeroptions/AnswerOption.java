package net.minimgun.quizframework.models.quiz.entities.answeroptions;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import net.minimgun.quizframework.enums.AnswerOptionType;

@Entity
public class AnswerOption {

    private @Id @GeneratedValue long id;
    private AnswerOptionType answerOptionType;
    @OneToOne(cascade = { CascadeType.ALL })
    private OptionAnswerOption optionAnswerOption;
    @OneToOne(cascade = { CascadeType.ALL })
    private RangeAnswerOption rangeAnswerOption;

    public AnswerOption() {
        super();
    }

    public OptionAnswerOption getOptionAnswerOption() {
        return optionAnswerOption;
    }

    public void setOptionAnswerOption(OptionAnswerOption optionAnswerOption) {
        this.optionAnswerOption = optionAnswerOption;
    }

    public RangeAnswerOption getRangeAnswerOption() {
        return rangeAnswerOption;
    }

    public void setRangeAnswerOption(RangeAnswerOption rangeAnswerOption) {
        this.rangeAnswerOption = rangeAnswerOption;
    }

    public void setAnswerOptionType(AnswerOptionType answerOptionType) {
        this.answerOptionType = answerOptionType;
    }

    public AnswerOptionType getAnswerOptionType() {
        return answerOptionType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AnswerOption [id=" + id + ", answerOptionType=" + answerOptionType + ", optionAnswerOption="
                + optionAnswerOption + ", rangeAnswerOption=" + rangeAnswerOption + "]";
    }
}
