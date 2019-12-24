/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites;

import io.icons.gameengine.Sprite;
import java.util.Random;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import io.icons.sprites.playeranimations.SpriteSheet;

/**
 *
 * @author Marko
 */
public class Orc extends Sprite {

    private static final Random RND = new Random();

    private double velocity, leftBound, rightBound;
    private int hitCount;
    ImageView iv = new ImageView(SpriteSheet.getOrcImage());
    Pera p;

    public void setLeftBound(double leftBound) {
        this.leftBound = leftBound;
    }

    public void setRightBound(double rightBound) {
        this.rightBound = rightBound;
    }

    class Pera extends Transition {

        int count = 4;

        public Pera() {
            setCycleDuration(Duration.millis(1000));
            setInterpolator(Interpolator.LINEAR);
        }

        @Override
        protected void interpolate(double frac) {
            int index = Math.min((int) Math.floor(frac * count), count - 1);
            int startOffset = 18;
            int imageWidth = 50;
            int spaceBetweenImages = 4;

            //index = 5 - index;//hoda unazad
            iv.setViewport(new Rectangle2D(index * imageWidth + startOffset + index * spaceBetweenImages /*20/*+ 1*/, 105, 50, 50));

            //System.out.println(index);
        }
    };

    public Orc(double velocity) {
        this.velocity = velocity;

        iv.setViewport(new Rectangle2D(25 /*20/*+ 1*/, 95, 50, 50));

        iv.setTranslateX(-25);
        iv.setTranslateY(-25);
        iv.setScaleX(2);
        iv.setScaleY(2);
        getChildren().add(iv);

        setScaleX(-getScaleX());

        p = new Pera();
        p.setCycleCount(Transition.INDEFINITE);
        p.play();

    }

    public void takeHit(double bulletX) {
        double orcX = this.getBoundsInParent().getMinX() + this.getBoundsInParent().getWidth() / 2;
        if (bulletX < orcX +5 && this.getScaleX() > 0) {
            hitCount++;
        } else if (bulletX > orcX - 5 && this.getScaleX() < 0) {
            hitCount++;
        }

    }

    public int getHitCount() {
        return hitCount;
    }

    @Override
    public void update() {
        setTranslateX(getTranslateX() - velocity);

        double w = getBoundsInParent().getWidth();
        if (getTranslateX() < leftBound + w / 2 || getTranslateX() > rightBound - w / 2) {
            velocity = -velocity;
            setScaleX(-getScaleX());
            //p.play();
        }

    }

    @Override
    public boolean collide(Sprite sprite) {
        if (sprite instanceof Bullet) {
            if (this.getBoundsInParent().intersects(sprite.getBoundsInParent())) {
                takeHit(sprite.getBoundsInParent().getMinX() + sprite.getBoundsInParent().getWidth() / 2);
                if (hitCount == 1) {
                    this.setSpriteDead();
                }
                sprite.setSpriteDead();

                return true;
            }
        }
        return false;
    }
}
