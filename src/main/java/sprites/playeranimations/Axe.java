/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites.playeranimations;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 *
 * @author Marko
 */
public class Axe extends ImageView {

    public Axe() {
        super(SpriteSheet.getImage());

        setViewport(new Rectangle2D(487, 50, 15, 35));
        setScaleX(2);
        setScaleY(2);
    }

}
