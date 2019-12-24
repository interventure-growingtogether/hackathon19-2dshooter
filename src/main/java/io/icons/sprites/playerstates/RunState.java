/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites.playerstates;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import io.icons.sprites.Player;

/**
 *
 * @author mile
 */
public class RunState extends State {

    public RunState(Player player) {
        super(player);

        resetState();
    }

    class Pera extends Transition {

        int count = 6;

        public Pera() {
            setCycleDuration(Duration.millis(1000));
            setInterpolator(Interpolator.LINEAR);
        }

        @Override
        protected void interpolate(double frac) {
            int index = Math.min((int) Math.floor(frac * count), count - 1);
            int startOffset = 25;
            int imageWidth = 50;
            int spaceBetweenImages = 4;

            //index = 5 - index;//hoda unazad
            player.getIv().setViewport(new Rectangle2D(index * imageWidth + startOffset + index * spaceBetweenImages /*20/*+ 1*/, 152, 50, 50));

            //System.out.println(index);
        }
    };

    @Override
    public void jumpPressed() {
        super.jumpPressed();
        player.jump();
    }

    @Override
    public void leftReleased() {
        super.leftReleased();
        if (player.isFaceLeft()) {
            player.stop();
        }
    }

    @Override
    public void leftPressed() {
        super.leftPressed();
        player.faceLeft();
        player.run();
    }

    @Override
    public void rightReleased() {
        super.rightReleased();
        if (player.isFaceRight()) {
            player.stop();
        }
    }

    @Override
    public void rightPressed() {
        super.rightPressed();
        player.faceRight();
        player.run();
    }

    @Override
    public void move() {
        player.setTranslateX(player.getTranslateX() + player.getVelocity());
    }

    @Override
    public void resetState() {
        Pera p = new Pera();
        p.setCycleCount(Transition.INDEFINITE);
        p.play();
        player.setAnimation(p);

    }

}
