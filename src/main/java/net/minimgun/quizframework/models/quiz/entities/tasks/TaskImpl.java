package net.minimgun.quizframework.models.quiz.entities.tasks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import net.minimgun.quizframework.enums.TaskType;
import net.minimgun.quizframework.models.quiz.interfaces.Task;

@Entity
public class TaskImpl implements Task {

    private @Id @GeneratedValue long id;
    private TaskType taskType;
    private String content;

    public TaskImpl(String content, TaskType taskType) {
        super();
        this.content = content;
        this.taskType = taskType;
    }

    public TaskImpl() {
        super();
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String getContent() {
        return content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TaskImpl [id=" + id + ", taskType=" + taskType + ", content=" + content + "]";
    }

}
