package net.minimgun.quizframework.models.quiz.interfaces;

import net.minimgun.quizframework.models.quiz.entities.answeroptions.AnswerOption;
import net.minimgun.quizframework.models.quiz.entities.tasks.TaskImpl;

public interface Question {

    TaskImpl getTask();

    AnswerOption getAnswerOption();

    int getQuestionIndex();

    int getTime();
}
