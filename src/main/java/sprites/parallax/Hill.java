/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites.parallax;

import gameengine.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

/**
 *
 * @author Marko
 */
public class Hill extends ParallaxSprite {

    public Hill() {
        Arc a = new Arc(0, 0, 2, 1, 0, 180);
        a.setType(ArcType.CHORD);
        a.setFill(Color.GREENYELLOW);
        
        getChildren().add(a);
        
        //speedX = 1;
    }
    
}
