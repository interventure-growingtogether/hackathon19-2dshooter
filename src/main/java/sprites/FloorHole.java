/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import gameengine.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Marko
 */
public class FloorHole extends Sprite {
    
    private boolean lowestLevel = false;

    private Rectangle hole;

    public FloorHole() {
        hole = new Rectangle(-0.5, -0.5, 1, 1);
        hole.setFill(Color.TRANSPARENT);
        
        //hole.setStroke(Color.RED);
        hole.setStrokeWidth(0.1);
        hole.setStrokeType(StrokeType.INSIDE);
        
        getChildren().add(hole);


    }

    public boolean isLowestLevel() {
        return lowestLevel;
    }

    public void setLowestLevel(boolean lowestLevel) {
        this.lowestLevel = lowestLevel;
    }
    
    

}