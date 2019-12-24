package io.icons.sprites;

import io.icons.sprites.playeranimations.SpriteSheet;
import javafx.scene.image.ImageView;

public class IvLogo extends ImageView {

    public IvLogo() {
        super(SpriteSheet.getInterVentureLogo());
        setScaleX(0.35);
        setScaleY(0.35);
    }
}
