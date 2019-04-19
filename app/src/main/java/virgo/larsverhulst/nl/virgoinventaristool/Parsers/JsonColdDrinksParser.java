package virgo.larsverhulst.nl.virgoinventaristool.Util;

import org.json.JSONObject;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public class JsonParser {

    public int insterColdDrinks(ArrayList<InvItem> coldDrinks){
        int succes = 0;

        JsonObject drinks = new JsonObject();

        for(InvItem i : coldDrinks){
            if(i.getNameOfDrink().equals("cola")){
                drinks.addProperty("cola" , ((i.getCrates()*24) + i.getBottles()));
            }else if(i.getNameOfDrink().equals("Sprite")){
                drinks.addProperty();
            }

        }

        return succes;
    }
}
