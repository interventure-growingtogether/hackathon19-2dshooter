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
public class Live extends ImageView {

    public Live() {
        super(SpriteSheet.getImage());

        setViewport(new Rectangle2D(780, 30, 30, 18));
        setScaleX(2);
        setScaleY(2);
    }

}
