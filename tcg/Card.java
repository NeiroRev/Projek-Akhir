package tcg;

public abstract class Card {
    public String name;
    public int cost;
    public String type;

    public Card(String name, int cost, String type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public abstract void play(Player self, Player opponent);

    @Override
    public String toString() {
        return name + " (Biaya: " + cost + ", Type: " + type + ")";
    }
}