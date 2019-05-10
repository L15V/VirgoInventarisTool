package virgo.larsverhulst.nl.virgoinventaristool.Util;

import java.io.Serializable;

public class InvItem implements Serializable {
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
        if(nameOfDrink.contains("o2")){
            total = ((crates*6)+bottles);
        }else if((nameOfDrink.contains("bacardi")) || (nameOfDrink.contains("wijn"))){
            total = ((crates * 12) + bottles);
        }else{
            total = ((crates*24)+bottles);
        }
        return  total;
    }
}
