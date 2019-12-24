/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Marko
 */
public class SpriteContainer {

    private final List<Sprite> SPRITES = new ArrayList<Sprite>();
    private final Set DEAD_SPRITES = new HashSet();

    public List<Sprite> getSprites() {
        return SPRITES;
    }

    public void addSprites(List<Sprite> sprites) {
        SPRITES.addAll(sprites);
    }

    public void addSprite(Sprite sprite) {
        SPRITES.add(sprite);
    }

    public void removeSprites(List<Sprite> sprites) {
        SPRITES.removeAll(sprites);
    }

    public void removeSprite(Sprite sprite) {
        SPRITES.remove(sprite);
    }

    public void addDeadSprite(Sprite sprite) {
        DEAD_SPRITES.add(sprite);
    }

    public void addDeadSprites(List<Sprite> sprites) {
        DEAD_SPRITES.addAll(sprites);
    }

    public void cleanDeadSprites() {
        SPRITES.removeAll(DEAD_SPRITES);
        DEAD_SPRITES.clear();
    }

}
