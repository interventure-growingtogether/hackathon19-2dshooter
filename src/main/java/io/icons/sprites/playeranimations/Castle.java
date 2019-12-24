/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites.playeranimations;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 *
 * @author Marko
 */
public class Castle extends ImageView {

    public Castle() {
        super(SpriteSheet.getCastleImage());

        setViewport(new Rectangle2D(0, 0, 1024, 627));
        setScaleX(2);
        setScaleY(2);
    }
}
