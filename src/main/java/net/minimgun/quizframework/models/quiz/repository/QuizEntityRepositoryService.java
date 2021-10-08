package net.minimgun.quizframework.models.quiz.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import net.minimgun.quizframework.exceptions.EntityNotFoundException;
import net.minimgun.quizframework.models.edit.EditPassword;
import net.minimgun.quizframework.models.edit.QuizCreateInfo;
import net.minimgun.quizframework.models.quiz.QuizEntity;
import net.minimgun.quizframework.models.quiz.QuizEntityInformation;
import net.minimgun.quizframework.util.EditTokenMap;
import net.minimgun.quizframework.util.TokenGenerator;

@Service
public class QuizEntityRepositoryService {

    @Autowired
    private QuizEntityRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private final EditTokenMap tokenMap = new EditTokenMap();

    /**
     * Returns a list of all QuizEntityInformation's with the given author.
     * 
     * @param author The author the QuizEntity should have, if author is null all
     *               QuizEntity's are returned
     * @return a list of all QuizEntityInformation's with the given author, if
     *         author is null all QuizEntityInformation's are returned
     * @since 0.0.1
     */
    public List<QuizEntityInformation> getAll(String author) {
        List<QuizEntity> quizEntities = author == null ? repository.findAll() : repository.findAllByAuthor(author);
        List<QuizEntityInformation> list = new ArrayList<>(quizEntities.size());
        for (QuizEntity quizEntity : quizEntities) {
            list.add(new QuizEntityInformation(quizEntity.getId(), quizEntity.getQuestions().size(),
                    quizEntity.getQuizName(), quizEntity.getAuthor()));
        }
        return list;
    }

    /**
     * Returns a list of all QuizEntityInformation's in the database.
     * 
     * @return a list of all QuizEntityInformation's in the database
     * @since 0.0.1
     */
    public List<QuizEntityInformation> getAll() {
        return getAll(null);
    }

    /**
     * This method is used to provide a QuizEntity for users that want to edit it.
     * For that they need to authorize with the editPassword of that Quiz. The
     * authorization is checked and a cookie is returned with a editToken for this
     * QuizEntity.
     * 
     * @param password a EditPassword object containing the password provided by the
     *                 user
     * @param response the {@link HttpServletResponse} to set the {@link Cookie}
     * @param id       the id of the QuizEntity to edit
     * @param request  the request to check if the user doesn’t yet have a editToken
     * @return the QuizEntity to edit
     * @since 0.0.1
     */
    public QuizEntity editQuizEntity(EditPassword password, HttpServletResponse response, String id,
            HttpServletRequest request) {
        QuizEntity quizEntity = getQuiz(id);
        if (hasValidEditToken(request, id)) {
            return quizEntity;
        }
        if (password != null && passwordEncoder.matches(password.getEditPassword(), quizEntity.getEditPassword())) {
            return getQuizEntityWithToken(response, id, quizEntity);
        }
        throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
    }

    /**
     * This method returns the {@link QuizEntity} with the specified id.
     * 
     * @param id The id of the {@link QuizEntity}.
     * @return the {@link QuizEntity} with the specified id.
     * @since 0.1.2
     */
    public QuizEntity getQuiz(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(QuizEntity.class, id));
    }

    /**
     * This method updates the QuizEntity with the provided id, with the given
     * QuizEntity if the user has a valid editToken.
     * 
     * @param quizEntity the updated QuizEntity
     * @param id         the id of the QuizEntity to update
     * @param request    the request to check that the user has a valid requestToken
     * @return the updated QuizEntity saved to the database
     * @since 0.0.1
     */
    public QuizEntity updateQuizEntity(QuizEntity quizEntity, String id, HttpServletRequest request) {
        QuizEntity old = getQuiz(id);
        quizEntity.setId(id);
        quizEntity.setEditPassword(old.getEditPassword());
        if (hasValidEditToken(request, id)) {
            return repository.save(quizEntity);
        }
        throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
    }

    /**
     * This method creates a new QuizEntity with the specified QuizCreateInfo. It
     * also sets a Cookie with the editToken.
     * 
     * @param createInfo the QuizCreateInfo that contains the information to create
     *                   a new QuizEntity
     * @param response   the {@link HttpServletResponse} to set the {@link Cookie}
     *                   that contains the editToken
     * @return a new QuizEntity with the given createInfo
     * @since 0.0.1
     */
    public QuizEntity createQuiz(QuizCreateInfo createInfo, HttpServletResponse response) {
        String id = UUID.randomUUID().toString();
        QuizEntity quizEntity = new QuizEntity(id, createInfo.getQuizSettings(), createInfo.getQuizName(),
                createInfo.getAuthor(), passwordEncoder.encode(createInfo.getEditPassword()));
        return repository.save(getQuizEntityWithToken(response, id, quizEntity));
    }

    private QuizEntity getQuizEntityWithToken(HttpServletResponse response, String id, QuizEntity quizEntity) {
        String editToken = TokenGenerator.generateToken(id);
        Cookie cookie = new Cookie("edit_token_" + id, editToken);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setMaxAge(-1);
        cookie.setSecure(true);
        response.addCookie(cookie);
        tokenMap.addEditToken(id, editToken);
        return quizEntity;
    }

    private boolean hasValidEditToken(HttpServletRequest request, String id) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String editToken = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("edit_token_" + id)) {
                    editToken = cookie.getValue();
                    break;
                }
            }
            if (editToken != null) {
                if (tokenMap.tokenIsValid(id, editToken)) {
                    return true;
                }
            }
        }
        return false;
    }
}
