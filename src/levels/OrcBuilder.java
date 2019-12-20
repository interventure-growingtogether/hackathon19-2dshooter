/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import gameengine.Sprite;
import java.util.Random;
import static levels.LevelElements.ENEMY2;
import static levels.LevelElements.ENEMY_MASK;
import sprites.Orc;

/**
 *
 * @author Marko
 */
public class OrcBuilder implements LevelElementBuilder {

    private static final Random RND = new Random();

    @Override
    public Sprite buildElement(byte element, int x, int width) {

        if ((element & ENEMY_MASK) == ENEMY2) {
            Orc orc = new Orc(2);
            orc.setLeftBound(x - width);
            orc.setRightBound(x);

            double min = x - width + orc.getBoundsInParent().getWidth() / 2;
            double max = x - orc.getBoundsInParent().getWidth() / 2;
            orc.setTranslateX(RND.nextDouble() * (max - min) + min);
            //orc.setTranslateY(-orc.getBoundsInParent().getHeight() / 2);
            return orc;
        }

        return null;
    }

}
