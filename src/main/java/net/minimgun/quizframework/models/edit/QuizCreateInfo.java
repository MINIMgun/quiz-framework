package net.minimgun.quizframework.models.edit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import net.minimgun.quizframework.models.quiz.entities.QuizSettingsImpl;

public class QuizCreateInfo {

    @NotNull
    @NotBlank
    private String author;
    @NotNull
    @NotBlank
    private String quizName;
    @NotNull
    @NotBlank
    private String editPassword;
    private QuizSettingsImpl quizSettings;

    public QuizCreateInfo(@NotNull String author, @NotNull String quizName, @NotNull String editPassword,
            QuizSettingsImpl quizSettings) {
        super();
        this.author = author;
        this.quizName = quizName;
        this.editPassword = editPassword;
        this.quizSettings = quizSettings;
    }

    public QuizCreateInfo(@NotNull String author, @NotNull String quizName, @NotNull String editPassword) {
        this(author, quizName, editPassword, null);
    }

    public QuizCreateInfo() {
        super();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getEditPassword() {
        return editPassword;
    }

    public void setEditPassword(String editPassword) {
        this.editPassword = editPassword;
    }

    public QuizSettingsImpl getQuizSettings() {
        return quizSettings;
    }

    public void setQuizSettings(QuizSettingsImpl quizSettings) {
        this.quizSettings = quizSettings;
    }

    @Override
    public String toString() {
        return "QuizCreateInfo [author=" + author + ", quizName=" + quizName + ", editPassword=" + editPassword
                + ", quizSettings=" + quizSettings + "]";
    }
}
