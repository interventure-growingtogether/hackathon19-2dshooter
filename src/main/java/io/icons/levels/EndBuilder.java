/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.levels;

import io.icons.gameengine.Sprite;
import io.icons.sprites.EndSprite;

import static io.icons.levels.LevelElements.END;
import static io.icons.levels.LevelElements.END_MASK;

/**
 *
 * @author Marko
 */
public class EndBuilder implements LevelElementBuilder {

    @Override
    public Sprite buildElement(byte element, int x, int width) {
        if ((element & END_MASK) == END) {
            Sprite end = new EndSprite();
            end.setTranslateX(x - width / 2);
            return end;
        }
        return null;
    }

}
