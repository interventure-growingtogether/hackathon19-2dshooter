/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.interfejs;

import io.icons.PlatformerGame;
import io.icons.TextOptionClickListener;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Marko
 */
public class MenuView extends DialogView {
    
    Text playAgain, exit, message;
    TextOptionClickListener listener;

    public MenuView(final PlatformerGame pg) {
        super(pg);
        this.listener = pg.getMain();

        initPlayAgain();
        initExit();
        initMessage();
    }

    private void initPlayAgain() {
        playAgain = new Text();
        playAgain.setText("Play platformer");
        playAgain.setFont(Font.font("Verdana", 50));
        playAgain.setFill(Color.WHITE);

        playAgain.setStroke(Color.BLUE);
        playAgain.setStrokeWidth(1);

        playAgain.setTranslateX(-playAgain.getBoundsInLocal().getWidth() / 2);
        playAgain.setTranslateY(-playAgain.getBoundsInLocal().getHeight() / 2);
        getChildren().add(playAgain);

        playAgain.setOnMouseClicked(e -> listener.onSelection(playAgain));
    }

    private void initExit() {
        exit = new Text();
        exit.setText("Exit platformer");
        exit.setFont(Font.font("Verdana", 50));
        exit.setFill(Color.WHITE);

        exit.setStroke(Color.BLUE);
        exit.setStrokeWidth(1);

        exit.setTranslateX(-exit.getBoundsInLocal().getWidth() / 2);
        exit.setTranslateY(exit.getBoundsInLocal().getHeight());
        getChildren().add(exit);

        exit.setOnMouseClicked(e -> listener.onSelection(exit));
    }

    private void initMessage() {
        message = new Text();
        message.setText("HAVE FUN");
        message.setFont(Font.font("Verdana", 50));
        message.setFill(Color.YELLOW);

        message.setStroke(Color.BLUE);
        message.setStrokeWidth(1);

        message.setTranslateX(-message.getBoundsInLocal().getWidth() / 2);
        message.setTranslateY(message.getBoundsInLocal().getHeight() * 2.5);
        getChildren().add(message);

    }

    public void setMessage(String text) {
        message.setText(text);
        message.setTranslateX(-message.getBoundsInLocal().getWidth() / 2);
    }

}
