/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.levels;

import io.icons.gameengine.Sprite;
import io.icons.sprites.Enemy;

import java.util.Random;

import static io.icons.levels.LevelElements.ENEMY1;
import static io.icons.levels.LevelElements.ENEMY_MASK;

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
