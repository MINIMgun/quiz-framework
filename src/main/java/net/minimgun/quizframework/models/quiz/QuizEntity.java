package net.minimgun.quizframework.models.quiz;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String author;
    @JsonIgnore
    private String editPassword;

    public QuizEntity(String id, List<QuestionImpl> questions, QuizSettingsImpl quizSettings, String quizName,
            String author, String editPassword) {
        super();
        this.id = id;
        this.questions = questions;
        this.quizSettings = quizSettings;
        this.quizName = quizName;
        this.author = author;
        this.editPassword = editPassword;
    }

    public QuizEntity(String id, QuizSettingsImpl quizSettings, String quizName, String author, String editPassword) {
        super();
        this.id = id;
        this.quizSettings = quizSettings;
        this.quizName = quizName;
        this.author = author;
        this.editPassword = editPassword;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getEditPassword() {
        return editPassword;
    }

    public void setEditPassword(String editPassword) {
        this.editPassword = editPassword;
    }

}
