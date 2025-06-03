package tcg;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            playGame();

            System.out.print("\nGame over. Play again? (y/n): ");
            String again = scanner.next().trim().toLowerCase();
            if (!again.equals("y")) {
                System.out.println("Thanks for playing!");
                break;
            }
        }
    }

    static void playGame() {
        Player player = new Player();
        Player enemy = new Player();
        player.resetDeck();
        enemy.resetDeck();
        player.drawCards(3);
        enemy.drawCards(3);

        while (player.hp > 0 && enemy.hp > 0) {
            printState(player, enemy);
            playerTurn(player, enemy);
            if (enemy.hp <= 0 || player.hp <= 0) break;
            enemyTurn(enemy, player);
        }

        if (player.hp <= 0 && enemy.hp <= 0) {
            System.out.println("It's a draw!");
        } else if (player.hp <= 0) {
            System.out.println("You lost!");
        } else {
            System.out.println("You won!");
        }
    }

    static void printState(Player player, Player enemy) {
        System.out.println("==========================");
        System.out.println("Enemy HP: " + enemy.hp);
        System.out.println("Your HP: " + player.hp);
        System.out.println("Your Hand:");
        for (int i = 0; i < player.hand.size(); i++) {
            System.out.println((i + 1) + ". " + player.hand.get(i));
        }
    }

    static void playerTurn(Player player, Player enemy) {
        System.out.print("Choose a card to play [1-" + player.hand.size() + "]: ");
        try {
            int choice = Integer.parseInt(scanner.next()) - 1;
            if (choice >= 0 && choice < player.hand.size()) {
                Card card = player.hand.remove(choice);
                card.play(player, enemy);
                System.out.println("You played: " + card.name);
            } else {
                System.out.println("Invalid choice. Turn skipped.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Turn skipped.");
        }
    }

    static void enemyTurn(Player enemy, Player player) {
        if (!enemy.hand.isEmpty()) {
            Card card = enemy.hand.remove(0);
            card.play(enemy, player);
            System.out.println("Enemy played: " + card.name);
        }
    }
}
