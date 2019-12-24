/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites;

import io.icons.gameengine.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author mile
 */
public class Background extends Sprite {

    public Background(int width, int height) {
        Rectangle bg = new Rectangle(0, 0, width, height);
        
        LinearGradient lg = new LinearGradient(0.5,0 ,0.5 ,1 , true, CycleMethod.NO_CYCLE, new Stop(0,Color.SKYBLUE), new Stop(1,Color.AQUA));
        
        /*Cloud c1 = new Cloud();
        c1.setTranslateX(width/2);
        c1.setTranslateY(height/2);*/
        
        bg.setFill(lg);
        getChildren().addAll(bg);
        
        
        
    }
}
