package io.icons.repository;

import java.util.List;

public class Question {

    private int id;
    private String name;
    private String hint;
    private int correctAnswer;
    private List<Answer> answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isAnswerCorrect(int answerId) {
        return correctAnswer == answerId;
    }

    public Answer getCorrectAnswer() {
        return answers.stream()
                .filter(answer -> answer.getId() == correctAnswer)
                .findFirst().get();
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hint='" + hint + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", answers=" + answers +
                '}';
    }
}
