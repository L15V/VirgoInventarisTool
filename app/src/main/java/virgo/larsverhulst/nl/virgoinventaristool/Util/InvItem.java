package virgo.larsverhulst.nl.virgoinventaristool.Util;

public class InvItem {
    private String nameOfDrink;
    private int crates;
    private int bottles;

    public InvItem(String nameOfDrink, int crates, int bottles) {
        this.nameOfDrink = nameOfDrink;
        this.crates = crates;
        this.bottles = bottles;
    }

    public String getNameOfDrink() {
        return nameOfDrink;
    }

    public void setNameOfDrink(String nameOfDrink) {
        this.nameOfDrink = nameOfDrink;
    }

    public int getCrates() {
        return crates;
    }

    public void setCrates(int crates) {
        this.crates = crates;
    }

    public int getBottles() {
        return bottles;
    }

    public void setBottles(int bottles) {
        this.bottles = bottles;
    }
}
