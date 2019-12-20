/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import gameengine.Sprite;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

/**
 *
 * @author mile
 */
public class Enemy extends Sprite {

    public interface EnemyHitListener {

        public void onEnemyHit();

        public void onEnemyDie();
    }

    private EnemyHitListener listener;

    public void setListener(EnemyHitListener listener) {
        this.listener = listener;
    }

    private static final Color[] HIT_COLORS = new Color[]{Color.BLUEVIOLET, Color.BLUE, Color.OLIVE, Color.ORANGE, Color.SADDLEBROWN,Color.BLACK};

    private double velocity, leftBound, rightBound;
    private int hitCount;
    Polygon head;

    private static int uid = 0;
    private int id = uid++;

    public int getMyId() {
        return id;
    }

    public void setLeftBound(double leftBound) {
        this.leftBound = leftBound;
    }

    public void setRightBound(double rightBound) {
        this.rightBound = rightBound;
    }

    public Enemy(double velocity) {
        this.velocity = velocity;

        head = new Polygon(
                new double[]{
                    -0.4, 0.3,//1
                    -0.4, -0.1,//2
                    -0.5, -0.1,//3
                    -0.3, -0.55,//4
                    0.3, -0.55,//5
                    0.5, -0.1,//6
                    0.4, -0.1,//7
                    0.4, 0.3//8
                });

        /*Arc head = new Arc();
         head.setCenterX(0);
         head.setCenterY(0.3);
         head.setRadiusX(0.5f);
         head.setRadiusY(0.8f);
         head.setStartAngle(0);
         head.setLength(180.0f);
         head.setType(ArcType.CHORD);*/
        head.setFill(Color.SADDLEBROWN);

        Rectangle usta = new Rectangle(-0.3, 0.1, 0.6, 0.05);
        Polygon levaKjova = new Polygon(new double[]{
            -0.3, 0.1,
            -0.2, 0.05,
            -0.1, 0.1
        });
        levaKjova.setFill(Color.WHITE);
        Polygon desnaKljova = new Polygon(new double[]{
            0.1, 0.1,
            0.2, 0.05,
            0.3, 0.1
        });
        desnaKljova.setFill(Color.WHITE);

        Rectangle leftLeg = new Rectangle(-0.4, 0.3, 0.2, 0.2);
        leftLeg.setFill(Color.BLACK);

        Rectangle rightLeg = new Rectangle(0.2, 0.3, 0.2, 0.2);
        rightLeg.setFill(Color.BLACK);

        Rectangle levaObrva = new Rectangle(-0.5, -0.4, 0.6, 0.1);
        levaObrva.setRotate(30);

        Rectangle desnaObrva = new Rectangle(-0.1, -0.4, 0.6, 0.1);
        desnaObrva.setRotate(150);

        Circle leftEye = new Circle(-0.2f, -0.15, 0.15);
        leftEye.setFill(Color.WHITE);

        Circle rightEye = new Circle(0.2f, -0.15, 0.15);
        rightEye.setFill(Color.WHITE);

        getChildren().addAll(head, usta, levaKjova, desnaKljova);
        getChildren().add(leftLeg);
        getChildren().add(rightLeg);
        getChildren().addAll(leftEye, levaObrva);
        getChildren().addAll(rightEye, desnaObrva);

        Scale scale = new Scale(100, 80);
        getTransforms().add(scale);

        Circle leftZenica = new Circle(-0.2f, -0.15, 0.05);
        leftEye.setFill(Color.WHITE);

        Circle rightZenica = new Circle(0.2f, -0.15, 0.05);
        rightEye.setFill(Color.WHITE);

        getChildren().addAll(leftZenica, rightZenica);

        TranslateTransition tt = new TranslateTransition(Duration.seconds(1), leftZenica);
        tt.setFromX(-0.1f);
        tt.setToX(0.1f);
        tt.setAutoReverse(true);
        tt.setCycleCount(Transition.INDEFINITE);

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(1), rightZenica);
        tt1.setFromX(-0.1f);
        tt1.setToX(0.1f);
        tt1.setAutoReverse(true);
        tt1.setCycleCount(Transition.INDEFINITE);

        tt.play();
        tt1.play();

    }

    public void takeHit() {
        head.setFill(HIT_COLORS[hitCount++]);
        if (listener != null) {
            listener.onEnemyHit();
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
        }
    }

    @Override
    public boolean collide(Sprite sprite) {
        if (sprite instanceof Bullet) {
            if (this.getBoundsInParent().intersects(sprite.getBoundsInParent())) {
                takeHit();
                if (hitCount == 5) {
                    TranslateTransition tt = new TranslateTransition(Duration.millis(1500), this);
                    tt.setToY(sprite.getTranslateY() - 50);
                    tt.setCycleCount(1);
                    //tt.play();

                    ScaleTransition st = new ScaleTransition(Duration.millis(1500), this);
                    st.setToX(0);
                    st.setToY(0);
                    st.setCycleCount(1);
                    //st.play();

                    ParallelTransition pt = new ParallelTransition(tt, st);
                    pt.setCycleCount(1);
                    pt.setOnFinished(e -> {
                        this.setSpriteDead();
                    });
                    pt.play();

                }
                sprite.setSpriteDead();

                return true;
            }
        }
        return false;
    }

    @Override
    public void setSpriteDead() {
        super.setSpriteDead();
        if (listener != null) {
            listener.onEnemyDie();
        }
    }

}
