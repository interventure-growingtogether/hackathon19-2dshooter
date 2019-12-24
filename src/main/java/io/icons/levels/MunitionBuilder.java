/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.levels;

import io.icons.gameengine.Sprite;
import io.icons.sprites.Munition;

import java.util.Random;

import static io.icons.levels.LevelElements.MUNITION;
import static io.icons.levels.LevelElements.MUNITION_MASK;

/**
 *
 * @author Marko
 */
public class MunitionBuilder implements LevelElementBuilder {

    private static final Random RND = new Random();

    @Override
    public Sprite buildElement(byte element, int x, int width) {

        if ((element & MUNITION_MASK) == MUNITION) {
            Sprite munition = new Munition();
            munition.setTranslateX(x - width / (RND.nextInt(3) + 1));
            munition.setTranslateY(-20);

            return munition;
        }

        return null;
    }

}
