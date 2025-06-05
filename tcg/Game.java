package tcg;

import java.util.*;
import tcg.Cards.*;

public class Game {
    private Player player;
    private Player enemy;
    private Scanner scanner;

    public Game() {
        player = new Player("Player");
        enemy = new Player("Enemy");
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("==== WW2 TCG ====");
        player.resetDeck();
        enemy.resetDeck();
        player.drawCards(3);
        enemy.drawCards(3);

        while (player.getHp() > 0 && enemy.getHp() > 0) {
            player.resetEnergy();
            enemy.resetEnergy();

            System.out.println("\n--- New Turn ---");
            printStatus();
            playerTurn();
            if (enemy.getHp() <= 0) break;
            enemyTurn();
        }

        System.out.println("\nGame Over!");
        if (player.getHp() <= 0) System.out.println("You lost!");
        else System.out.println("You won!");
    }

    private void printStatus() {
        System.out.println("Your HP: " + player.getHp() + " | Energy: " + player.getEnergy());
        System.out.println("Enemy HP: " + enemy.getHp());
        System.out.println("Your Hand:");
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
    }

    private void playerTurn() {
        System.out.print("Choose a card to play [1-" + player.getHand().size() + "] or 0 to skip: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine()) - 1;
            if (choice == -1) {
                System.out.println("Turn skipped.");
                return;
            }
            if (choice >= 0 && choice < player.getHand().size()) {
                Card card = player.getHand().get(choice);
                if (card.cost <= player.getEnergy()) {
                    player.playCard(choice, enemy);
                } else {
                    System.out.println("Not enough energy!");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private void enemyTurn() {
        for (int i = 0; i < enemy.getHand().size(); i++) {
            Card card = enemy.getHand().get(i);
            if (card.cost <= enemy.getEnergy()) {
                System.out.println("Enemy plays: " + card.name);
                enemy.playCard(i, player);
                break;
            }
        }
    }
}
