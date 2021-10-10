package net.minimgun.quizframework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minimgun.quizframework.exceptions.EntityNotFoundException;
import net.minimgun.quizframework.models.quiz.QuizEntity;

public class EditTokenMap extends HashMap<String, List<String>> {

    private static final long serialVersionUID = -7717476083499322886L;

    public void addEditToken(String id, String token) {
        List<String> tokens = null;
        if (containsKey(id)) {
            tokens = get(id);
        } else {
            tokens = new ArrayList<>();
        }
        tokens.add(token);
        put(id, tokens);
    }

    public List<String> getEditTokens(String id) {
        List<String> tokens = get(id);
        if (tokens != null) {
            return tokens;
        }
        throw new EntityNotFoundException(QuizEntity.class, id);
    }

    public boolean tokenIsValid(String id, String token) {
        List<String> tokens = get(id);
        if (tokens != null) {
            return tokens.contains(token);
        }
        return false;
    }
}
