package tcg.GUI;

import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JFrame {
    public GameOverScreen(String pemenang) {
        setTitle("Permainan Selesai");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Permainan Selesai! Pemenang: " + pemenang, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(107, 142, 35));

        JButton restartButton = new JButton("Main Lagi");
        JButton exitButton = new JButton("Keluar");

        restartButton.setBackground(new Color(107, 142, 35).darker());
        restartButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(107, 142, 35).darker());
        exitButton.setForeground(Color.WHITE);

        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        restartButton.addActionListener(e -> {
            dispose();
            new MainMenu(); // Kembali ke menu utama, atau bisa langsung new GameGUI() jika ingin langsung main lagi
        });

        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}