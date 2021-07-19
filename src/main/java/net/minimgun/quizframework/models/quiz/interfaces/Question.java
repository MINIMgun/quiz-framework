package net.minimgun.quizframework.models.quiz.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;

import net.minimgun.quizframework.models.quiz.entities.tasks.TaskImpl;

public interface Question {

    TaskImpl getTask();

    AnswerOption getAnswerOption();

    @Transient
    default List<User> evaluate(List<UserInput> userInputs) {
        List<User> users = new ArrayList<>();
        for (UserInput userInput : userInputs) {
            if (getAnswerOption().isCorrectValue(userInput.getValue())) {
                users.add(userInput.getUser());
            }
        }
        return users;
    }

    int getQuestionIndex();

    int getTime();
}
