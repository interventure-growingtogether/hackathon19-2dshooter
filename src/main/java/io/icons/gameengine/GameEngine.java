/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.gameengine;

import java.util.List;

import javafx.animation.AnimationTimer;

/**
 * @author Marko
 */
public abstract class GameEngine {

    protected boolean paused = false;
    protected boolean quizStarted = false;

    private AnimationTimer timer;
    private final SpriteContainer spriteContainer = new SpriteContainer();

    public GameEngine() {

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                drawScene();
            }
        };
    }

    private void drawScene() {
        if (!paused && !quizStarted) {
            updateSprites();
            colideSprites();
            cleanSprites();
        }
    }

    public void startGame() {
        timer.start();
    }

    public void stopGame() {
        timer.stop();
    }

    public void setPaused(final boolean paused) {
        this.paused = paused;
    }

    public void setQuizStarted(final boolean quizStarted) {
        this.quizStarted = quizStarted;
    }

    protected void updateSprites() {
        spriteContainer
                .getSprites()
                .forEach(Sprite::update);
    }

    protected void colideSprites() {
        List<Sprite> s1, s2;
        s1 = s2 = spriteContainer.getSprites();

        for (Sprite sprite1 : s1) {
            for (Sprite sprite2 : s2) {
                if (sprite1.collide(sprite2)) {

                    handleCollision(sprite1, sprite2);

                    /* Optimisation if one sprite can collide only one sprite 
                    *  per frame,
                    *  if sprite can collide more io.icons.sprites remove break.    
                     */
                    //break;
                }
            }
        }

    }

    /**
     * Method for extra operations if collision happened.
     *
     * Example: If Bullet hits Sprite you want to remove them from GameEngine so
     * you call spriteContainer.addDeadSprites(Bullet, Sprite)
     *
     * @param s1
     * @param s2
     */
    protected void handleCollision(Sprite s1, Sprite s2) {
        if (s1.isSpriteDead()) {
            spriteContainer.addDeadSprite(s1);
        }

        if (s2.isSpriteDead()) {
            spriteContainer.addDeadSprite(s2);
        }
    }

    protected void cleanSprites() {
        spriteContainer.cleanDeadSprites();
    }

    public SpriteContainer getSpriteContainer() {
        return spriteContainer;
    }

}
