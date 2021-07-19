package net.minimgun.quizframework.models.quiz.interfaces;

import net.minimgun.quizframework.enums.TaskType;

public interface Task {

    TaskType getTaskType();

    String getContent();
}
