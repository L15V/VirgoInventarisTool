package virgo.larsverhulst.nl.virgoinventaristool.Parsers;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import virgo.larsverhulst.nl.virgoinventaristool.Util.InvItem;

public class JsonColdDrinksParser extends AsyncTask<String , Void , String> {
    private static final String TAG = "JsonColdDrink parser";

    private int cola;
    private int cola_zero;
    private int sprite;
    private int fuze_green;
    private int fuze_sparkling;
    private int fuze_blacktea;
    private int fanta;
    private int cassis;
    private int o2_geel;
    private int o2_rood;
    private int o2_groen;
    private int redbull;
    private int fristi;
    private int chocomel;
    private int spa_rood;

    public JsonColdDrinksParser(){

    }

    public JsonColdDrinksParser(int cola, int cola_zero, int sprite, int fuze_green, int fuze_sparkling, int fuze_blacktea, int fanta, int cassis, int o2_geel, int o2_rood, int o2_groen, int redbull, int fristi, int chocomel, int spa_rood) {
        this.cola = cola;
        this.cola_zero = cola_zero;
        this.sprite = sprite;
        this.fuze_green = fuze_green;
        this.fuze_sparkling = fuze_sparkling;
        this.fuze_blacktea = fuze_blacktea;
        this.fanta = fanta;
        this.cassis = cassis;
        this.o2_geel = o2_geel;
        this.o2_rood = o2_rood;
        this.o2_groen = o2_groen;
        this.redbull = redbull;
        this.fristi = fristi;
        this.chocomel = chocomel;
        this.spa_rood = spa_rood;
    }

    /**
     *  Standaard getters and setters methods + add en remove methods
     */
    public int getCola() {
        return cola;
    }

    public void setCola(int cola) {
        this.cola = cola;
    }

    public void addCola(int amount){
        this.cola += amount;
    }

    public void removeCola(int amount){
        this.cola -= amount;
    }

    public int getCola_zero() {
        return cola_zero;
    }

    public void setCola_zero(int cola_zero) {
        this.cola_zero = cola_zero;
    }

    public void addColaZero(int amount){
        this.cola_zero += amount;
    }

    public void removeColaZero(int amount){
        this.cola_zero -= amount;
    }

    public int getSprite() {
        return sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

    public void addSprite(int amount){
        this.sprite += amount;
    }

    public void removeSprite(int amount){
        this.sprite -= amount;
    }

    public int getFuze_green() {
        return fuze_green;
    }

    public void setFuze_green(int fuze_green) {
        this.fuze_green = fuze_green;
    }

    public void addFuze_green(int amount){
        this.fuze_green += amount;
    }

    public void removeFuze_green(int amount){
        this.fuze_green -= amount;
    }

    public int getFuze_sparkling() {
        return fuze_sparkling;
    }

    public void setFuze_sparkling(int fuze_sparkling) {
        this.fuze_sparkling = fuze_sparkling;
    }

    public void addFuze_sparkling(int amount){
        this.fuze_sparkling += amount;
    }

    public void removeFuze_sparkling(int amount){
        this.fuze_sparkling -= amount;
    }

    public int getFuze_blacktea() {
        return fuze_blacktea;
    }

    public void setFuze_blacktea(int fuze_blacktea) {
        this.fuze_blacktea = fuze_blacktea;
    }

    public void addFuze_blacktea(int amount){
        this.fuze_blacktea += amount;
    }

    public void removeFuze_blacktea(int amount){
        this.fuze_blacktea -= amount;
    }

    public int getFanta() {
        return fanta;
    }

    public void setFanta(int fanta) {
        this.fanta = fanta;
    }

    public void addFanta(int amount){
        this.fanta += amount;
    }

    public void removeFanta(int amount){
        this.fanta -= amount;
    }

    public int getCassis() {
        return cassis;
    }

    public void setCassis(int cassis) {
        this.cassis = cassis;
    }

    public void addCassic(int amount){
        this.cassis += amount;
    }

    public void removeCassic(int amount){
        this.cassis -= amount;
    }

    public int getO2_geel() {
        return o2_geel;
    }

    public void setO2_geel(int o2_geel) {
        this.o2_geel = o2_geel;
    }

    public void addO2_geel(int amount){
        this.o2_geel += amount;
    }

    public void removeO2_geel(int amount){
        this.o2_geel -= amount;
    }

    public int getO2_rood() {
        return o2_rood;
    }

    public void setO2_rood(int o2_rood) {
        this.o2_rood = o2_rood;
    }

    public void addO2_rood(int amount){
        this.o2_rood += amount;
    }

    public void removeO2_rood(int amount){
        this.o2_rood -= amount;
    }

    public int getO2_groen() {
        return o2_groen;
    }

    public void setO2_groen(int o2_groen) {
        this.o2_groen = o2_groen;
    }

    public void addO2_groen(int amount){
        this.o2_groen += amount;
    }

    public void removeO2_groen(int amount){
        this.o2_groen -= amount;
    }

    public int getRedbull() {
        return redbull;
    }

    public void setRedbull(int redbull) {
        this.redbull = redbull;
    }

    public void addRedbull(int amount){
        this.redbull += amount;
    }

    public void removeRedbull(int amount){
        this.redbull -= amount;
    }

    public int getFristi() {
        return fristi;
    }

    public void setFristi(int fristi) {
        this.fristi = fristi;
    }

    public void addFristi(int amount){
        this.fristi += amount;
    }

    public void removeFristi(int amount){
        this.fristi -= amount;
    }

    public int getChocomel() {
        return chocomel;
    }

    public void setChocomel(int chocomel) {
        this.chocomel = chocomel;
    }

    public void addChocomel(int amount){
        this.chocomel += amount;
    }

    public void removeChocomel(int amount){
        this.chocomel -= amount;
    }

    public int getSpa_rood() {
        return spa_rood;
    }

    public void setSpa_rood(int spa_rood) {
        this.spa_rood = spa_rood;
    }

    public void addSpa_rood(int amount){
        this.spa_rood += amount;
    }

    public void removeSpa_rood(int amount){
        this.spa_rood -= amount;
    }

    /**
     * Function for generation a json file that is complatible with the nodeJS REST api
     * @return JSONArray for the nodeJS REST api
     * @throws JSONException
     */
    public JSONArray getColdDrinksJSON() throws JSONException {
        JSONArray drinks = new JSONArray();
        JSONObject obDrinks = new JSONObject();

        obDrinks.put("cola" , this.cola);
        obDrinks.put("cola_zero", this.cola_zero);
        obDrinks.put("sprite" , this.sprite);
        obDrinks.put("fuze_green" , this.fuze_green);
        obDrinks.put("fuze_sparkling", this.fuze_sparkling);
        obDrinks.put("fuze_blacktea", this.fuze_blacktea);
        obDrinks.put("fanta" , this.fanta);
        obDrinks.put("cassis" , this.cassis);
        obDrinks.put("o2_geel" , this.o2_geel);
        obDrinks.put("o2_rood" , this.o2_rood);
        obDrinks.put("o2_groen" , this.o2_groen);
        obDrinks.put("redbull" , this.redbull);
        obDrinks.put("fristi" , this.fristi);
        obDrinks.put("chocomel" , this.chocomel);
        obDrinks.put("spa_rood" , this.spa_rood);

        System.out.println("Cola for json: " + this.cola);

        drinks.put(obDrinks);

        return drinks;
    }

    /**
     * Get the latest inserted amount of drinks in the database
     * @return int 1 on succes and 0
     * @throws JSONException
     */
    public int getLatestDrinks() throws JSONException {
        int succes = 0;

        this.execute("http://192.168.0.19:8080/getlatestcolddrinks/");
        if(this.getStatus() == Status.FINISHED){
            succes = 1;
        }
        return succes;
    }







    @Override
    protected  String doInBackground(String... params){
        InputStream inputStream = null;
        BufferedReader reader = null;

        String urlString = "";
        String response = "";

        System.out.println("Started do in background");
        for (String url : params){
            Log.i(TAG, url);
        }
        try{
            URL url = new URL(params[0]);
            System.out.println(url);

            URLConnection connection = url.openConnection();

            reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
            response = reader.readLine().toString();

            String line;
            while((line = reader.readLine())!= null){
                response += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }finally {
            if(reader != null){
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }
        return response;
    }

    protected void onProgressUpdate(Integer... progress){
        Log.i(TAG, progress.toString());
    }

    protected void onPostExecute(String response){
        System.out.println("OnpostExecute started!");
        if(response != null){
            try{
                JSONArray jsonArray = new JSONArray(response);
                System.out.println(jsonArray);

                JSONObject drinks = jsonArray.getJSONObject(0);
                this.cola = drinks.getInt("cola");
                this.cola_zero = drinks.getInt("cola_zero");
                this.sprite = drinks.getInt("sprite");
                this.fuze_green = drinks.getInt("fuze_green");
                this.fuze_sparkling = drinks.getInt("fuze_sparkling");
                this.fuze_blacktea = drinks.getInt("fuze_blacktea");
                this.fanta = drinks.getInt("fanta");
                this.cassis = drinks.getInt("cassis");
                this.o2_geel = drinks.getInt("o2_geel");
                this.o2_rood = drinks.getInt("o2_rood");
                this.o2_groen = drinks.getInt("o2_groen");
                this.redbull = drinks.getInt("redbull");
                this.fristi = drinks.getInt("fristi");
                this.chocomel = drinks.getInt("chocomel");
                this.spa_rood = drinks.getInt("spa_rood");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
