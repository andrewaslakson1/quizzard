package com.revature.quizzard.question;

import com.revature.quizzard.card.dtos.requests.NewCardRequest;
import com.revature.quizzard.common.dtos.ResourceCreationResponse;
import com.revature.quizzard.common.exceptions.InvalidRequestException;
import com.revature.quizzard.common.util.web.Authenticated;
import com.revature.quizzard.question.dtos.requests.NewQuestionRequest;
import com.revature.quizzard.question.dtos.responses.QuestionResponse;
import com.revature.quizzard.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestControllerAdvice
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Authenticated
    @GetMapping(produces = "application/json")
    public List<QuestionResponse> getAllQuestions() {
        return questionService.findQuestions();
    }

    @Authenticated
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public ResourceCreationResponse createNewQuestion(@RequestBody NewQuestionRequest newQuestionRequest, HttpSession session) {
        newQuestionRequest.setCreator((AppUser) session.getAttribute("authUser"));
        return questionService.createNewQuestion(newQuestionRequest);
    }
}
