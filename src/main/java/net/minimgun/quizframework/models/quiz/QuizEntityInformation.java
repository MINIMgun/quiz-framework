package net.minimgun.quizframework.models.quiz;

import net.minimgun.quizframework.models.quiz.interfaces.QuizInformation;

public class QuizEntityInformation implements QuizInformation {

    private String QuizEntityId;
    private int questionAmount;
    private String quizName;
    private String author;

    public QuizEntityInformation(String quizEntityId, int questionAmount, String quizName, String author) {
        super();
        QuizEntityId = quizEntityId;
        this.questionAmount = questionAmount;
        this.quizName = quizName;
        this.author = author;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getQuizName() {
        return quizName;
    }

    public String getQuizEntityId() {
        return QuizEntityId;
    }

    public void setQuizEntityId(String quizEntityId) {
        QuizEntityId = quizEntityId;
    }

    public int getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(int questionAmount) {
        this.questionAmount = questionAmount;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
