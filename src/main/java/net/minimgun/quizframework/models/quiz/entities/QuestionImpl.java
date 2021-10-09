package net.minimgun.quizframework.models.quiz.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import net.minimgun.quizframework.models.quiz.entities.answeroptions.AnswerOption;
import net.minimgun.quizframework.models.quiz.entities.tasks.TaskImpl;
import net.minimgun.quizframework.models.quiz.interfaces.Question;

@Entity
public class QuestionImpl implements Question {

    private @Id @GeneratedValue long id;
    @OneToOne(cascade = { CascadeType.ALL })
    private TaskImpl task;
    @OneToOne(cascade = { CascadeType.ALL })
    private AnswerOption answerOption;
    private int questionIndex;
    private int time;

    public QuestionImpl(TaskImpl task, AnswerOption answerOption, int questionIndex, int time) {
        super();
        this.task = task;
        this.answerOption = answerOption;
        this.questionIndex = questionIndex;
        this.time = time;
    }

    public QuestionImpl() {
        super();
    }

    public void setTask(TaskImpl task) {
        this.task = task;
    }

    public void setAnswerOption(AnswerOption answerOption) {
        this.answerOption = answerOption;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public TaskImpl getTask() {
        return task;
    }

    @Override
    public AnswerOption getAnswerOption() {
        return answerOption;
    }

    @Override
    public int getQuestionIndex() {
        return questionIndex;
    }

    @Override
    public int getTime() {
        return time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QuestionImpl [id=" + id + ", task=" + task + ", answerOption=" + answerOption + ", questionIndex="
                + questionIndex + ", time=" + time + "]";
    }

}
