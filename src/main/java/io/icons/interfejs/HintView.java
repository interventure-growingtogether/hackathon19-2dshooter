/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.interfejs;

import io.icons.PlatformerGame;
import io.icons.repository.Question;
import io.icons.repository.QuestionRepository;
import io.icons.util.StringUtils;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Marko
 */
public class HintView extends DialogView {

    private static final LinkedList<String> HINTS;

    static {
        QuestionRepository repository = QuestionRepository.buildRepository();
        HINTS = repository.getQuestions().stream()
                .map(Question::getHint)
                .collect(Collectors.toCollection(LinkedList::new));
        Collections.shuffle(HINTS);
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
        this.hintText = StringUtils.asFixedLengthLines(hintText);

        pg.setPaused(true);
        initHintTextField();

        this.setOnMouseClicked(e -> closeDialog());
        POOL.schedule(this::closeDialog, 5, TimeUnit.SECONDS);
    }

    private void initHintTextField() {
        this.hintTextField = new Text();
        this.hintTextField.setText(hintText);

        this.hintTextField.setFont(Font.font("Verdana", 36));
        this.hintTextField.setFill(Color.WHITE);

        this.hintTextField.setStroke(Color.YELLOWGREEN);
        this.hintTextField.setStrokeWidth(1);

        this.hintTextField.setTranslateX(-this.hintTextField.getBoundsInLocal().getWidth() / 2);
        this.hintTextField.setTranslateY(-this.hintTextField.getBoundsInLocal().getHeight() / 2);
        getChildren().add(this.hintTextField);
    }

    @Override
    protected void additionalCloseOperations() {
        POOL.schedule(() -> pg.setPaused(false), 200, TimeUnit.MILLISECONDS);
    }
}
