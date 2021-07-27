package net.minimgun.quizframework.models.quiz.entities.answeroptions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RangeAnswerOption{
    
    private @Id @GeneratedValue long id;
    private double correctValue;

    public RangeAnswerOption(double correctValue) {
        super();
        this.correctValue = correctValue;
    }

    public RangeAnswerOption() {
        super();
    }


    public double getCorrectValue() {
        return correctValue;
    }

    public void setCorrectValue(double correctValue) {
        this.correctValue = correctValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
