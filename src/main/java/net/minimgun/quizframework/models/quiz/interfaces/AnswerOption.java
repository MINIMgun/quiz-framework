package net.minimgun.quizframework.models.quiz.interfaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;

import net.minimgun.quizframework.enums.AnswerOptionType;

@Entity
public abstract class AnswerOption {

    private @Id @GeneratedValue long id;

    public abstract AnswerOptionType getAnswerOptionType();

    @Transient
    public abstract boolean isCorrectValue(Object value);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
