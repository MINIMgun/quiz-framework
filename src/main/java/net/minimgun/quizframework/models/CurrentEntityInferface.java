package net.minimgun.quizframework.models;

import java.util.List;

/**
 * This interface provides a default method used by the
 * {@link CurrentEntityHelper} to return the current entity.
 * 
 * @author MINIMgun
 * @param <Entity> The entity to return.
 */
public interface CurrentEntityInferface<Entity> {

    /**
     * Returnes a {@link List} of entitys ordered by the most current to least
     * current.
     * 
     * @return A {@link List} of entitys ordered by the most current to least
     *         current.
     */
    List<Entity> getOrderedByCurrent();
}
