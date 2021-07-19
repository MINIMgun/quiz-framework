package net.minimgun.quizframework.models;

import java.util.List;

import net.minimgun.quizframework.exceptions.NoEntityInDatabaseListException;

/**
 * Class that provides a static method to get the current entity from a
 * repository implementing {@link CurrentEntityInferface}.
 * 
 * @author MINIMgun
 */
public class CurrentEntityHelper {

    /**
     * This static method is used to return the current entity from a class
     * implementing {@link CurrentEntityInferface} or if the
     * {@link CurrentEntityInferface#getOrderedByCurrent()} list is empty a
     * {@link NoEntityInDatabaseListException}
     * 
     * @param <Entity>         The type of the Entity
     * @param entityRepository A class implementing {@link CurrentEntityInferface}.
     * @return The current entity.
     * @throws NoEntityInDatabaseListException if the
     *                                         {@link CurrentEntityInferface#getOrderedByCurrent()}
     *                                         list is empty.
     */
    public static <Entity> Entity getCurrent(CurrentEntityInferface<Entity> entityRepository)
            throws NoEntityInDatabaseListException {
        List<Entity> list = entityRepository.getOrderedByCurrent();
        if (list.isEmpty()) {
            throw new NoEntityInDatabaseListException();
        }
        return list.get(0);
    }
}
