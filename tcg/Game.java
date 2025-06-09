package tcg;

import java.util.*;
import tcg.Cards.*;

public class Game {
    public Player player;
    public Player enemy;
    private boolean gameOver = false;
    private int turnCount = 0; // Tambahkan ini

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
    // Implementation to reset the player's deck
}

public void drawCards(int n) {
    // Implementation to draw n cards from the deck
}

    // Getter untuk player dan enemy (tidak langsung mengembalikan objek agar tidak bocor)
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


    public List<Card> getEnemyHand() {
        return enemy.getHand(); 
    }

    public String getStatus() {
        return "Your HP: " + player.getHp() + " | Energy: " + player.getEnergy() +
               "\nEnemy HP: " + enemy.getHp();
    }

    public List<Card> getPlayerHand() {
        return player.getHand();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getResult() {
        if (!gameOver) return "Game is still running.";
        return player.getHp() <= 0 ? "You lost!" : "You won!";
    }
    public void startNewRound() {
    player.drawCards(1);
    enemy.drawCards(1);
    turnCount++;
    if (turnCount % 5 == 0) {
        player.addEnergy(5); // Tambah 5 energy setiap 5 turn
        enemy.addEnergy(5);
    }
    // Jangan reset energy ke 5 di sini!
}

    public String getWinner() {
        if (!isGameOver()) return "None";
        return player.getHp() <= 0 ? "Enemy" : "Player";
    }

    public String playerPlayCard(int index) {
        player.drawCards(1);
        player.resetEnergy();
        enemy.resetEnergy();

        List<Card> hand = player.getHand();
        if (index < 0 || index >= hand.size()) return "Invalid card index.";

        Card selected = hand.get(index);
        if (selected.cost > player.getEnergy()) {
            return "Not enough energy.";
        }

        String result = "Player plays: " + selected.name + "\n";
        player.playCard(index, enemy);

        if (enemy.getHp() <= 0) {
            gameOver = true;
            return result + "\nEnemy defeated!";
        }

        result += "\n" + enemyTurn();
        if (player.getHp() <= 0) {
            gameOver = true;
            result += "\nYou were defeated!";
        }

        return result;
    }

    private String enemyTurn() {
        enemy.drawCards(1);
        for (int i = 0; i < enemy.getHand().size(); i++) {
            Card card = enemy.getHand().get(i);
            if (card.cost <= enemy.getEnergy()) {
                String result = "Enemy plays: " + card.name + "\n";
                enemy.playCard(i, player);
                return result;
            }
        }
        return "Enemy skipped their turn.";
    }

    public Object Player() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Player'");
    }

    // Removed duplicate getEnemy() method to resolve compilation error.

    public void checkGameOver() {
        if (player.getHp() <= 0 || enemy.getHp() <= 0) {
            gameOver = true;
        }
    }

    public int getTurnCount() {
        return turnCount;
    }
}
