package net.minimgun.quizframework.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import net.minimgun.quizframework.models.edit.EditPassword;
import net.minimgun.quizframework.models.edit.QuizCreateInfo;
import net.minimgun.quizframework.models.quiz.QuizEntity;
import net.minimgun.quizframework.models.quiz.QuizEntityInformation;
import net.minimgun.quizframework.models.quiz.repository.QuizEntityRepositoryService;

@RestController
@RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
public class EditController {

    @Autowired
    private QuizEntityRepositoryService service;

    @Operation(summary = "Create a new QuizEntity", description = "This method creates a new QuizEntity with the specified QuizCreateInfo. It also sets a Cookie with the editToken.")
    @PostMapping("/createNewQuiz")
    ResponseEntity<QuizEntity> createNewQuiz(@RequestBody @NotBlank @NotNull QuizCreateInfo createInfo,
            HttpServletResponse response) {
        return new ResponseEntity<QuizEntity>(service.createQuiz(createInfo, response), HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve a QuizEntity to edit", description = "This method is used to provide a QuizEntity for users that want to edit it. For that they need to authorize with the editPassword of that Quiz. The authorization is checked and a cookie is returned with a editToken for this QuizEntity.")
    @PostMapping("/getQuiz/id={id}")
    ResponseEntity<QuizEntity> editQuiz(@RequestBody @NotNull @NotBlank EditPassword password,
            HttpServletResponse response, @PathVariable @NotNull @NotBlank String id, HttpServletRequest request) {
        return new ResponseEntity<QuizEntity>(service.editQuizEntity(password, response, id, request),
                HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Update a QuizEntity", description = "This method updates the QuizEntity with the provided id, with the given QuizEntity if the user has a valid editToken.")
    @PostMapping("/saveQuiz/id={id}")
    ResponseEntity<QuizEntity> saveQuiz(@RequestBody @NotNull @NotBlank QuizEntity quizEntity,
            @PathVariable @NotNull @NotBlank String id, HttpServletRequest request) {
        return new ResponseEntity<QuizEntity>(service.updateQuizEntity(quizEntity, id, request), HttpStatus.OK);
    }

    @Operation(summary = "Get a list of all QuizEntityInformation", description = "Returns a list of all QuizEntityInformation's with the given author.")
    @GetMapping("/getAllQuizInformation")
    ResponseEntity<List<QuizEntityInformation>> getAll(@RequestParam(name = "author", required = false) String author) {
        return new ResponseEntity<List<QuizEntityInformation>>(service.getAll(author), HttpStatus.OK);
    }
}
