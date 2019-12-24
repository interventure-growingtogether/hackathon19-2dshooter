package io.icons.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static void main(String[] args) {
        buildRepository();

    }
    public static QuestionRepository buildRepository() {
        //TODO fill QuestionRepository with questions
        File directory = new File("./");
        File file = new File("questions.json");

        System.out.println(directory.getAbsolutePath());
        return new QuestionRepository();
    }

}
