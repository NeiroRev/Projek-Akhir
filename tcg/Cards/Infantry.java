package tcg.Cards;

import tcg.Card;
import tcg.Player;

public class Infantry extends Card {
    private int damage = 2;

    public Infantry() {
        super("Infantry", 1, "Ground");
    }

    @Override
    public void play(Player self, Player opponent) {
        opponent.hp -= damage;
    }
}