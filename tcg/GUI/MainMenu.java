package tcg.GUI;

import tcg.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("WW2 TCG - Menu Utama");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("WW2 TCG", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        Color oliveDrab = new Color(107, 142, 35);
        getContentPane().setBackground(oliveDrab);

        title.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(oliveDrab);

        JButton startButton = new JButton("Mulai Permainan");
        JButton cardListButton = new JButton("Lihat Daftar Kartu");
        JButton exitButton = new JButton("Keluar");

        startButton.setBackground(oliveDrab.darker());
        startButton.setForeground(Color.WHITE);
        cardListButton.setBackground(oliveDrab.darker());
        cardListButton.setForeground(Color.WHITE);
        exitButton.setBackground(oliveDrab.darker());
        exitButton.setForeground(Color.WHITE);

        buttonPanel.add(startButton);
        buttonPanel.add(cardListButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Aksi tombol
        startButton.addActionListener(e -> {
            dispose(); // tutup menu utama
            new GameGUI(); // buka GUI permainan
        });

        cardListButton.addActionListener(e -> {
            new CardListDialog(this); // tampilkan daftar kartu
        });

        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }   
}

