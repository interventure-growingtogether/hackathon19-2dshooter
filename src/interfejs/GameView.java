/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfejs;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sprites.Coin;
import sprites.playeranimations.Axe;
import sprites.playeranimations.Live;

/**
 *
 * @author Marko
 */
public class GameView extends Group {

    private double width, height;
    Live lives[] = new Live[3];

    private Text time;
    private Text munition;
    private Text points;

    public GameView(double width, double height) {
        this.width = width;
        this.height = height;

        initLives();
        initMunition();
        initPoints();
        initTime();
    }

    private void initLives() {
        for (int i = 0; i < 3; i++) {
            lives[i] = new Live();
            lives[i].setTranslateX(width - (i + 1) * (lives[i].getBoundsInLocal().getWidth() + 15));
            lives[i].setTranslateY(10);

            getChildren().addAll(lives[i]);
        }
    }

    private void initMunition() {
        Axe axe = new Axe();
        axe.setTranslateX(15);
        axe.setTranslateY(10);

        munition = new Text("X 0");
        munition.setTranslateX(15 + axe.getBoundsInLocal().getWidth() + 15);
        munition.setTranslateY(30);
        munition.setFont(new Font(20));

        getChildren().addAll(axe, munition);

    }

    private void initPoints() {
        Coin c = new Coin();
        c.setTranslateX(munition.getTranslateX() + 70);
        c.setTranslateY(10 + c.getBoundsInLocal().getHeight() / 2);

        points = new Text("X 0");
        points.setTranslateX(munition.getTranslateX() + 70 + c.getBoundsInLocal().getWidth());
        points.setTranslateY(30);
        points.setFont(new Font(20));

        getChildren().addAll(c, points);
    }

    private void initTime() {
        time = new Text("0");
        time.setTranslateX(width / 2);
        time.setTranslateY(30);
        time.setFill(Color.RED);
        time.setFont(new Font(40));

        getChildren().add(time);
    }

    public void setTime(int min, int sec) {
        time.setText("0" + min + ":" + sec);
    }

    public void setMunition(int count) {
        munition.setText("X " + count);
    }

    public void setPoints(int count) {
        points.setText("X " + count);
    }

    public void setLives(int count) {
        getChildren().removeAll(lives);

        for (int i = 0; i < count; i++) {
            getChildren().add(lives[i]);
        }
    }

}
