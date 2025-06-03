package tcg;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    public int hp = 20;
    public ArrayList<Card> deck = new ArrayList<>();
    public ArrayList<Card> hand = new ArrayList<>();

    public void drawCards(int count) {
        for (int i = 0; i < count && !deck.isEmpty(); i++) {
            hand.add(deck.remove(0));
        }
    }

    public void resetDeck() {
        deck.clear();
        deck.add(new Card("Fireball", "attack", 5));
        deck.add(new Card("Heal", "heal", 3));
        deck.add(new Card("Shield", "heal", 2));
        deck.add(new Card("Strike", "attack", 4));
        deck.add(new Card("Blast", "attack", 6));
        Collections.shuffle(deck);
    }
}
