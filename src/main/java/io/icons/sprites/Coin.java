/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites;

import io.icons.gameengine.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Marko
 */
public class Coin extends Sprite {

    public interface CoinPickupListener {

        public void onCoinPickup(int amount);
    }
    private CoinPickupListener listener;

    public Coin() {
        Circle c = new Circle(15);
        c.setFill(Color.ORANGE);

        /*TranslateTransition tt = new TranslateTransition(Duration.millis(500), this);
        tt.setToY(this.getTranslateY() + 30);
        tt.play();*/
        getChildren().add(c);
    }

    public Coin(CoinPickupListener listener) {
        this();
        this.listener = listener;
    }

    @Override
    public boolean collide(Sprite sprite) {
        if (sprite instanceof Player) {
            if (this.getBoundsInParent().intersects(sprite.getBoundsInParent())) {
                this.setSpriteDead();

                if (listener != null) {
                    listener.onCoinPickup(1);
                }
                return true;
            }
        }
        return false;
    }

    public void setListener(CoinPickupListener listener) {
        this.listener = listener;
    }

}
