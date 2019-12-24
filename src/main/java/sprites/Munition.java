/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import gameengine.Sprite;
import java.util.Random;
import sprites.playeranimations.Axe;

/**
 *
 * @author Marko
 */
public class Munition extends Sprite {

    public interface MunitionPickupListener {

        public void onMunitionPickup(int amount);
    }
    private MunitionPickupListener listener;

    private static final Random RND = new Random();

    private int count;

    public Munition() {
        count = RND.nextInt(3) + 1;

        for (int i = 0; i < count; i++) {
            Axe a = new Axe();
            a.setTranslateX(-a.getBoundsInLocal().getWidth() / 2);
            a.setTranslateY(-a.getBoundsInLocal().getHeight() / 2);

            a.setRotate(i * 30);
            getChildren().add(a);
        }
    }

    public Munition(MunitionPickupListener listener) {
        this();
        this.listener = listener;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean collide(Sprite sprite) {
        if (sprite instanceof Player) {
            if (this.getBoundsInParent().intersects(sprite.getBoundsInParent())) {
                this.setSpriteDead();

                if (listener != null) {
                    listener.onMunitionPickup(count);
                }
                return true;
            }
        }
        return false;
    }

    public void setListener(MunitionPickupListener listener) {
        this.listener = listener;
    }
    
    
}
