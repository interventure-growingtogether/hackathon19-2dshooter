/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import gameengine.Sprite;
import java.util.Random;
import static levels.LevelElements.ENEMY1;
import static levels.LevelElements.ENEMY_MASK;
import sprites.Enemy;

/**
 *
 * @author Marko
 */
public class EnemyBuilder implements LevelElementBuilder {

    private static final Random RND = new Random();

    @Override
    public Sprite buildElement(byte element, int x, int width) {

        if ((element & ENEMY_MASK) == ENEMY1) {
            Enemy enemy = new Enemy(2);
            enemy.setLeftBound(x - width);
            enemy.setRightBound(x);

            double min = x - width + enemy.getBoundsInParent().getWidth() / 2;
            double max = x - enemy.getBoundsInParent().getWidth() / 2;
            enemy.setTranslateX(RND.nextDouble() * (max - min) + min);
            enemy.setTranslateY(-enemy.getBoundsInParent().getHeight() / 2);
            return enemy;
        }

        return null;
    }

}
