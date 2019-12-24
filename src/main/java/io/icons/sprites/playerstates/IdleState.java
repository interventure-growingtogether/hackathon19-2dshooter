/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites.playerstates;

import javafx.geometry.Rectangle2D;
import io.icons.sprites.Player;

/**
 *
 * @author mile
 */
public class IdleState extends State {

    public IdleState(Player player) {
        super(player);
        resetState();
    }

    @Override
    public void jumpPressed() {
        super.jumpPressed();
        player.jump();
    }

    @Override
    public void leftPressed() {
        super.leftPressed();
        player.faceLeft();
        player.run();
    }

    @Override
    public void rightPressed() {
        super.rightPressed();
        player.faceRight();
        player.run();
    }

    @Override
    public void resetState() {
        player.stopAnimation();
        player.getIv().setViewport(new Rectangle2D(/*82*/25, 52, 50, 50));

    }

}
