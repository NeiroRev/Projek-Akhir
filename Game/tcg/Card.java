package tcg;

public class Card {
    public String name;
    public String type; // "attack", "heal"
    public int value;

    public Card(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public void play(Player self, Player opponent) {
        switch (type) {
            case "attack":
                opponent.hp -= value;
                break;
            case "heal":
                self.hp += value;
                break;
        }
    }

    @Override
    public String toString() {
        return name + " (" + type + ": " + value + ")";
    }
}
