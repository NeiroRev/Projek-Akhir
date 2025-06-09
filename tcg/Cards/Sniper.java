package tcg.Cards;

import tcg.Card;
import tcg.Player;

public class Sniper extends Card {
    private int damage = 3;

    public Sniper() {
        super("Sniper", 3, "Ground");
    }

    @Override
    public void play(Player self, Player opponent) {
        opponent.hp -= damage;
    }
}
