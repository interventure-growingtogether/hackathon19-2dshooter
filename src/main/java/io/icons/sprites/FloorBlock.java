/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites;

import io.icons.gameengine.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author mile
 */
public class FloorBlock extends Sprite {

    private Rectangle block;

    private static int uid = 0;
    private int id = uid++;

    public int getMyId() {
        return id;
    }

    public FloorBlock() {
        block = new Rectangle(-0.5, -0.5, 1, 1);
        block.setFill(Color.SIENNA);
        getChildren().add(block);

        Polygon p = new Polygon(
                new double[]{
                    -0.5, -0.5,
                    -0.5, 0,
                    
                    -0.35, -0.3,
                    -0.25, 0,
                    0, -0.3,
                    0.25, 0,
                    0.35, -0.3,
                    
                    0.5, 0,
                    0.5, -0.5
                });
        p.setFill(Color.LIME);

        //block.setStroke(Color.ORANGE);
        block.setStrokeWidth(0.05);
        block.setStrokeType(StrokeType.INSIDE);

        // p.setStroke(Color.BLACK);
        p.setStrokeWidth(0.05);
        p.setStrokeType(StrokeType.INSIDE);

        getChildren().add(p);
    }

}
