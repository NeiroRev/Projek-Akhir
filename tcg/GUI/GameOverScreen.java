package tcg.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOverScreen extends JFrame {
    public GameOverScreen(String winner) {
        setTitle("Game Over");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Game Over! Winner: " + winner, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.CENTER);

        JButton exitButton = new JButton("Keluar");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}