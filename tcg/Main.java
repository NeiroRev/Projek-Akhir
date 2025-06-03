package tcg;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            playGame();

            System.out.print("\nGame over. Play again? (y/n): ");
            String again = scanner.next().trim().toLowerCase();
            if (!again.equals("y")) break;
        }

        System.out.println("Thank you for playing!");
    }

    static void playGame() {
        Player player = new Player();
        Player enemy = new Player();
        player.resetDeck();
        enemy.resetDeck();
        player.drawCards(3);
        enemy.drawCards(3);

        while (player.hp > 0 && enemy.hp > 0) {
            player.resetEnergy();
            enemy.resetEnergy();
            System.out.println("\n--- New Turn ---");
            printState(player, enemy);
            playerTurn(player, enemy);
            if (enemy.hp <= 0 || player.hp <= 0) break;
            enemyTurn(enemy, player);
        }

        System.out.println(player.hp <= 0 ? "You lost!" : "You won!");
    }

    static void printState(Player p, Player e) {
        System.out.println("Your HP: " + p.hp + " | Energy: " + p.energy);
        System.out.println("Enemy HP: " + e.hp);
        System.out.println("Your Hand:");
        for (int i = 0; i < p.hand.size(); i++) {
            System.out.println((i + 1) + ". " + p.hand.get(i));
        }
    }

    static void playerTurn(Player p, Player e) {
        System.out.print("Choose a card to play [1-" + p.hand.size() + "] or 0 to skip: ");
        try {
            int choice = Integer.parseInt(scanner.next()) - 1;
            if (choice == -1) {
                System.out.println("Turn skipped.");
                return;
            }
            if (choice >= 0 && choice < p.hand.size()) {
                Card card = p.hand.get(choice);
                if (card.cost <= p.energy) {
                    p.hand.remove(choice);
                    p.energy -= card.cost;
                    card.play(p, e);
                    System.out.println("Played: " + card.name);
                } else {
                    System.out.println("Not enough energy!");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (Exception e1) {
            System.out.println("Invalid input.");
        }
    }

    static void enemyTurn(Player e, Player p) {
        for (Card c : new ArrayList<>(e.hand)) {
            if (c.cost <= e.energy) {
                e.hand.remove(c);
                e.energy -= c.cost;
                c.play(e, p);
                System.out.println("Enemy played: " + c.name);
                break;
            }
        }
    }
}
