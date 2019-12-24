/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.levels;

import io.icons.gameengine.Sprite;
import java.util.Random;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import static io.icons.levels.LevelElements.COIN;
import static io.icons.levels.LevelElements.COIN_MASK;
import io.icons.sprites.Coin;

/**
 *
 * @author Marko
 */
public class CoinBuilder implements LevelElementBuilder {

    private static final Random RND = new Random();

    @Override
    public Sprite buildElement(byte element, int x, int width) {

        if ((element & COIN_MASK) == COIN) {
            Sprite coin = new Coin();
            coin.setTranslateX(x - width / (RND.nextInt(3) + 1));
            coin.setTranslateY(-20);

            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), coin);
            tt.setToY(-40);
            //tt.setCycleCount(TranslateTransition.INDEFINITE);
            //tt.setAutoReverse(true);
            tt.setInterpolator(Interpolator.LINEAR);

            tt.setOnFinished(new EventHandler<ActionEvent>() {
                int animationRate = 40;

                @Override
                public void handle(ActionEvent ev) {
                    tt.setToY(coin.getTranslateY() + animationRate);
                    animationRate = -animationRate;
                    tt.stop();
                    tt.play();
                }
            });

            //tt.play();
            return coin;
        }
        return null;

    }

}
