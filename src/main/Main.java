package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import interfejs.MenuView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 *
 * @author mile
 */
public class Main extends Application implements MenuView.MenuListener {

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
        primaryStage.show();
        primaryStage.setResizable(false);

        mv = new MenuView(this);
        mv.setTranslateX(pg.getScene().getWidth() / 2);
        mv.setTranslateY(pg.getScene().getHeight() / 2);
        pg.getRoot().getChildren().add(mv);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onPlay() {
        pg.getRoot().getChildren().remove(mv);

        if (turn > 0) {
            pg = new PlatformerGame(this);

            primaryStage.setTitle(pg.getTITLE());
            primaryStage.setScene(pg.getScene());
            primaryStage.show();
            primaryStage.setResizable(false);

            mv = new MenuView(this);
            mv.setTranslateX(pg.getScene().getWidth() / 2);
            mv.setTranslateY(pg.getScene().getHeight() / 2);
        } else {
            turn++;
        }

        pg.startGame();
    }

    @Override
    public void onExit() {
        Platform.exit();
    }

    public void gameFinished(int result) {
        if(result>0){
            mv.setMessage("You won " + result + " points");
        } else {
           mv.setMessage("You lost :-(");
        }
        
        pg.getRoot().getChildren().add(mv);
    }

}
