package tcg.Cards;

import tcg.Card;
import tcg.Player;

public class Paratrooper extends Card {
    private int damage = 2;

    public Paratrooper() {
        super("Paratrooper", 2, "Ground");
    }

    @Override
    public void play(Player self, Player opponent) {
        opponent.hp -= damage;
    }
}
