package tcg;

import java.util.*;

public class Game {
    public Player player;
    public Player enemy;
    private boolean gameOver = false;
    private int turnCount = 0;

    public Game() {
        player = new Player("Player");
        enemy = new Player("Enemy");
    }

    public void start() {
        player.resetDeck();
        enemy.resetDeck();
        player.drawCards(3);
        enemy.drawCards(3);
    }

    public Player getPlayer() {
        return this.player;
    }

    public Player getEnemy() {
        return this.enemy;
    }

    public void resetDeck() {
  
    }

    public void drawCards(int n) {
    }

    public int getPlayerHP() {
        return player.getHp();
    }

    public int getEnemyHP() {
        return enemy.getHp();
    }

    public int getPlayerEnergy() {
        return player.getEnergy();
    }

    public int getEnemyEnergy() {
        return enemy.getEnergy();
    }

    public List<Card> getPlayerHand() {
        return player.getHand();
    }

    public List<Card> getEnemyHand() {
        return enemy.getHand();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getResult() {
        if (!gameOver) return "Permainan masih berlangsung.";
        return player.getHp() <= 0 ? "Kamu kalah!" : "Kamu menang!";
    }

    public void startNewRound() {
        player.drawCards(1);
        enemy.drawCards(1);
        turnCount++;
        if (turnCount % 5 == 0) {
            player.addEnergy(5); 
            enemy.addEnergy(5);
        }

    }

    public String getWinner() {
        if (!isGameOver()) return "Belum ada";
        return player.getHp() <= 0 ? "Musuh" : "Pemain";
    }

    public void checkGameOver() {
        if (player.getHp() <= 0 || enemy.getHp() <= 0) {
            gameOver = true;
        }
    }

    public int getTurnCount() {
        return turnCount;
    }
    
}
