package io.icons.repository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuestionRepository {

    public static final String QUESTIONS_JSON = "./src/main/resources/questions.json";

    private List<Question> questions = new ArrayList<>();
    private List<Question> viewedQuestions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Question> getViewedQuestions() {
        return viewedQuestions;
    }

    public Question getRandomQuestion() {
        Random random = new Random();
        Question question = questions.get(random.nextInt(questions.size()));

        while (viewedQuestions.contains(question)) {
            question = questions.get(random.nextInt(questions.size()));
        }

        viewedQuestions.add(question);
        return question;
    }

    private QuestionRepository init() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(QUESTIONS_JSON);
        try {
            JsonParser jsonParser = new JsonFactory().createParser(file);
            Question[] questionsArray = objectMapper.readValue(jsonParser, Question[].class);
            questions.addAll(Arrays.asList(questionsArray));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public static QuestionRepository buildRepository() {
        return (new QuestionRepository()).init();
    }
}
