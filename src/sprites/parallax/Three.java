/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites.parallax;

import gameengine.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Marko
 */
public class Three extends ParallaxSprite {

    public Three() {
        Rectangle stablo = new Rectangle(-0.5, 0, 1, 3);
        stablo.setFill(Color.BROWN);

        Ellipse krosnja = new Ellipse(0, -0.5, 1, 2);
        krosnja.setFill(Color.GREEN);

        getChildren().addAll(stablo, krosnja);
        
        //speedX = 1;
    }

}
