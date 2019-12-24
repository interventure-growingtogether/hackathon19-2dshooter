/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfejs;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sprites.playeranimations.SpriteSheet;

/**
 *
 * @author Marko
 */
public class MenuView extends Group {

    public interface MenuListener {

        public void onPlay();

        public void onExit();
    }

    ImageView iv = new ImageView(SpriteSheet.getBgImage());
    Text playAgain, exit, message;
    MenuListener listener;

    public MenuView(MenuListener listener) {
        this.listener = listener;

        iv.setViewport(new Rectangle2D(0, 0, 700, 366));
        iv.setTranslateX(-700 / 2);
        iv.setTranslateY(-366 / 2);
        iv.setOpacity(0.99999);

        getChildren().add(iv);
        setTranslateX(0.5);
        setTranslateY(0.5);

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

        playAgain.setOnMouseClicked(e -> {
            listener.onPlay();
        });
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

        exit.setOnMouseClicked(e -> {
            listener.onExit();
        });
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
