package net.minimgun.quizframework.models.info.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.minimgun.quizframework.models.CurrentEntityInferface;
import net.minimgun.quizframework.models.info.entity.Info;

public interface InfoRepository extends JpaRepository<Info, Long>, CurrentEntityInferface<Info> {

    List<Info> findAllByOrderByIdDesc();

    @Override
    default List<Info> getOrderedByCurrent() {
        return findAllByOrderByIdDesc();
    }
}
