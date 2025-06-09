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

            if (player.getHp() <= 0) break;
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
        player.drawCards(1); // 🔥 Tambahkan ini
        List<Card> hand = player.getHand();
        while (true) {
            System.out.print("Pilih satu kartu [1-" + hand.size() + "] atau pilih 0 untuk skip: ");
            String input = scanner.nextLine();
            System.out.println("=======================================");
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid salah bro. Pilih angkanya itu!");
                continue;
            }

            if (choice == 0) {
                System.out.println("Kamu melewati giliranmu.");
                return;
            }

            if (choice < 1 || choice > hand.size()) {
                System.out.println("Invalid bro. Coba lagi!");
                continue;
            }

            Card selected = hand.get(choice - 1);
            if (selected.cost > player.getEnergy()) {
                System.out.println("Wah energimu kurang bruh.");
                continue;
            }

            player.playCard(choice - 1, enemy);
            return;
        }
    }

    private void enemyTurn() {
    enemy.drawCards(1); // 🔥 Tambahkan ini
    for (int i = 0; i < enemy.getHand().size(); i++) { 
            Card card = enemy.getHand().get(i);
            if (card.cost <= enemy.getEnergy()) {
                System.out.println("Enemy memainkan kartu: " + card.name);
                enemy.playCard(i, player);
                return;
            }
        }

        System.out.println("Musuh melewati gilirannya.");
    }
}
