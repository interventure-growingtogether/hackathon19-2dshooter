/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameengine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Marko
 */
public class SpriteContainer {

    private final List<Sprite> sprites = new ArrayList<Sprite>();
    private final Set<Sprite> deadSprites = new HashSet<>();

    public List<Sprite> getSprites() {
        return sprites;
    }

    public void addSprites(List<Sprite> sprites) {
        this.sprites.addAll(sprites);
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprites(List<Sprite> sprites) {
        this.sprites.removeAll(sprites);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    public void addDeadSprite(Sprite sprite) {
        deadSprites.add(sprite);
    }

    public void addDeadSprites(List<Sprite> sprites) {
        deadSprites.addAll(sprites);
    }

    public void cleanDeadSprites() {
        sprites.removeAll(deadSprites);
        deadSprites.clear();
    }

}
