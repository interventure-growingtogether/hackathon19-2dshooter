package io.icons;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import io.icons.cameras.Camera;
import io.icons.gameengine.GameEngine;
import io.icons.gameengine.Sprite;
import io.icons.interfejs.GameView;
import io.icons.levels.LevelBuilder;
import io.icons.levels.Levels;
import io.icons.sprites.Background;
import io.icons.sprites.Bird;
import io.icons.sprites.Bullet;
import io.icons.sprites.Cloud;
import io.icons.sprites.Coin;
import io.icons.sprites.Enemy;
import io.icons.sprites.Munition;
import io.icons.sprites.Player;
import io.icons.sprites.parallax.Hill;
import io.icons.sprites.parallax.Mountain;
import io.icons.sprites.parallax.ParallaxLevel;
import io.icons.sprites.parallax.ParallaxSprite;
import io.icons.sprites.parallax.Three;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Marko
 */
public class PlatformerGame extends GameEngine {

    public static final int WINDOW_WIDTH = 1600 * 4 / 6;
    public static final int WINDOW_HEIGHT = 900 * 4 / 6;

    public static final int FLOOR_WIDTH = WINDOW_WIDTH;
    public static final int FLOOR_HEIGHT = 50;

    public static final int ENEMY_WIDTH = 100;
    public static final int ENEMY_HEIGHT = /*80*/ 100;

    public static final String TITLE = "IVformer";

    public static final double ENEMY_SPEED = 2.5f;

    private static final Random RND = new Random();

    private double time = 0, gameTime = 180; // game time in seconds

    private Player player;
    // camera used for applying transformations
    private Camera camera = new Camera();

    Group sprites = new Group();
    Group root = new Group();

    GameView gv;
    PlatformerGameListener pf;

    List<ParallaxSprite> parallaxSprites = new ArrayList<>();

    Scene scene;

    Main main;

    public PlatformerGame(Main main) {
        super();

        this.main = main;

        gv = new GameView(WINDOW_WIDTH, WINDOW_HEIGHT);
        pf = new PlatformerGameListener(gv, this);
        Background bg = new Background(WINDOW_WIDTH, WINDOW_HEIGHT);

        //Floor floor = new Floor(FLOOR_WIDTH, FLOOR_HEIGHT);
        //floor.setTranslateY(WINDOW_HEIGHT);
        //floor.transY(WINDOW_HEIGHT);
        //getSpriteContainer().addSprites(floor.getFloorElements());
        //camera.getChildren().addAll(floor.getFloorElements());
        createMountains(100);
        createHills(100);
        createThrees(100);
        createClouds(100);
        createBirds(100);

        camera.getChildren().addAll(sprites);
        root.getChildren().addAll(bg, camera, gv);

        List<Sprite> levelSprites = LevelBuilder.generate(Levels.LEVEL_1);
        levelSprites.forEach(s -> {
            s.setTranslateY(s.getTranslateY() + WINDOW_HEIGHT);

            if (s instanceof Coin) {
                Coin c = ((Coin) s);
                c.setListener(pf);

                ScaleTransition st = new ScaleTransition(Duration.millis(1500), c);
                st.setFromX(-1);
                st.setToX(1);

                st.setCycleCount(ScaleTransition.INDEFINITE);
                st.setInterpolator(Interpolator.LINEAR);
                st.setAutoReverse(true);
                st.play();

                TranslateTransition tr = new TranslateTransition(Duration.millis(1500), c);
                tr.setToY(c.getTranslateY() - 40);

                tr.setCycleCount(ScaleTransition.INDEFINITE);
                tr.setInterpolator(Interpolator.LINEAR);
                tr.setAutoReverse(true);
                tr.play();
            }

            if (s instanceof Munition) {
                ((Munition) s).setListener(pf);
            }

            if (s instanceof Enemy) {
                ((Enemy) s).setListener(pf);
            }
            

        });

        getSpriteContainer().addSprites(levelSprites);
        sprites.getChildren().addAll(levelSprites);

        createPlayer();

        createScene();

    }

    public Main getMain() {
        return main;
    }

    public PlatformerGameListener getGameListener() {
        return pf;
    }

    protected void createPlayer() {
        player = new Player(pf);
        player.setTranslateX(1000);
        player.setTranslateY(WINDOW_HEIGHT - FLOOR_HEIGHT - ENEMY_HEIGHT / 2);
        sprites.getChildren().add(player);
        getSpriteContainer().addSprite(player);
    }

    public void createScene() {
        scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setOnKeyPressed(player);
        scene.setOnKeyReleased(player);
        scene.setOnMouseClicked(ev -> {
            if (!paused && pf.getMunitionCount() > 0) {
                pf.addMunition(-1);

                Bullet b = new Bullet();
                if (player.isFaceLeft()) {
                    b.setDirectionLeft();
                }
                b.setTranslateX(player.getTranslateX());
                b.setTranslateY(player.getTranslateY());
                sprites.getChildren().add(b);
                getSpriteContainer().addSprite(b);
            }
        });
    }

    protected void createClouds(int cloudCount) {
        for (int i = 0; i < cloudCount; i++) {
            Cloud cloud = new Cloud();
            cloud.setTranslateX((1 + i) * 400);

            cloud.setTranslateY((RND.nextInt(WINDOW_HEIGHT - FLOOR_HEIGHT - ENEMY_HEIGHT - 50)));

            double scale = RND.nextDouble() + 0.5;
            cloud.setScaleX(scale);
            cloud.setScaleY(scale);

            sprites.getChildren().add(cloud);
            getSpriteContainer().addSprite(cloud);
        }
    }

    protected void createMountains(int mountainCount) {
        for (int i = 0; i < mountainCount; i++) {
            Mountain mountain = new Mountain(ParallaxLevel.FARR);
            mountain.setTranslateX((1 + i) * 200);

            int maxSize = 150;
            int minSize = 100;
            double scale = RND.nextDouble() * (maxSize - minSize) + minSize;
            mountain.setScaleX(scale);
            mountain.setScaleY(scale);

            mountain.setTranslateY(WINDOW_HEIGHT - mountain.getBoundsInParent().getHeight() / 3. * 2.);

            sprites.getChildren().add(mountain);
            parallaxSprites.add(mountain);
            //getSpriteContainer().addSprite(mountain);
        }
        for (int i = 0; i < mountainCount; i++) {
            Mountain mountain = new Mountain(ParallaxLevel.MIDDLE);
            mountain.setTranslateX(-350 + (1 + i) * 700);

            int maxSize = 200;
            int minSize = 170;
            double scale = RND.nextDouble() * (maxSize - minSize) + minSize;
            mountain.setScaleX(scale);
            mountain.setScaleY(scale);

            mountain.setTranslateY(WINDOW_HEIGHT - mountain.getBoundsInParent().getHeight() / 2);

            sprites.getChildren().add(mountain);
            parallaxSprites.add(mountain);
            //getSpriteContainer().addSprite(mountain);
        }
        for (int i = 0; i < mountainCount; i++) {
            Mountain mountain = new Mountain(ParallaxLevel.NEAR);
            mountain.setTranslateX((1 + i) * 700);

            int maxSize = 300;
            int minSize = 240;
            double scale = RND.nextDouble() * (maxSize - minSize) + minSize;
            mountain.setScaleX(scale);
            mountain.setScaleY(scale);

            mountain.setTranslateY(WINDOW_HEIGHT - mountain.getBoundsInParent().getHeight() / 2);

            sprites.getChildren().add(mountain);
            parallaxSprites.add(mountain);
            //getSpriteContainer().addSprite(mountain);
        }
    }

    protected void createHills(int cloudCount) {
        for (int i = 0; i < cloudCount; i++) {
            Hill hill = new Hill();
            hill.setTranslateX((1 + i) * 400);

            double scale = RND.nextDouble() * (150 - 100) + 100;
            hill.setScaleX(150);
            hill.setScaleY(100);

            hill.setTranslateY(WINDOW_HEIGHT - hill.getBoundsInParent().getHeight() / 2);

            sprites.getChildren().add(hill);
            parallaxSprites.add(hill);
            //getSpriteContainer().addSprite(hill);
        }
    }

    protected void createThrees(int cloudCount) {
        for (int i = 0; i < cloudCount; i++) {
            Three three = new Three();
            three.setTranslateX((1 + i) * 300);

            double scale = RND.nextDouble() * (40 - 25) + 25;
            three.setScaleX(scale);
            three.setScaleY(scale);

            three.setTranslateY(WINDOW_HEIGHT - three.getBoundsInParent().getHeight() / 4 * 3);

            sprites.getChildren().add(three);
            parallaxSprites.add(three);
            //etSpriteContainer().addSprite(three);
        }
    }

    @Deprecated
    protected void createEnemieis(int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            Enemy enemy = new Enemy(ENEMY_SPEED);
            enemy.setTranslateX((1 + i) * 800);
            enemy.setTranslateY(WINDOW_HEIGHT - FLOOR_HEIGHT - ENEMY_HEIGHT / 2);

            sprites.getChildren().add(enemy);
            getSpriteContainer().addSprite(enemy);
        }
    }

    protected void createBirds(int birdCount) {
        for (int i = 0; i < birdCount; i++) {
            Bird bird = new Bird();
            bird.setTranslateX((1 + i) * 1000);
            bird.setTranslateY((RND.nextInt(WINDOW_HEIGHT - 6 * FLOOR_HEIGHT)-2*FLOOR_HEIGHT));

            /*double scale = RND.nextDouble() + 0.5;*/
            bird.setScaleX(3);
            bird.setScaleY(3);

            sprites.getChildren().add(bird);
            getSpriteContainer().addSprite(bird);
        }
    }

    @Deprecated
    protected void createMunition(int munitionCount) {
        for (int i = 0; i < munitionCount; i++) {
            Munition mun = new Munition(pf);
            mun.setTranslateX((1 + i) * 700);
            mun.setTranslateY(WINDOW_HEIGHT - FLOOR_HEIGHT - mun.getBoundsInLocal().getHeight() / 2);

            sprites.getChildren().add(mun);
            getSpriteContainer().addSprite(mun);
        }
    }

    @Deprecated
    private void createCoins(int coinCount) {
        for (int i = 0; i < coinCount; i++) {
            Coin c = new Coin(pf);
            c.setTranslateX((1 + i) * 900);
            c.setTranslateY(WINDOW_HEIGHT - FLOOR_HEIGHT - c.getBoundsInLocal().getHeight() * 3);

            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), c);
            tt.setToY(WINDOW_HEIGHT - FLOOR_HEIGHT - c.getBoundsInLocal().getHeight() * 3 + 30);
            tt.setCycleCount(TranslateTransition.INDEFINITE);
            tt.setAutoReverse(true);
            tt.setInterpolator(Interpolator.LINEAR);
            tt.play();

            sprites.getChildren().add(c);
            getSpriteContainer().addSprite(c);
        }
    }

    @Override
    protected void updateSprites() {
        super.updateSprites();
        time += 1. / 60;

        int min = ((int) gameTime - (int) time) / 60;
        int sec = ((int) gameTime - (int) time) % 60;
        gv.setTime(min, sec);

        if (min <= 0 && sec <= 0) {
            pf.setPointsCount(0);
            stopGame();
        }

        camera.setTranslateX(WINDOW_WIDTH / 2 - player.getTranslateX());
        camera.setTranslateY(WINDOW_HEIGHT / 2 - player.getTranslateY());

        parallaxSprites.forEach(ps -> {
            ps.setCurrentPlayerX(player.getTranslateX());
            ps.update();
        });
    }
    
    private void doUpdateSprites() {
        super.updateSprites();
        time += 1. / 60;

        int min = ((int) gameTime - (int) time) / 60;
        int sec = ((int) gameTime - (int) time) % 60;
        gv.setTime(min, sec);

        if (min <= 0 && sec <= 0) {
            pf.setPointsCount(0);
            stopGame();
        }

        camera.setTranslateX(WINDOW_WIDTH / 2 - player.getTranslateX());
        camera.setTranslateY(WINDOW_HEIGHT / 2 - player.getTranslateY());

        parallaxSprites.forEach(ps -> {
            ps.setCurrentPlayerX(player.getTranslateX());
            ps.update();
        });
    }

    @Override
    protected void handleCollision(Sprite s1, Sprite s2) {
        super.handleCollision(s1, s2);

        if (s1.isSpriteDead()) {
            sprites.getChildren().remove(s1);
        }

        if (s2.isSpriteDead()) {
            sprites.getChildren().remove(s2);
        }
    }

    @Override
    public void stopGame() {
        super.stopGame();
        main.gameFinished(pf.getPointsCount());
    }

    public Scene getScene() {
        return scene;
    }

    public String getTITLE() {
        return TITLE;
    }

    public Group getRoot() {
        return root;
    }

}
