/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import gameengine.Sprite;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import static levels.LevelElements.*;
import sprites.GroundStaff;
import sprites.EndSprite;
import sprites.FloorBlock;
import sprites.FloorHole;

/**
 * Graditelj ^Fabricki metod
 *
 * @author Marko
 */
public class LevelBuilder {

    public static final int FLOOR_HEIGHT = 50;
    private static final int VERTICAL_SPACE = -4 * FLOOR_HEIGHT;

    static Integer xTranslate = 0, lastWidth = 0;

    static List<LevelElementBuilder> elements = new ArrayList();

    static {
        elements.add(new EndBuilder());
        elements.add(new CoinBuilder());
        elements.add(new MunitionBuilder());
        elements.add(new EnemyBuilder());
        elements.add(new OrcBuilder());
    }

    public static List<Sprite> generate(byte[][] level) {
        List<Sprite> result = new ArrayList<>();

        int platformVertcialCount = level.length;
        int platformHorizontalCount = level[0].length;

        for (int vertical = platformVertcialCount - 1; vertical >= 0; vertical--) {
            xTranslate = 0;
            for (int horizontal = 0; horizontal < platformHorizontalCount; horizontal++) {

                Sprite floorOrHole = generateType(level[vertical][horizontal], xTranslate, (platformVertcialCount - vertical - 1) * VERTICAL_SPACE - FLOOR_HEIGHT / 2);

                if (floorOrHole instanceof FloorHole && vertical == platformVertcialCount - 1) {
                    ((FloorHole) floorOrHole).setLowestLevel(true);
                }

                if (vertical == platformVertcialCount - 1) {
                    Sprite groundStaff = null;
                    if (floorOrHole instanceof FloorBlock) {
                        groundStaff = new GroundStaff(floorOrHole.getBoundsInParent().getWidth(), Color.SIENNA);
                    } else {
                        groundStaff = new GroundStaff(floorOrHole.getBoundsInParent().getWidth(), Color.GREENYELLOW);
                    }

                    groundStaff.setTranslateX(((Translate) floorOrHole.getTransforms().get(0)).getX()+1);
                    groundStaff.setTranslateY(floorOrHole.getTranslateY() - 1);
                    result.add(groundStaff);
                }

                for (LevelElementBuilder eb : elements) {
                    Sprite s = eb.buildElement(level[vertical][horizontal], xTranslate, lastWidth);
                    if (s != null) {
                        s.setTranslateY(s.getTranslateY() + (platformVertcialCount - vertical - 1) * VERTICAL_SPACE - FLOOR_HEIGHT / 2
                                - s.getBoundsInLocal().getHeight() / 2 - FLOOR_HEIGHT / 2);
                        result.add(s);
                    }
                }

                result.add(floorOrHole);
            }

        }
        return result;
    }

    private static Sprite generateType(byte elem, Integer x, int y) {
        Sprite sprite = null;

        switch (elem & TYPE_MASK) {
            case PLATFORM:
                sprite = new FloorBlock();
                break;
            case HOLE:
                sprite = new FloorHole();
                break;
        }

        int width = 0;
        switch (elem & SIZE_MASK) {
            case SMALL:
                width = 2 * FLOOR_HEIGHT;
                break;
            case MEDIUM:
                width = 4 * FLOOR_HEIGHT;
                break;
            case LARGE:
                width = 6 * FLOOR_HEIGHT;
                break;
            case XLARGE:
                width = 8 * FLOOR_HEIGHT;
                break;
        }
        xTranslate += width / 2 - 1;//-1 da predju malo jedni preko drugih jer se inace vidi linija izmedju blokova
        sprite.getTransforms().add(new Translate(x + width / 2, y));
        xTranslate += width / 2 - 1;
        sprite.getTransforms().add(new Scale(width, FLOOR_HEIGHT));

        lastWidth = width;
        return sprite;
    }
}
