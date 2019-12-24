/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites;

import io.icons.gameengine.Sprite;
import io.icons.sprites.playeranimations.SpriteSheet;
import io.icons.sprites.playerstates.DeadState;
import io.icons.sprites.playerstates.FallState;
import io.icons.sprites.playerstates.IdleState;
import io.icons.sprites.playerstates.JumpState;
import io.icons.sprites.playerstates.RunState;
import io.icons.sprites.playerstates.State;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.stream.Stream;

/**
 *
 * @author mile
 */
public class Player extends Sprite implements EventHandler<KeyEvent> {

    public interface PlayerHitListener {

        public void onPlayerHit();

        public void onPlayerDie();

        public void onPlayerWin();
    }
    private PlayerHitListener listener;

    private static final double PLAYER_VELOCITY = 7.5f;
    Image image = SpriteSheet.getImage();
    ImageView iv = new ImageView(image);
    private final State IDLE_STATE = new IdleState(this);
    private final State RUN_STATE = new RunState(this);
    private final State JUMP_STATE = new JumpState(this);
    private final State DEAD_STATE = new DeadState(this);
    private final State FALL_STATE = new FallState(this);

    private Transition animation;
    private State state;
    private boolean right = true;
    private double velocity = PLAYER_VELOCITY;

    private int hitsToTake = 3;

    public Player() {
        state = IDLE_STATE;
        state.resetState();

        iv.setTranslateX(-25);
        iv.setTranslateY(-25);
        iv.setScaleX(2);
        iv.setScaleY(2);
        getChildren().add(iv);
    }

    public Player(PlayerHitListener listener) {
        this();
        this.listener = listener;
    }

    public ImageView getIv() {
        return iv;
    }

    public void stopAnimation() {
        if (animation != null) {
            animation.stop();
            animation.pause();
        }
    }

    public void setAnimation(Transition animation) {
        if (this.animation != null) {
            this.animation.stop();
            this.animation.pause();
        }
        this.animation = animation;
    }

    public double getVelocity() {
        if (isFaceRight()) {
            return velocity;
        } else {
            return -velocity;
        }
    }

    public void run() {
        if (!(state instanceof RunState)) {
            state = RUN_STATE;
            state.resetState();
        }

    }

    public void faceRight() {
        if (isFaceLeft()) {
            setScaleX(-getScaleX());
        }
        right = true;
    }

    public void faceLeft() {
        if (isFaceRight()) {
            setScaleX(-getScaleX());
        }
        right = false;
    }

    public void jump() {
        state = JUMP_STATE;
        state.resetState();
    }

    public void stop() {
        state = IDLE_STATE;
        state.resetState();
    }

    public void die() {
        if (listener != null) {
            listener.onPlayerDie();
        }

        state = DEAD_STATE;
        state.resetState();
    }

    public boolean isFaceRight() {
        return right;
    }

    public boolean isFaceLeft() {
        return !right;
    }

    @Override
    public void update() {
        state.move();
    }

    // executed on keyboard input to perform particular actions
    @Override
    public void handle(KeyEvent event) {
        if (isPressed(event, KeyCode.D, KeyCode.RIGHT)) {
            state.rightPressed();
        } else if (isReleased(event, KeyCode.D, KeyCode.RIGHT)) {
            state.rightReleased();
        } else if (isPressed(event, KeyCode.A, KeyCode.LEFT)) {
            state.leftPressed();
        } else if (isReleased(event, KeyCode.A, KeyCode.LEFT)) {
            state.leftReleased();
        } else if (isPressed(event, KeyCode.SPACE, KeyCode.W, KeyCode.UP)) {
            state.jumpPressed();
        } else if (isReleased(event, KeyCode.SPACE, KeyCode.W, KeyCode.UP)) {
            state.jumpReleased();
        } else if (isPressed(event, KeyCode.DIGIT1)) {

        } else if (isPressed(event, KeyCode.DIGIT2)) {

        } else if (isPressed(event, KeyCode.DIGIT3)) {

        }
    }

    private boolean isPressed(final KeyEvent event, KeyCode... keyCodes) {
        return event.getEventType() == KeyEvent.KEY_PRESSED && matchesAnyKey(event, keyCodes);
    }

    private boolean matchesAnyKey(final KeyEvent event, final KeyCode... keyCodes) {
        return Stream.of(keyCodes).anyMatch(kc -> event.getCode() == kc);
    }

    private boolean isReleased(final KeyEvent event, KeyCode... keyCodes) {
        return event.getEventType() == KeyEvent.KEY_RELEASED && matchesAnyKey(event, keyCodes);
    }

    public void takeHit() {
        if (listener != null) {
            listener.onPlayerHit();
        }

        if (--hitsToTake == 0) {

            //TODO za sada samo, mora se ispraviti
            if (!(state instanceof DeadState)) {
                RotateTransition rt = new RotateTransition(Duration.seconds(0.5), this);
                rt.setAutoReverse(false);
                rt.setCycleCount(5);
                rt.setInterpolator(Interpolator.LINEAR);
                rt.setFromAngle(0);
                rt.setToAngle(360);
                rt.setOnFinished(e -> {
                    die();
                });
                rt.play();

                Timeline t = new Timeline(new KeyFrame(Duration.seconds(0.8), new KeyValue(this.translateYProperty(), 200)),
                        new KeyFrame(Duration.seconds(2), new KeyValue(this.translateYProperty(), 1100)));

                t.play();

                ScaleTransition sc = new ScaleTransition(Duration.seconds(0.8), this);
                sc.setFromX(1);
                sc.setFromY(1);
                sc.setToX(1.8);
                sc.setToY(1.8);
                sc.setAutoReverse(true);

                sc.play();
            }

            // die();
        }

    }

    @Override
    public boolean collide(Sprite sprite) {

        if (sprite instanceof EndSprite) {
            if (this.getBoundsInParent().intersects(sprite.getBoundsInParent())) {
                if (listener != null) {
                    listener.onPlayerWin();
                }
            }
        }

        if (sprite instanceof Enemy || sprite instanceof Orc) {

            if (this.getBoundsInParent().intersects(sprite.getBoundsInParent())) {

                if (this.getBoundsInParent().getMaxY() - 20 <= sprite.getBoundsInParent().getMinY()) {

                    sprite.setSpriteDead();

                } else {
                    sprite.setSpriteDead();
                    this.takeHit();
                }
                return true;
            }
        }

        if (sprite instanceof FloorBlock) {

            Bounds me = this.getBoundsInParent();
            Bounds block = sprite.getBoundsInParent();

            if (me.intersects(block) && (state instanceof JumpState || state instanceof FallState)) {

                if (me.getMaxY() >= block.getMinY() && me.getMaxY() <= block.getMaxY() - 20
                        /*&& me.getMinX() + me.getWidth() / 2 >= block.getMinX()
                        && me.getMinX() + me.getWidth() / 2 <= block.getMaxX()*/
                        && ((me.getMaxX() - 27 > block.getMinX() && me.getMaxX() < block.getMaxX())
                        || (me.getMinX() + 27 < block.getMaxX() && me.getMinX() > block.getMinX()))) {
                    this.stop();

                    TranslateTransition t = new TranslateTransition(Duration.millis(80), this);
                    t.setInterpolator(Interpolator.LINEAR);
                    t.setToY((block.getMinY() - me.getHeight() / 2));
                    t.play();
                }

                return true;
            }

        }

        if (sprite instanceof FloorHole) {
            Bounds objA = this.getBoundsInParent();
            Bounds objB = sprite.getBoundsInParent();

            if (objA.intersects(objB) && (state instanceof RunState)) {
                if (objA.getMaxY() - 40/* - 20*/ <= objB.getMinY()
                        && objA.getMinX() + objA.getWidth() / 3 >= objB.getMinX()
                        && objA.getMaxX() - objA.getWidth() / 3 <= objB.getMaxX()) {

                    if (((FloorHole) sprite).isLowestLevel()) {
                        hitsToTake--;
                        if (listener != null) {
                            listener.onPlayerHit();
                        }

                        if (hitsToTake == 0) {
                            state = FALL_STATE;
                            state.resetState();

                            die();
                        } else {
                            double move = 0;
                            if (objA.getMaxX() > objB.getMinX() + objB.getWidth() / 2) {
                                move = objB.getWidth();
                            } else {
                                move = -objB.getWidth();
                            }
                            setTranslateX(getTranslateX() + move);
                        }

                    } else {
                        state = FALL_STATE;
                        state.resetState();
                    }

                }
                return true;
            } else if (objA.intersects(objB) && (state instanceof JumpState || state instanceof FallState) && objA.getMaxY() - 40/* - 20*/ <= objB.getMinY()
                    && objA.getMinX() + objA.getWidth() / 3 >= objB.getMinX()
                    && objA.getMaxX() - objA.getWidth() / 3 <= objB.getMaxX()
                    && ((FloorHole) sprite).isLowestLevel()) {
                if (((FloorHole) sprite).isLowestLevel()) {
                    hitsToTake--;
                    if (listener != null) {
                        listener.onPlayerHit();
                    }

                    if (hitsToTake == 0) {
                        state = FALL_STATE;
                        state.resetState();

                        die();
                    } else {
                        double move = 0;
                        if (objA.getMaxX() > objB.getMinX() + objB.getWidth() / 2) {
                            move = objB.getWidth();
                        } else {
                            move = -objB.getWidth();
                        }
                        setTranslateX(getTranslateX() + move);
                    }

                } else {
                    state = FALL_STATE;
                    state.resetState();
                }

            }

        }
        return false;
    }

}
