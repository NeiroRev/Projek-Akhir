package tcg.Cards;

import javax.swing.JOptionPane;

import tcg.Card;
import tcg.Player;
import tcg.interfaces.CardEffect;

public class Paratrooper extends Card implements CardEffect {
    private int damage = 2;

    public Paratrooper() {
        super("Paratrooper", 2, "Ground");
    }

    @Override
    public void play(Player self, Player opponent) {
        apply(self, opponent);
    }

    @Override
    public void apply(Player self, Player opponent) {
        opponent.hp -= damage;
        opponent.hp -= damage;
        JOptionPane.showMessageDialog(null,
            "Paratrooper menyerang dua kali!\nTotal damage: " + (damage * 2),
            "Aksi Paratrooper", JOptionPane.INFORMATION_MESSAGE
        );
    }
}
