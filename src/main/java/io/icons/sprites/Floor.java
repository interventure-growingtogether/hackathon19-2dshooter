/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.sprites;

import io.icons.gameengine.Sprite;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mile
 */
@Deprecated
public class Floor /*extends Sprite*/ {

    private int height;// in pixels
    private int width;// in pixels

    private final List<Sprite> floorElements = new ArrayList<Sprite>();

    public Floor(int width, int height) {
        this.height = height;
        this.width = width;
        Scale scale = new Scale(3 * height, height);// floorBlocks should be square and side equals to the height of the floor
        Translate translate = new Translate(0, -height / 2);
        // floor is made up of a number of floorBlocks the following loop only concatenates a big number of blocks to make up a floor
        int i = -4 * width;

        boolean previosFloor = false;
        Random rnd = new Random();
        while (i < 5 * width + height) {

            Sprite floorElement = null;

            switch (rnd.nextInt(3)) {
                case 0:
                    if (!previosFloor) {
                        floorElement = new FloorHole();
                        previosFloor = true;
                    } else {
                        floorElement = new FloorBlock();
                        previosFloor = false;
                    }
                    break;
                case 1:
                    floorElement = new FloorBlock();
                    previosFloor = false;
                    break;
                case 2:
                    floorElement = new FloorBlock();
                    floorElement.setTranslateY(-height * 4);
                    previosFloor = false;
                    break;
            }

            floorElement.getTransforms().add(new Translate(i, 0));
            floorElement.getTransforms().add(translate);
            floorElement.getTransforms().add(scale);
            //getChildren().add(floorElement);
            i += 3 * height;

            floorElements.add(floorElement);
        }
    }

    public List getFloorElements() {
        return floorElements;
    }

    public void transY(int WINDOW_HEIGHT) {
        floorElements.forEach(e -> e.setTranslateY(e.getTranslateY() + WINDOW_HEIGHT));
    }

}
