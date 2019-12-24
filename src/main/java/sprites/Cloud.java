/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import gameengine.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author km130663
 */
public class Cloud extends Sprite {

    public Cloud() {

        Circle krug1 = new Circle(45);
        Circle krug2 = new Circle(-30, -30, 30);
        Circle krug3 = new Circle(30, -30, 30);
        krug1.setFill(Color.WHITE);
        krug2.setFill(Color.WHITE);
        krug3.setFill(Color.WHITE);

        IvLogo ivLogo = new IvLogo();
        ivLogo.setRotate(180);
        ivLogo.setTranslateX(-200);
        ivLogo.setTranslateY(-200);

        getChildren().addAll(krug1, krug2, krug3, ivLogo);

        this.setRotate(180);//da ne budi miki nego oblak

    }

    @Override
    public void update() {
        setTranslateX(getTranslateX() - 0.5f);
    }

}
