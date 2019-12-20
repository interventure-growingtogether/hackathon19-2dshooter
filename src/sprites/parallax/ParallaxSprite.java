/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites.parallax;

import gameengine.Sprite;

/**
 *
 * @author Marko
 */
public class ParallaxSprite extends Sprite {

    private double previosPlayerX = Double.NaN, currentPlayerX = Double.NaN;
    protected double speedX;

    public void setCurrentPlayerX(double currentPlayerX) {
        previosPlayerX = this.currentPlayerX;
        this.currentPlayerX = currentPlayerX;
    }

    @Override
    public void update() {
        if (Double.isNaN(previosPlayerX) || Double.isNaN(currentPlayerX)) {
            return;
        }

        if (currentPlayerX < previosPlayerX) {
            //Pomerio se levo
            speedX = Math.abs(speedX);
        } else if (currentPlayerX > previosPlayerX) {
            //Pomerio se desno
            speedX = -Math.abs(speedX);
        }

        if (previosPlayerX != currentPlayerX) {
            this.setTranslateX(this.getTranslateX() + speedX);
        }

    }

}
