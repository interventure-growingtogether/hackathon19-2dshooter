/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gameengine.GameEngine;
import interfejs.GameView;
import sprites.Coin;
import sprites.Enemy;
import sprites.Munition;
import sprites.Player;

/**
 *
 * @author Marko
 */
public class PlatformerGameListener implements Munition.MunitionPickupListener, Coin.CoinPickupListener, Player.PlayerHitListener, Enemy.EnemyHitListener {

    private GameView gv;
    private GameEngine ge;

    private int munitionCount, livesCount = 3, pointsCount;

    public PlatformerGameListener(GameView gv, GameEngine ge) {
        this.gv = gv;
        this.ge = ge;
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
    }

    @Override
    public void onPlayerWin() {
        ge.stopGame();
    }

    void setPointsCount(int i) {
        pointsCount = i;
    }

}
