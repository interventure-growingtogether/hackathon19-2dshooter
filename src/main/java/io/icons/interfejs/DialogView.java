package io.icons.interfejs;

import io.icons.PlatformerGame;
import io.icons.sprites.playeranimations.SpriteSheet;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DialogView extends Group {
    public static final ScheduledExecutorService POOL;
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;

    static {
        POOL = Executors.newScheduledThreadPool(4);
        Runtime.getRuntime().addShutdownHook(new Thread(POOL::shutdownNow));
    }

    protected final PlatformerGame pg;
    protected volatile boolean closed;

    public DialogView(final PlatformerGame pg) {
        this.pg = pg;

        final ImageView iv = new ImageView(SpriteSheet.getBgImage());
        iv.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
        iv.setTranslateX(-WIDTH / 2);
        iv.setTranslateY(-HEIGHT / 2);
        iv.setOpacity(0.99999);

        setTranslateX(0.5);
        setTranslateY(0.5);

        getChildren().add(iv);

        setTranslateX(pg.getScene().getWidth() / 2);
        setTranslateY(pg.getScene().getHeight() / 2);
        Platform.runLater(() -> pg.getRoot().getChildren().add(this));
    }

    protected void closeDialog() {
        if (!closed) {
            closed = true;
            Platform.runLater(() -> pg.getRoot().getChildren().remove(this));
            additionalCloseOperations();
        }
    }

    protected void additionalCloseOperations() {

    }
}
