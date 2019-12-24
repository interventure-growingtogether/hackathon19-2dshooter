/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.gameengine;

import javafx.scene.Group;

/**
 *
 * @author mile
 */
public abstract class Sprite extends Group {

    private boolean dead = false;

    /**
     * Method used to update sprite position/state, should bee called from
     * GameEngine.
     */
    public void update() {

    }

    /**
     * Method for checking collision between this sprite and passed sprite.
     *
     * @param sprite
     * @return True if there is collision
     */
    public boolean collide(Sprite sprite) {
        return false;
    }

    public boolean isSpriteDead() {
        return dead;
    }

    public void setSpriteDead() {
        this.dead = true;
    }

}
