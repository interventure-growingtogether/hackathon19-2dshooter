package io.icons.sprites;

import javafx.scene.image.ImageView;
import io.icons.sprites.playeranimations.SpriteSheet;

public class IvLogo extends ImageView {

    public IvLogo() {
        super(SpriteSheet.getInterVentureLogo());
        setScaleX(0.35);
        setScaleY(0.35);
    }
}
