package net.minimgun.quizframework.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This {@link RuntimeException} is thrown when no entity with the given id
 * could be found and intercepted by springs {@link ExceptionHandler}.
 * 
 * @author MINIMgun
 */
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1127896790101292623L;

    /**
     * 
     * Creates a new {@link EntityNotFoundException}.
     * 
     * @param entity        The class of the entity that couldn't be found.
     * @param idDescription A discription of the used id
     * @param id            The id.
     */
    public EntityNotFoundException(Class<?> entity, String idDescription, Object id) {
        super("Could not find a " + entity.getSimpleName() + " in the database with the " + idDescription + ": " + id);
    }

    /**
     * 
     * Creates a new {@link EntityNotFoundException}.
     * 
     * @param entity The class of the entity that couldn't be found.
     * @param id     The id.
     */
    public EntityNotFoundException(Class<?> entity, Long id) {
        this(entity, "id", id);
    }

    /**
     * 
     * Creates a new {@link EntityNotFoundException}.
     * 
     * @param entity The class of the entity that couldn't be found.
     * @param id     The id.
     */
    public EntityNotFoundException(Class<?> entity, String id) {
        this(entity, "id", id);
    }
}
