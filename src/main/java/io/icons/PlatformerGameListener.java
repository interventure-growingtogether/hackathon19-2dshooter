package io.icons;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import io.icons.interfejs.GameView;
import io.icons.interfejs.HintView;
import io.icons.interfejs.QuestionView;
import io.icons.repository.Question;
import io.icons.repository.QuestionRepository;
import io.icons.sprites.Coin.CoinPickupListener;
import io.icons.sprites.Enemy.EnemyHitListener;
import io.icons.sprites.Player.PlayerHitListener;
import javafx.application.Platform;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

import static io.icons.sprites.Munition.MunitionPickupListener;

/**
 * @author Marko
 */
public class PlatformerGameListener
    implements MunitionPickupListener, CoinPickupListener, PlayerHitListener, EnemyHitListener {

    private final Deque<Question> questions;
    private GameView gv;
    private PlatformerGame ge;

    private int munitionCount, livesCount = 3, pointsCount;

    public PlatformerGameListener(GameView gv, PlatformerGame ge) {
        this.gv = gv;
        this.ge = ge;

        this.questions = new LinkedBlockingDeque<>(
            QuestionRepository.buildRepository().getQuestions()
        );
    }

    @Override
    public void onMunitionPickup(int count) {
        munitionCount += count;
        gv.setMunition(munitionCount);
    }

    @Override
    public void onCoinPickup(int amount) {
        pointsCount += amount;
        gv.setPoints(pointsCount);
        HintView.showIn(ge);
    }

    @Override
    public void onPlayerHit() {
        livesCount--;
        gv.setLives(livesCount);
    }

    @Override
    public void onPlayerDie() {
        pointsCount = 0;
        ge.stopGame();
    }

    public void addMunition(int count) {
        onMunitionPickup(count);
    }

    public int getMunitionCount() {
        return munitionCount;
    }

    public int getLivesCount() {
        return livesCount;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    @Override
    public void onEnemyHit() {
        pointsCount += 1;
        gv.setPoints(pointsCount);
    }

    @Override
    public void onEnemyDie() {
        pointsCount += 5;
        gv.setPoints(pointsCount);
        HintView.showIn(ge);
    }

    @Override
    public void onPlayerWin() {
        ge.setQuizStarted(true);
        QuestionView.showIn(ge, questions.poll(), this::showNextQuestionOrEndGame);
    }

    private void showNextQuestionOrEndGame() {
        Platform.runLater(() -> {
            if (questions.isEmpty()) {
                ge.stopGame();
            } else {
                QuestionView.showIn(ge, questions.poll(), this::showNextQuestionOrEndGame);
            }
        });
    }

    void setPointsCount(int i) {
        pointsCount = i;
    }

    public void incrementPointsBy(final int increment) {
        pointsCount += increment;
    }
}
