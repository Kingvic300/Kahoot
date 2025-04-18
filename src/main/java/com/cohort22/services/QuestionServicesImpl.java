package com.cohort22.services;

import com.cohort22.dtos.request.QuestionRequest;
import com.cohort22.dtos.response.QuestionResponse;
import com.cohort22.data.models.Question;
import com.cohort22.data.repositories.QuestionRepository;
import com.cohort22.exceptions.QuestionNotFoundException;
import com.cohort22.mappers.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionServicesImpl implements QuestionServices {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<QuestionResponse> getAllQuestions() {
      List<Question> questions = questionRepository.findAll();
      if (questions.isEmpty()) {
          throw new QuestionNotFoundException("Question not found");
      }
      List<QuestionResponse> questionResponses = new ArrayList<>();
      for (Question question : questions) {
          questionResponses.add(QuestionMapper.mapToQuestionResponse("Question found", question));
      }
      return questionResponses;
    }

    @Override
    public QuestionResponse getQuestionByName(QuestionRequest questionRequest) {
        Optional<Question> question = questionRepository.findByQuestion(questionRequest.getQuestion());
        if (question.isPresent()) {
            return QuestionMapper.mapToQuestionResponse("Question found", question.get());
        }
        throw new QuestionNotFoundException("Question not found");
    }

    @Override
    public QuestionResponse createQuestion(QuestionRequest questions) {
        Question question = QuestionMapper.mapToQuestion(questions);
        question.setId(UUID.randomUUID().toString());
        questionRepository.save(question);

        return QuestionMapper.mapToQuestionResponse("Question Created", question);

    }

    @Override
    public QuestionResponse deleteQuestion(QuestionRequest questions) {
        Optional<Question> question = questionRepository.findByQuestion(questions.getQuestion());
        if (question.isPresent()) {
            questionRepository.delete(question.get());
            return QuestionMapper.mapToQuestionResponse("Question deleted",question.get());
        }
        throw new QuestionNotFoundException("Question Not Found");
    }
}
