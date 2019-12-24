package sprites;

import javafx.scene.image.ImageView;
import sprites.playeranimations.SpriteSheet;

public class IvLogo extends ImageView {

    public IvLogo() {
        super(SpriteSheet.getInterVentureLogo());
        setScaleX(0.35);
        setScaleY(0.35);
    }
}
