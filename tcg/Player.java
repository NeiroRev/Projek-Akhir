package tcg;

import java.util.*;
import tcg.Cards.*;

public class Player {
    private String name;
    public int hp;
    private int energy;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hp = 20;
        this.energy = 5;
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getEnergy() {
        return energy;
    }

    public void resetEnergy() {
        this.energy = 5;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void resetDeck() {
    deck.clear();
    hand.clear();

    for (int i = 0; i < 4; i++) {
        deck.add(new Infantry());      // 4 Infantry
        deck.add(new LightTank());     // 4 LightTank
        deck.add(new Paratrooper());   // 4 Paratrooper
        deck.add(new Medic());         // 4 Medic
        deck.add(new MediumTank());    // 4 MediumTank 
        deck.add(new Sniper());        // 4 Sniper 
    }

    Collections.shuffle(deck);
    }

    public void drawCards(int count) {
        for (int i = 0; i < count && !deck.isEmpty(); i++) {
            hand.add(deck.remove(0));
        }
    }

    public void playCard(int index, Player opponent) {
        if (index < 0 || index >= hand.size()) return;
        Card card = hand.remove(index);
        this.energy -= card.cost; 
        card.play(this, opponent);
    }

    public void takeDamage(int amount) {
        hp -= amount;
        if (hp < 0) hp = 0;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > 20) hp = 20;
    }

    public void addEnergy(int amount) {
        this.energy += amount;
    }
}