package net.minimgun.quizframework.models.quiz.entities.answeroptions;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OptionAnswerOption {

    private @Id @GeneratedValue long id;
    @ElementCollection(fetch=FetchType.EAGER)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OptionAnswerOption [id=" + id + ", options=" + options + ", correctValue=" + correctValue + "]";
    }

}
