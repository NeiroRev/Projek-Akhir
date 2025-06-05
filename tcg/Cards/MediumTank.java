package tcg.Cards;

import tcg.Card;
import tcg.Player;

public class MediumTank extends Card {
    public MediumTank() {
        super("Medium Tank", 4, "Vehicle");
    }

    public void effect(Player owner, Player enemy) {
        System.out.println(owner.getName() + " menyerang dengan Medium Tank! (-5 HP lawan)");
        enemy.setHp(enemy.getHp() - 5);
    }

    @Override
    public void play(Player owner, Player enemy) {
        // You can define what happens when the card is played.
        // For now, let's just call the effect method.
        effect(owner, enemy);
    }
}
