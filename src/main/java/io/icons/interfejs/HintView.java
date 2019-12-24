/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.interfejs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.icons.PlatformerGame;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import io.icons.repository.QuestionRepository;

/**
 * @author Marko
 */
public class HintView extends DialogView {

    private static final Queue<String> HINTS;

    static {
        QuestionRepository repository = QuestionRepository.buildRepository();
        HINTS = repository.getQuestions().stream()
            .map(q -> q.getName() + "\n" + q.getCorrectAnswer().getName())
            .collect(Collectors.toCollection(LinkedList::new));
    }

    Text hintTextField;
    private String hintText;

    public static void showIn(PlatformerGame game) {
        if (!HINTS.isEmpty()) {
            new HintView(game, HINTS.poll());
        }
    }

    public HintView(final PlatformerGame pg, String hintText) {
        super(pg);
        this.hintText = hintText;

        pg.setPaused(true);
        initHintTextField();

        this.setOnMouseClicked(e -> closeDialog());
        POOL.schedule(this::closeDialog, 2500, TimeUnit.MILLISECONDS);
    }

    private void initHintTextField() {
        this.hintTextField = new Text();
        this.hintTextField.setText(hintText);

        this.hintTextField.setFont(Font.font("Verdana", 36));
        this.hintTextField.setFill(Color.WHITE);

        this.hintTextField.setStroke(Color.BLUE);
        this.hintTextField.setStrokeWidth(1);

        this.hintTextField.setTranslateX(-this.hintTextField.getBoundsInLocal().getWidth() / 2);
        this.hintTextField.setTranslateY(-this.hintTextField.getBoundsInLocal().getHeight() / 2);
        getChildren().add(this.hintTextField);
    }

    @Override protected void additionalCloseOperations() {
        POOL.schedule(() -> pg.setPaused(false), 200, TimeUnit.MILLISECONDS);
    }
}
