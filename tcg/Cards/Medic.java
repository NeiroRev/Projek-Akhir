package tcg.Cards;

import tcg.Card;
import tcg.Player;
import tcg.interfaces.CardEffect;

public class Medic extends Card implements CardEffect {
    private int heal = 3;

    public Medic() {
        super("Medic", 2, "Support");
    }

    @Override
    public void play(Player self, Player opponent) {
        apply(self, opponent);
    }

    @Override
    public void apply(Player self, Player opponent) {
        self.heal(heal);
    }
}
