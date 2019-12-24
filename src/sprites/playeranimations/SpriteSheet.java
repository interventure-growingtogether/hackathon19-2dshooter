/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites.playeranimations;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 *
 * @author Marko
 */
public class SpriteSheet {

    private static Image KNIGHT_BLUE_IMAGE = new Image("spritesheet/KnightBlue.png");
    private static Image CASTLE_IMAGE = new Image("spritesheet/Castle.png");
    private static Image ORC_IMAGE = new Image("spritesheet/Orc.png");
    private static Image BG_IMAGE = new Image("spritesheet/bg.jpg");
    private static Image DRAGON_IMAGE = new Image("spritesheet/dragon.png");
    private static Image IV_LOGO_TRANS = new Image("spritesheet/iv-logo-transparent.png");

    static public Image getImage() {
        return makeTransparent(KNIGHT_BLUE_IMAGE);
    }

    static public Image getCastleImage() {
        return CASTLE_IMAGE;
    }

    static public Image getOrcImage() {
        return makeTransparent(ORC_IMAGE);
    }

    static public Image getBgImage() {
        return BG_IMAGE;
    }
    
    static public Image getDragonImage() {
        return DRAGON_IMAGE;
    }

    static public Image getInterVentureLogo() {
        return IV_LOGO_TRANS;
    }

    static private Image makeTransparent(Image inputImage) {
        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(W, H);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);

                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                //pozadina
                if (r == 112
                        && g >= 146
                        && b >= 190) {
                    argb &= 0x00FFFFFF;
                }
                //ivice
                if (r == 163
                        && g >= 73
                        && b >= 164) {
                    argb &= 0x00FFFFFF;
                }

                writer.setArgb(x, y, argb);
            }
        }

        return outputImage;
    }
}
