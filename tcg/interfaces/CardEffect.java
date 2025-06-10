package tcg.interfaces;

import tcg.Player;

public interface CardEffect {
    void apply(Player self, Player opponent);
}