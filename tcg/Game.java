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
        // Implementasi reset deck jika diperlukan
    }

    public void drawCards(int n) {
        // Implementasi draw kartu jika diperlukan
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
            player.addEnergy(5); // Tambah 5 energy setiap 5 turn
            enemy.addEnergy(5);
        }
        // Tidak reset energy di sini!
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

    // Fungsi untuk main kartu oleh player (jika ingin dipakai di CLI)
    public String playerPlayCard(int index) {
        player.drawCards(1);
        // player.resetEnergy(); // Jangan reset energy di sini jika energy tidak direset per turn
        // enemy.resetEnergy();

        List<Card> hand = player.getHand();
        if (index < 0 || index >= hand.size()) return "Indeks kartu tidak valid.";

        Card selected = hand.get(index);
        if (selected.cost > player.getEnergy()) {
            return "Energi tidak cukup.";
        }

        String result = "Pemain memainkan: " + selected.name + "\n";
        player.playCard(index, enemy);

        if (enemy.getHp() <= 0) {
            gameOver = true;
            return result + "\nMusuh dikalahkan!";
        }

        result += "\n" + enemyTurn();
        if (player.getHp() <= 0) {
            gameOver = true;
            result += "\nKamu dikalahkan!";
        }

        return result;
    }

    // Fungsi giliran musuh (jika ingin dipakai di CLI)
    private String enemyTurn() {
        enemy.drawCards(1);
        for (int i = 0; i < enemy.getHand().size(); i++) {
            Card card = enemy.getHand().get(i);
            if (card.cost <= enemy.getEnergy()) {
                String result = "Musuh memainkan: " + card.name + "\n";
                enemy.playCard(i, player);
                return result;
            }
        }
        return "Musuh melewatkan giliran.";
    }
}
