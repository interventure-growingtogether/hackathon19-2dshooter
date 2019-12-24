/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites.playerstates;

import javafx.geometry.Rectangle2D;
import sprites.Player;

/**
 *
 * @author mile
 */
public class FallState extends State {

    private static final double JUMP_VELOCITY = 15.0f;
    private static final double GRAVITY_ACCELERATION = -0.5f;
    private static final double ACCURACY = 0.01f;

    private double velocity = 0;

    public FallState(Player player) {
        super(player);
    }

    @Override
    public void move() {
        player.setTranslateX(player.getTranslateX() + player.getVelocity());
        player.setTranslateY(player.getTranslateY() - velocity);
        velocity += GRAVITY_ACCELERATION;
        /*if (Math.abs(velocity + JUMP_VELOCITY - GRAVITY_ACCELERATION) < ACCURACY) {
            player.stop();
        }*/
    }

    @Override
    public void resetState() {
        player.stopAnimation();
        velocity = 0;

        player.getIv().setViewport(new Rectangle2D(/*82*/79, 470, 50, 50));
    }

    @Override
    public void leftPressed() {
        player.faceLeft();
    }

    @Override
    public void rightPressed() {
        player.faceRight();
    }

}
