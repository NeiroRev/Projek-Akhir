package tcg;

import java.util.*;
import Cards.Card;
import Cards.Infantry;
import Cards.Tank;
import Cards.Medic;

public class Player {
    public int hp = 20;
    public int energy = 5;
    public ArrayList<Card> deck = new ArrayList<>();
    public ArrayList<Card> hand = new ArrayList<>();

    public void drawCards(int count) {
        for (int i = 0; i < count && !deck.isEmpty(); i++) {
            hand.add(deck.remove(0));
        }
    }

    public void resetDeck() {
        deck.clear();
        deck.add(new Infantry());
        deck.add(new Infantry());
        deck.add(new Tank());
        deck.add(new Medic());
        Collections.shuffle(deck);
    }

    public void resetEnergy() {
        energy = 5;
    }
}
