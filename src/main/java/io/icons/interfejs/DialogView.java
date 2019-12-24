package io.icons.interfejs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import io.icons.PlatformerGame;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import io.icons.sprites.playeranimations.SpriteSheet;

public class DialogView extends Group {
    public static final ScheduledExecutorService POOL;

    static {
        POOL = Executors.newScheduledThreadPool(4);
        Runtime.getRuntime().addShutdownHook(new Thread(POOL::shutdownNow));
    }

    protected final PlatformerGame pg;
    protected volatile boolean closed;

    public DialogView(final PlatformerGame pg) {
        this.pg = pg;

        final ImageView iv = new ImageView(SpriteSheet.getBgImage());
        iv.setViewport(new Rectangle2D(0, 0, 700, 366));
        iv.setTranslateX(-700 / 2);
        iv.setTranslateY(-366 / 2);
        iv.setOpacity(0.99999);

        getChildren().add(iv);
        setTranslateX(0.5);
        setTranslateY(0.5);

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
