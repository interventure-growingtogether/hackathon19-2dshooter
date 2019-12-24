/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.icons.levels;

import io.icons.gameengine.Sprite;

/**
 *
 * @author Marko
 */
public interface LevelElementBuilder {

    /**
     * @param element element koji se proverava za gradnju
     * @param x x kordinata desnog kraja platforme
     * @param width sirina platforme
     */
    public Sprite buildElement(byte element, int x, int width);
}
