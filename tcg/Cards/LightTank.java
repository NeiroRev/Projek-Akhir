package tcg.Cards;

import tcg.Card;
import tcg.Player;

public class LightTank extends Card {
    private int damage = 3; 

    public LightTank() {
        super("Light Tank", 2, "Vehicle");
    }

    @Override
    public void play(Player self, Player opponent) {
        opponent.hp -= damage;
    }
}