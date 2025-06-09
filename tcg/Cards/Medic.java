package tcg.Cards;

import tcg.Card;
import tcg.Player;

public class Medic extends Card {
    private int heal = 3;

    public Medic() {
        super("Medic", 2, "Support");
    }

    @Override
    public void play(Player self, Player opponent) {
        self.heal(heal);
    }
}
