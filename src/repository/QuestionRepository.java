package repository;

import java.util.List;
import java.util.Random;

public class QuestionRepository {

    private List<Question> questions;
    private List<Question> viewedQuestions;

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
}
