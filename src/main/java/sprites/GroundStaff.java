/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import gameengine.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Marko
 */
public class GroundStaff extends Sprite {

    public GroundStaff(double width, Color c) {
        Rectangle block = new Rectangle(-width/2, 0, width, 800);
        block.setFill(c);
        getChildren().add(block);
    }

}
