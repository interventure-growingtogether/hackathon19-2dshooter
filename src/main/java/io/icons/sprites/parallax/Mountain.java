/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites.parallax;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Marko
 */
public class Mountain extends ParallaxSprite {

    public Mountain(ParallaxLevel level) {
        Polygon p = new Polygon(new double[]{
            -1, 1,
            0, -1.5,
            1, 1,
            -1, 1
        });

        Polygon p2 = new Polygon(new double[]{
            -0.4, -0.5,
            -0.2, -0.5,
            0, -0.2,
            0.25, -0.5,
            0.35, -0.4,
            0.4, -0.5,
            0, -1.5,});

        switch (level) {
            case FARR:
                p.setFill(Color.rgb(167, 159, 159));
                p2.setFill(Color.rgb(220, 214, 214));
                speedX = 0.2;
                break;
            case MIDDLE:
                p.setFill(Color.GRAY);
                p2.setFill(Color.WHITE);
                speedX = 0.5;
                break;
            case NEAR:
                p.setFill(Color.DARKGRAY);
                p2.setFill(Color.WHITE);
                //speedX = 1;
                break;

        }

        getChildren().addAll(p, p2);
    }

}
