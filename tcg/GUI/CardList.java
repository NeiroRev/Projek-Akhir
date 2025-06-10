package tcg.GUI;

import javax.swing.*;
import java.util.*;

import tcg.Card;
import tcg.Cards.*;

public class CardList extends JDialog {
    public CardList(JFrame parent) {
        super(parent, "Daftar Kartu", true);
        setSize(400, 400);
        setLocationRelativeTo(parent);

        java.util.List<Card> cards = Arrays.asList(
            new Infantry(),
            new LightTank(),
            new Paratrooper(),
            new Medic(),
            new MediumTank(),
            new Sniper()
        );

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Card card : cards) {
            StringBuilder detail = new StringBuilder();
            detail.append("<html><b>").append(card.name).append("</b><br>")
                  .append("Cost / Biaya : ").append(card.cost).append("<br>")
                  .append("Type : ").append(card.type);

            if (card instanceof Medic) {
                try {
                    java.lang.reflect.Field healField = card.getClass().getDeclaredField("Heal");
                    healField.setAccessible(true);
                    detail.append("<br>Heal: ").append(healField.get(card));
                } catch (Exception ignored) {}
            } else {
                try {
                    java.lang.reflect.Field damageField = card.getClass().getDeclaredField("damage");
                    damageField.setAccessible(true);
                    detail.append("<br>Damage: ").append(damageField.get(card));
                } catch (Exception ignored) {}
            }

            detail.append("</html>");

            JLabel label = new JLabel(detail.toString());
            label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }
}