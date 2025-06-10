package tcg.Cards;

import tcg.Card;
import tcg.Player;

public class MediumTank extends Card {
    private int damage = 4;

    public MediumTank() {
        super("Medium Tank", 4, "Vehicle");
    }

    @Override
    public void play(Player self, Player opponent) {
        opponent.hp -= damage;
    }
}