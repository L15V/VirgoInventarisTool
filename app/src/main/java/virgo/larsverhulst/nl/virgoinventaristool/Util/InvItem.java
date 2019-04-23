package virgo.larsverhulst.nl.virgoinventaristool.Util;

public class InvItem {
    private String nameOfDrink;
    private String kindOfDrink;
    private int crates;
    private int bottles;

    public InvItem(String nameOfDrink, String kindOfDrink, int crates, int bottles) {
        this.nameOfDrink = nameOfDrink;
        this.kindOfDrink = kindOfDrink;
        this.crates = crates;
        this.bottles = bottles;
    }

    public String getNameOfDrink() {
        return nameOfDrink;
    }

    public void setNameOfDrink(String nameOfDrink) {
        this.nameOfDrink = nameOfDrink;
    }

    public String getKindOfDrink() {
        return kindOfDrink;
    }

    public void setKindOfDrink(String kindOfDrink) {
        this.kindOfDrink = kindOfDrink;
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

    public int getTotalToAdd(){
        int total = 0;
        total = ((crates*24)+bottles);
        return  total;
    }
}
