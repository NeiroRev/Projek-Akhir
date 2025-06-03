package tcg;

public class Game {
    public Player player = new Player();
    public Player enemy = new Player();
    public String log = "";

    public Game() {
        player.resetDeck();
        enemy.resetDeck();
        player.drawCards(3);
        enemy.drawCards(3);
    }

    public void playCard(int index) {
        if (index >= 0 && index < player.hand.size()) {
            Card selected = player.hand.get(index);
            selected.play(player, enemy);
            log = "You played: " + selected.name;
            player.hand.remove(index);

            enemyTurn();
        }
    }

    public void enemyTurn() {
        if (!enemy.hand.isEmpty()) {
            Card aiCard = enemy.hand.remove(0);
            aiCard.play(enemy, player);
            log += "\nEnemy played: " + aiCard.name;
        }
    }

    public boolean isGameOver() {
        return player.hp <= 0 || enemy.hp <= 0;
    }
}
