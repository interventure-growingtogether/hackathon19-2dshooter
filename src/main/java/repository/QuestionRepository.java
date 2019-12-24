package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuestionRepository {

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

    public static QuestionRepository buildRepository() {
        //TODO fill QuestionRepository with questions
        return new QuestionRepository();
    }

    public static void main(String[] args) {

        //initialize();

    }

    private static void initialize() throws FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
    }
}
