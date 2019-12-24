/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.interfejs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.icons.PlatformerGame;
import io.icons.repository.Answer;
import io.icons.repository.Question;
import io.icons.util.StringUtils;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Marko
 */
public class QuestionView extends DialogView {

    private static final int POINT_BONUS = 100;
    private final List<Text> textFields = new ArrayList<>();
    private final Runnable onClose;
    private final Question question;
    private volatile boolean answersLocked;

    public static void showIn(PlatformerGame game, Question question, Runnable onClose) {
        new QuestionView(game, question, onClose);
    }

    public QuestionView(final PlatformerGame pg, Question question, Runnable onClose) {
        super(pg);
        this.question = question;
        this.onClose = onClose;

        Text questionText = createTextField(question.getName(), Color.BLUEVIOLET, Color.YELLOW);
        textFields.add(questionText);
        getChildren().add(questionText);

        List<Answer> answers = new ArrayList<>(question.getAnswers());
        Collections.shuffle(answers);
        answers.stream().map(Answer::getName).forEach(this::initTextField);

        POOL.schedule(this::showCorrectAnswerAndClose, 10, TimeUnit.SECONDS);
    }

    private void initTextField(String textContent) {
        Text textField = createTextField(textContent, Color.WHITE, Color.BLUE);
        getChildren().add(textField);

        textField.setOnMouseEntered(e -> textField.setUnderline(true));
        textField.setOnMouseExited(e -> textField.setUnderline(false));
        textField.setOnMouseClicked(e -> {
            if (!answersLocked) {
                answersLocked = true;
                handleAnswer(textContent);
            }
        });

        textFields.add(textField);
    }

    private void handleAnswer(final String textContent) {
        if (correctAnswer().equals(textContent)) {
            pg.getGameListener().incrementPointsBy(POINT_BONUS);
            String correctAnswerMessage = "Correct! " + POINT_BONUS + " earned";
            getChildren().add(createTextField(correctAnswerMessage, Color.GREEN, Color.WHITE));
        } else {
            String incorrectAnswerMessage = "Incorrect! The answer was\n" + correctAnswer();
            getChildren().add(createTextField(incorrectAnswerMessage, Color.RED, Color.WHITE));
        }

        POOL.schedule(this::closeDialog, 2, TimeUnit.SECONDS);
    }

    private String correctAnswer() {
        return question.getCorrectAnswer().getName();
    }

    private Text createTextField(final String textContent, Color fill, Color stroke) {

        Text textField = new Text();
        textField.setText(StringUtils.asFixedLengthLines(textContent));

        textField.setFont(Font.font("Verdana", 36));
        textField.setFill(fill);

        textField.setStroke(stroke);
        textField.setStrokeWidth(1);

        double height = textField.getBoundsInLocal().getHeight();
        double offset = textFields.isEmpty()
            ? 5 + height / 2 - (getLayoutBounds().getHeight() / 2)
            : offsetJustBelowLastTextField();
        textField.setTranslateY(offset);
        textField.setTranslateX(-textField.getBoundsInLocal().getWidth() / 2);
        return textField;
    }

    private double offsetJustBelowLastTextField() {
        return lastTextField().getLocalToParentTransform().getTy() + lastTextField().getBoundsInLocal().getHeight() + 10;
    }

    private Text lastTextField() {
        return textFields.get(textFields.size() - 1);
    }

    @Override
    protected void additionalCloseOperations() {
        onClose.run();
    }

    private void showCorrectAnswerAndClose() {
        answersLocked = true;
        if (!closed) {
            Platform.runLater(() -> {
                String timeOutMessage = "Out of time! The answer was\n" + correctAnswer();
                getChildren().add(createTextField(timeOutMessage, Color.RED, Color.WHITE));
            });
        }
        POOL.schedule(this::closeDialog, 2, TimeUnit.SECONDS);
    }
}
