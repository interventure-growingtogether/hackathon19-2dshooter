/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import gameengine.Sprite;
import sprites.playeranimations.Castle;

/**
 *
 * @author Marko
 */
public class EndSprite extends Sprite {

    public EndSprite() {
        Castle c = new Castle();
        c.setTranslateX(-c.getBoundsInLocal().getWidth() / 2);
        c.setTranslateY(-c.getBoundsInLocal().getHeight() / 2);
        
        c.setScaleX(0.5);
        c.setScaleY(0.5);

        getChildren().add(c);
    }

}
