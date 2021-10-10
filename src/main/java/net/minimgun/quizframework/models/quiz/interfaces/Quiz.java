package net.minimgun.quizframework.models.quiz.interfaces;

import java.util.List;

import net.minimgun.quizframework.models.quiz.entities.QuestionImpl;
import net.minimgun.quizframework.models.quiz.entities.QuizSettingsImpl;

public interface Quiz extends QuizInformation{

    List<QuestionImpl> getQuestions();

    QuizSettingsImpl getQuizSettings();

    String getEditPassword();
}
