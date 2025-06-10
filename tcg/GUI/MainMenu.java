package tcg.GUI;

import javax.swing.*;
import java.awt.*;

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
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton startButton = new JButton("Mulai Permainan");
        JButton cardListButton = new JButton("Lihat Daftar Kartu");
        JButton exitButton = new JButton("Keluar");

        startButton.setBackground(oliveDrab.darker());
        startButton.setForeground(Color.WHITE);
        cardListButton.setBackground(oliveDrab.darker());
        cardListButton.setForeground(Color.WHITE);
        exitButton.setBackground(oliveDrab.darker());
        exitButton.setForeground(Color.WHITE);

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardListButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(cardListButton);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalGlue());

        add(buttonPanel, BorderLayout.CENTER);

        startButton.addActionListener(e -> {
            dispose(); 
            new GameGUI(); 
        });

        cardListButton.addActionListener(e -> {
            new CardList(this); 
        });
        
        exitButton.addActionListener(e -> System.exit(0));
        
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }   
}

