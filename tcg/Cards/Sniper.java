package tcg.Cards;

import tcg.Card;
import tcg.Player;

public class Sniper extends Card {
    public Sniper() {
        super("Sniper", 2, "Infantry");
    }

    
    public void effect(Player owner, Player enemy) {
        System.out.println(owner.getName() + " menggunakan Sniper! Menyerang musuh secara akurat! (-3 HP lawan)");
        enemy.setHp(enemy.getHp() - 3);
    }

    @Override
    public void play(Player owner, Player enemy) {
        effect(owner, enemy);
    }
}
