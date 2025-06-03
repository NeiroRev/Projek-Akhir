package tcg;

public abstract class Card {
    public String nama;
    public String tipe;
    public int hargaUnit;

    public Card(String nama, int hargaUnit, String tipe) {
        this.nama = nama;
        this.hargaUnit = hargaUnit;
        this.tipe = tipe;

    public abstract void play(Player sendiri, Player musuh);

    @Override
    public String toString() {
        return nama + " (" + tipe + ")";
    }
}
}