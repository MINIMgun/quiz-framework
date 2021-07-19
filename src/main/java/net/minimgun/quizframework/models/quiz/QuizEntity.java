package net.minimgun.quizframework.models.quiz;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import net.minimgun.quizframework.models.quiz.entities.QuestionImpl;
import net.minimgun.quizframework.models.quiz.entities.QuizSettingsImpl;
import net.minimgun.quizframework.models.quiz.interfaces.Quiz;

@Entity
public class QuizEntity implements Quiz {

    private @Id String id;
    @OneToMany
    private List<QuestionImpl> questions;
    @OneToOne
    private QuizSettingsImpl quizSettings;
    private String quizName;

    public QuizEntity(String id, List<QuestionImpl> questions, QuizSettingsImpl quizSettings, String quizName) {
        super();
        this.id = id;
        this.questions = questions;
        this.quizSettings = quizSettings;
        this.quizName = quizName;
    }

    public QuizEntity() {
        super();

    }

    @Override
    public List<QuestionImpl> getQuestions() {
        return questions;
    }

    @Override
    public QuizSettingsImpl getQuizSettings() {
        return quizSettings;
    }

    @Override
    public String getQuizName() {
        return quizName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestions(List<QuestionImpl> questions) {
        this.questions = questions;
    }

    public void setQuizSettings(QuizSettingsImpl quizSettings) {
        this.quizSettings = quizSettings;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

}
