/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites;

import io.icons.gameengine.Sprite;
import io.icons.sprites.playeranimations.SpriteSheet;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import java.util.Random;

/**
 *
 * @author Marko
 */
public class Bird extends Sprite {

    private double speedY = new Random().nextDouble(), tempSpeed;
    private double rangeY = 3f;

    ImageView imageView = new ImageView(SpriteSheet.getDragonImage());

    public Bird() {
        if (speedY > 0.5) {
            speedY = speedY / 4;
        } else {
            speedY /= 2;
        }

        Circle glava = new Circle(10);
        glava.setFill(Color.RED);

        Circle levoOko = new Circle(-5, -4, 3, Color.WHITE);
        Circle desnoOko = new Circle(-1, -4, 3, Color.WHITE);

        Circle levaZenica = new Circle(-4, -4, 1, Color.BLACK);
        Circle desnaZenica = new Circle(0, -4, 1, Color.BLACK);

        Ellipse gornjaUsna = new Ellipse(-5, 4, 5, 1);
        gornjaUsna.setFill(Color.ORANGE);
        gornjaUsna.setRotate(-30);

        Ellipse donjaUsna = new Ellipse(-5, 6, 5, 1);
        donjaUsna.setFill(Color.ORANGE);
        donjaUsna.setRotate(-55);

        //getChildren().addAll(glava, levoOko, desnoOko, levaZenica, desnaZenica, gornjaUsna, donjaUsna);

        
        imageView.setTranslateX(-280 / 2);
        imageView.setTranslateY(-280 / 2);
        imageView.setScaleX(-0.3);
        imageView.setScaleY(0.3);
        
        getChildren().add(imageView);

        Pera p = new Pera();
        p.setCycleCount(Transition.INDEFINITE);
        p.play();
    }

    class Pera extends Transition {

        int count = 8;

        public Pera() {
            setCycleDuration(Duration.millis(800));
            setInterpolator(Interpolator.LINEAR);
        }

        @Override
        protected void interpolate(double frac) {
            int index = Math.min((int) Math.floor(frac * count), count - 1);
            int startOffset = 0;
            int imageWidth = 294;
            int imageHeight = 280;
            int spaceBetweenImages = 0;

           // index = 7;
            imageView.setViewport(new Rectangle2D(index * imageWidth + startOffset + index * spaceBetweenImages, 140, imageWidth, imageHeight));

            //System.out.println(index);
        }
    };

    @Override
    public void update() {
        setTranslateX(getTranslateX() - 1f);
        setTranslateY(getTranslateY() + tempSpeed);

        tempSpeed += speedY;
        if (rangeY - Math.abs(tempSpeed) < 0) {
            speedY = -speedY;
        }
    }

}
