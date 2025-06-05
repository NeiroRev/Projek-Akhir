package tcg.Cards;
import tcg.Card;
import tcg.Player;

public class LightTank extends Card {
    public LightTank() {
        super("Light Tank", 2, "Vehicle");
    }

    @Override
    public void play(Player self, Player opponent) {
        opponent.hp -= 3;
        System.out.println(self.getName() + " menyerang dengan Light Tank! (-3 HP lawan)");
    }
}