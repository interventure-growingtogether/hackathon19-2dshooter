/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import interfejs.MenuView;
import interfejs.TextOptionClickListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author mile
 */
public class Main extends Application implements TextOptionClickListener {

    PlatformerGame pg;
    MenuView mv;
    int turn = 0;
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        pg = new PlatformerGame(this);

        primaryStage.setTitle(pg.getTITLE());
        primaryStage.setScene(pg.getScene());
        primaryStage.setMinHeight(PlatformerGame.WINDOW_HEIGHT);
        primaryStage.setMinWidth(PlatformerGame.WINDOW_WIDTH);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> onExit());

        mv = new MenuView(pg);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



    public void onPlay() {
        pg.getRoot().getChildren().remove(mv);

        if (turn > 0) {
            pg = new PlatformerGame(this);

            primaryStage.setTitle(pg.getTITLE());
            primaryStage.setScene(pg.getScene());
            primaryStage.show();
            primaryStage.setResizable(false);

            mv = new MenuView(pg);
            mv.setTranslateX(pg.getScene().getWidth() / 2);
            mv.setTranslateY(pg.getScene().getHeight() / 2);
        } else {
            turn++;
        }

        pg.startGame();
    }

    public void onExit() {
        System.exit(0);
    }

    public void gameFinished(int result) {
        if (result > 0) {
            mv.setMessage("You won " + result + " points");
        } else {
            mv.setMessage("You lost :-(");
        }

        pg.getRoot().getChildren().add(mv);
    }

    @Override public void onSelection(final Text textOption) {
        switch (textOption.getText()) {
            case "Play platformer":
                onPlay();
                break;
            case "Exit platformer":
            default:
                onExit();
                break;
        }
    }
}
