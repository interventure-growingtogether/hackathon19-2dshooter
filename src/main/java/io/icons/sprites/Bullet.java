/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites;

import io.icons.gameengine.Sprite;
import io.icons.sprites.playeranimations.Axe;

/**
 *
 * @author Marko
 */
public class Bullet extends Sprite {

    private double speed = 10f;
    private Axe a;

    public Bullet() {
        a = new Axe();
        a.setRotate(90);
        a.setTranslateX(-a.getBoundsInLocal().getWidth() / 2);
        a.setTranslateY(-a.getBoundsInLocal().getHeight() / 2);

        getChildren().add(a);
    }

    public void setDirectionLeft() {
        a.setRotate(a.getRotate() + 180);//sekira obrne smer glave
        speed = -Math.abs(speed);
    }

    @Override
    public void update() {
        setTranslateX(getTranslateX() + speed);
    }

}
