package tcg.Cards;

import tcg.Card;
import tcg.Player;

public class Paratrooper extends Card {
    public Paratrooper() {
        super("Paratrooper", 3, "Infantry");
    }

    @Override
    public void play(Player self, Player opponent) {
        int damage = 2;
        System.out.println(self.getName() + " menerjunkan Paratrooper!");
        System.out.println(">> Paratrooper menyerang dua kali!");

        for (int i = 0; i < 2; i++) {
            opponent.hp -= damage;
            System.out.println(">> Serangan ke-" + (i + 1) + " mengurangi " + damage + " HP lawan.");
        }
    }
}
