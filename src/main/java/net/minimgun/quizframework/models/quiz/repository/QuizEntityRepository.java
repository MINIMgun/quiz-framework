package net.minimgun.quizframework.models.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.minimgun.quizframework.models.quiz.QuizEntity;

public interface QuizEntityRepository extends JpaRepository<QuizEntity, String> {

    List<QuizEntity> findAllByAuthor(String author);
}
