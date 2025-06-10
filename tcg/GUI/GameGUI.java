package tcg.GUI;

import javax.swing.*;
import java.awt.*;
import tcg.*;

public class GameGUI extends JFrame {
    private Game game;
    private JLabel playerStatus, enemyStatus;
    private JPanel handPanel;
    private JButton skipButton;

    public GameGUI() {
        game = new Game();
        game.getPlayer().resetDeck();
        game.getEnemy().resetDeck();
        game.getPlayer().drawCards(3);
        game.getEnemy().drawCards(3);

        setTitle("WW2 TCG GAME");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color oliveDrab = new Color(107, 142, 35);
        getContentPane().setBackground(oliveDrab);

        playerStatus = new JLabel();
        enemyStatus = new JLabel();
        updateStatusLabels();

        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.setBackground(oliveDrab);
        playerStatus.setForeground(Color.WHITE);
        enemyStatus.setForeground(Color.WHITE);
        statusPanel.add(playerStatus);
        statusPanel.add(enemyStatus);
        add(statusPanel, BorderLayout.NORTH);

        handPanel = new JPanel();
        handPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); 
        handPanel.setBackground(oliveDrab);
        add(handPanel, BorderLayout.CENTER);
        refreshHand();

        skipButton = new JButton("Skip Turn");
        skipButton.setBackground(oliveDrab.darker());
        skipButton.setForeground(Color.WHITE);
        skipButton.addActionListener(e -> {
            int beforeEnergy = game.getPlayerEnergy();
            int beforeTurn = game.getTurnCount(); 

            enemyTurn();
            checkGameEnd();
            updateStatusLabels();
            refreshHand();

            int afterEnergy = game.getPlayerEnergy();
            int afterTurn = game.getTurnCount();

            if (afterTurn % 5 == 0 && afterEnergy > beforeEnergy) {
                JOptionPane.showMessageDialog(this,
                    "Energy kamu bertambah 5!\nEnergy sekarang: " + afterEnergy,
                    "Bonus Energy", JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(oliveDrab);
        bottomPanel.add(skipButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateStatusLabels() {
        playerStatus.setText("Player HP: " + game.getPlayerHP() + " | Energy: " + game.getPlayerEnergy());
        enemyStatus.setText("Enemy HP: " + game.getEnemyHP() + " | Energy: " + game.getEnemyEnergy());
    }

    private void refreshHand() {
        handPanel.removeAll();

        for (int i = 0; i < game.getPlayerHand().size(); i++) {
            Card card = game.getPlayerHand().get(i);
            JButton cardButton = new JButton("<html><b>" + card.name + "</b><br/>Cost: " + card.cost + "<br/>Type: " + card.type + "</html>");
            cardButton.setPreferredSize(new Dimension(130, 70));
            cardButton.setBackground(new Color(154, 205, 50)); 
            cardButton.setForeground(Color.BLACK);
            cardButton.setFont(new Font("Arial", Font.BOLD, 14));
            cardButton.setFocusPainted(false);
            cardButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(85, 107, 47), 2, true), 
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));

            int index = i;

            cardButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    cardButton.setBackground(new Color(107, 142, 35));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    cardButton.setBackground(new Color(154, 205, 50));
                }
            });

            cardButton.addActionListener(e -> {
                if (card.cost > game.getPlayerEnergy()) {
                    JOptionPane.showMessageDialog(this, "Kamu tidak punya cukup energi untuk kartu ini!");
                    return;
                }

                int beforeEnemyHP = game.getEnemyHP();
                int beforePlayerHP = game.getPlayerHP();

                game.player.playCard(index, game.enemy);

                int afterEnemyHP = game.getEnemyHP();
                int afterPlayerHP = game.getPlayerHP();

                int damage = beforeEnemyHP - afterEnemyHP;
                if (damage < 0) damage = 0; 

                JOptionPane.showMessageDialog(this,
                    "Kamu menyerang!\n" +
                    "Damage ke musuh: " + damage +
                    "\nHP Musuh: " + afterEnemyHP +
                    "\nHP Kamu: " + afterPlayerHP,
                    "Serangan Player", JOptionPane.INFORMATION_MESSAGE
                );

                game.checkGameOver();
                if (!game.isGameOver()) {
                    enemyTurn();
                    game.checkGameOver();
                }
                checkGameEnd();
                updateStatusLabels();
                refreshHand();
            });

            handPanel.add(cardButton);
        }

        revalidate();
        repaint();
    }

    private void enemyTurn() {
        for (int i = 0; i < game.getEnemyHand().size(); i++) {
            Card card = game.getEnemyHand().get(i);
            if (card.cost <= game.getEnemyEnergy()) {
                int beforePlayerHP = game.getPlayerHP();
                int beforeEnemyHP = game.getEnemyHP();

                game.enemy.playCard(i, game.player);

                int afterPlayerHP = game.getPlayerHP();
                int afterEnemyHP = game.getEnemyHP();

                int damage = beforePlayerHP - afterPlayerHP;
                if (damage < 0) damage = 0;

                JOptionPane.showMessageDialog(this,
                    "Musuh menyerang dengan: " + card.name +
                    "\nDamage ke kamu: " + damage +
                    "\nHP Kamu: " + afterPlayerHP +
                    "\nHP Musuh: " + afterEnemyHP,
                    "Serangan Musuh", JOptionPane.INFORMATION_MESSAGE
                );
                break;
            }
        }
        game.startNewRound();
    }

    private void checkGameEnd() {
        if (game.isGameOver()) {
            String winner = game.getWinner();
            new GameOverScreen(winner);
            dispose(); 
        }
    }
}
