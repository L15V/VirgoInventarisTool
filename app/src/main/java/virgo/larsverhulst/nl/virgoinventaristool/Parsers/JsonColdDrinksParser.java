package virgo.larsverhulst.nl.virgoinventaristool.Parsers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import virgo.larsverhulst.nl.virgoinventaristool.Util.InvItem;
import virgo.larsverhulst.nl.virgoinventaristool.Util.RandomString;
import virgo.larsverhulst.nl.virgoinventaristool.Util.RequestQueueSingleton;

public class JsonColdDrinksParser extends AsyncTask<String, Void, String> {
    private static final String TAG = "JsonColdDrink parser";

    private Context context;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private SharedPreferences sprefs;

    private boolean done = false;

    public JsonColdDrinksParser(Context context) {
        this.context = context;
        editor = this.context.getSharedPreferences("cold_drinks", Context.MODE_PRIVATE).edit();
        editor.apply();
        prefs = this.context.getSharedPreferences("cold_drinks", Context.MODE_PRIVATE);
        sprefs = this.context.getSharedPreferences("settings" , Context.MODE_PRIVATE);
    }

    public JsonColdDrinksParser(Context context, int cola, int cola_zero, int sprite, int fuze_green, int fuze_sparkling, int fuze_blacktea, int fanta, int cassis, int o2_geel, int o2_rood, int o2_groen, int redbull, int fristi, int chocomel, int spa_rood) {
        editor = this.context.getSharedPreferences("cold_drinks", Context.MODE_PRIVATE).edit();
        editor.apply();
        prefs = this.context.getSharedPreferences("cold_drinks", Context.MODE_PRIVATE);

        this.context = context;

        editor.putInt("cola", cola);
        editor.putInt("cola_zero", cola_zero);
        editor.putInt("sprite", sprite);
        editor.putInt("fuze_green", fuze_green);
        editor.putInt("fuze_sparkling", fuze_sparkling);
        editor.putInt("fuze_blacktea", fuze_blacktea);
        editor.putInt("fanta", fanta);
        editor.putInt("cassis", cassis);
        editor.putInt("o2_geel", o2_geel);
        editor.putInt("o2_rood", o2_rood);
        editor.putInt("o2_groen", o2_groen);
        editor.putInt("redbull", redbull);
        editor.putInt("fristi", fristi);
        editor.putInt("chocomel", chocomel);
        editor.putInt("spa_rood", spa_rood);

        editor.apply();
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns the amount of cola using SharedPreferences
     *
     * @return amount cola
     */
    public int getCola() {
        return prefs.getInt("cola", 0);
    }

    /**
     * set amount of cola using SharedPreferences
     *
     * @param cola - to set amount of cola in SharedPreferences
     */
    public void setCola(int cola) {
        editor.putInt("cola", cola);
        editor.apply();
    }

    /**
     * add cola to previous amount
     *
     * @param amount - cola to add
     */
    public void addCola(int amount) {
        editor.putInt("cola", (prefs.getInt("cola", 0) + amount));
        editor.apply();
    }

    /**
     * remove cola from previous amount. Can't be smaller than 0!
     *
     * @param amount - cola to subtract
     */
    public void subtractCola(int amount) {
        int oldDrink = prefs.getInt("cola", 0);
        int newDrink = oldDrink - amount;
        if (newDrink >= 0) {
            editor.putInt("cola", newDrink);
            editor.apply();
        }
    }

    /**
     * Returns the amount of cola zero using SharedPreferences
     *
     * @return amount cola zero
     */
    public int getCola_zero() {
        return prefs.getInt("cola_zero", 0);
    }

    /**
     * set amount of cola zero using SharedPreferences
     *
     * @param cola_zero - to set amount of cola zero in SharedPreferences
     */
    public void setCola_zero(int cola_zero) {
        editor.putInt("cola_zero", cola_zero);
        editor.apply();
    }

    /**
     * add cola zero to previous amount
     *
     * @param amount - cola zero to add
     */
    public void addCola_zero(int amount) {
        editor.putInt("cola_zero", (prefs.getInt("cola_zero", 0) + amount));
        editor.apply();
    }

    /**
     * subtract cola zero from previous amount. Can,t be smaller than 0!
     *
     * @param amount - cola zero to subtract
     */
    public void subtractCola_zero(int amount) {
        int oldDrink = prefs.getInt("cola_zero", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("cola_zero", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of sprite using SharedPreferences
     *
     * @return amount sprite
     */
    public int getSprite() {
        return prefs.getInt("sprite", 0);
    }

    /**
     * set amount of sprite using SharedPreferences
     *
     * @param sprite - to set amount of sprite in SharedPreferences
     */
    public void setSprite(int sprite) {
        editor.putInt("sprite", sprite);
        editor.apply();
    }

    /**
     * add sprite to previous amount
     *
     * @param amount - sprite to add
     */
    public void addSprite(int amount) {
        editor.putInt("sprite", (prefs.getInt("sprite", 0) + amount));
        editor.apply();
    }

    /**
     * remove sprite from previous amount. Can't be smaller than 0!
     *
     * @param amount - sprite to subtract
     */
    public void subtractSprite(int amount) {
        int oldDrink = prefs.getInt("sprite", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("sprite", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of fuzetea green using SharedPreferences
     *
     * @return amount fuzetean green
     */
    public int getFuze_green() {
        return prefs.getInt("fuze_green", 0);
    }

    /**
     * set amount of fuzetea green using SharedPreferences
     *
     * @param fuze_green - to set amount of fuzetea greem in SharedPreferences
     */
    public void setFuze_green(int fuze_green) {
        editor.putInt("fuze_green", fuze_green);
        editor.apply();
    }

    /**
     * add fuzetea green to previous amount
     *
     * @param amount - fuzetean green to add
     */
    public void addFuze_green(int amount) {
        editor.putInt("fuze_green", (prefs.getInt("fuze_green", 0) + amount));
        editor.apply();
    }

    /**
     * remove fuzetea green from previous amount. Can't be smaller than 0!
     *
     * @param amount - fuzetea green to subtract
     */
    public void subtractFuze_green(int amount) {
        int oldDrink = prefs.getInt("fuze_green", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("fuze_green", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of fuzetea sparkling using SharedPreferences
     *
     * @return amount fuzetea sparkling
     */
    public int getFuze_sparkling() {
        return prefs.getInt("fuze_sparkling", 0);
    }

    /**
     * set amount of fuzetea sparkling using SharedPreferences
     *
     * @param fuze_sparkling - to set amount of fuzetea sparkling in SharedPreferences
     */
    public void setFuze_sparkling(int fuze_sparkling) {
        editor.putInt("fuze_sparkling", fuze_sparkling);
        editor.apply();
    }

    /**
     * add fuzetea sparkling to previous amount
     *
     * @param amount - fuzetea sparkling to add
     */
    public void addFuze_sparkling(int amount) {
        editor.putInt("fuze_sparkling", (prefs.getInt("fuze_sparkling", 0) + amount));
        editor.apply();
    }

    /**
     * remove fuzetea sparkling from previous amount. Can't be smaller than 0!
     *
     * @param amount - fuzetea sparkling to subtract
     */
    public void subtractFuze_sparkling(int amount) {
        int oldDrink = prefs.getInt("fuze_sparkling", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("fuze_sparkling", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of fuzetea blacktea using SharedPreferences
     *
     * @return amount fuze blacktea
     */
    public int getFuze_blacktea() {
        return prefs.getInt("fuze_blacktea", 0);
    }

    /**
     * set amount of fuzetea blacktea using SharedPreferences
     *
     * @param fuze_blacktea - to set amount of fuze_blacktea in SharedPreferences
     */
    public void setFuze_blacktea(int fuze_blacktea) {
        editor.putInt("fuze_blacktea", fuze_blacktea);
        editor.apply();
    }

    /**
     * add fuzetea blacktea to previous amount
     *
     * @param amount - fuzetea blacktea to add
     */
    public void addFuze_blacktea(int amount) {
        editor.putInt("fuze_blacktea", (prefs.getInt("fuze_blacktea", 0) + amount));
        editor.apply();
    }

    /**
     * remove fuzetea blacktea from previous amount. Can't be smaller than 0!
     *
     * @param amount - fuzetea blacktea to subtract
     */
    public void subtractFuze_blacktea(int amount) {
        int oldDrink = prefs.getInt("fuze_blacktea", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("fuze_blacktea", newDrink);
        editor.apply();

    }

    /**
     * Returns the amount of Fanta using SharedPreferences
     *
     * @return amount Fanta
     */
    public int getFanta() {
        return prefs.getInt("fanta", 0);
    }

    /**
     * set amount of Fanta using SharedPreferences
     *
     * @param fanta - to set amount of fanta in SharedPreferences
     */
    public void setFanta(int fanta) {
        editor.putInt("fanta", fanta);
        editor.apply();
    }

    /**
     * add fanta to previous amount
     *
     * @param amount - fanta to add
     */
    public void addFanta(int amount) {
        editor.putInt("fanta", (prefs.getInt("fanta", 0) + amount));
        editor.apply();
    }

    /**
     * remove fanta from previous amount. Can't be smaller than 0!
     *
     * @param amount - fanta to subtract
     */
    public void subtractFanta(int amount) {
        int oldDrink = prefs.getInt("fanta", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("fanta", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of Cassis using SharedPreferences
     *
     * @return amount cassis
     */
    public int getCassis() {
        return prefs.getInt("cassis", 0);
    }

    /**
     * set amount of cassis using SharedPreferences
     *
     * @param cassis - to set amount of cassis in SharedPreferences
     */
    public void setCassis(int cassis) {
        editor.putInt("cassis", cassis);
        editor.apply();
    }

    /**
     * add cassis to previous amount
     *
     * @param amount - cassis to add
     */
    public void addCassis(int amount) {
        editor.putInt("cassis", (prefs.getInt("cassis", 0) + amount));
        editor.apply();
    }

    /**
     * remove cassis from previous amount. Can't be smaller than 0!
     *
     * @param amount - cassis to subtract
     */
    public void subtractCassis(int amount) {
        int oldDrink = prefs.getInt("cassis", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("cassis", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of O2 geel using SharedPreferences
     *
     * @return amount O2 geel
     */
    public int getO2_geel() {
        return prefs.getInt("o2_geel", 0);
    }

    /**
     * set amount of O2 geel using SharedPreferences
     *
     * @param o2_geel - to set amount of O2 Geel in SharedPreferences
     */
    public void setO2_geel(int o2_geel) {
        editor.putInt("o2_geel", o2_geel);
        editor.apply();
    }

    /**
     * add O2 Geel to previous amount
     *
     * @param amount - O2 geel to add
     */
    public void addO2_geel(int amount) {
        editor.putInt("o2_geel", (prefs.getInt("o2_geel", 0) + amount));
        editor.apply();
    }

    /**
     * remove O2 Geel from previous amount. Can't be smaller than 0!
     *
     * @param amount - O2 geel to subtract
     */
    public void subtractO2_geel(int amount) {
        int oldDrink = prefs.getInt("o2_geel", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("o2_geel", newDrink);
        editor.apply();

    }

    /**
     * Returns the amount of O2 rood using SharedPreferences
     *
     * @return amount O2 rood
     */
    public int getO2_rood() {
        return prefs.getInt("o2_rood", 0);
    }

    /**
     * set amount of O2 rood using SharedPreferences
     *
     * @param o2_rood - to set amount of O2 Rood in SharedPreferences
     */
    public void setO2_rood(int o2_rood) {
        editor.putInt("o2_rood", o2_rood);
        editor.apply();
    }

    /**
     * add O2 rood to previous amount
     *
     * @param amount - O2 rood to add
     */
    public void addO2_rood(int amount) {
        editor.putInt("o2_rood", (prefs.getInt("o2_rood", 0) + amount));
        editor.apply();
    }

    /**
     * remove O2 Rood from previous amount. Can't be smaller than 0!
     *
     * @param amount - O2 Rood to subtract
     */
    public void subtractO2_rood(int amount) {
        int oldDrink = prefs.getInt("o2_rood", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("o2_rood", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of O2 groen using SharedPreferences
     *
     * @return amount O2 groen
     */
    public int getO2_groen() {
        return prefs.getInt("o2_groen", 0);
    }

    /**
     * set amount of O2 groen using SharedPreferences
     *
     * @param o2_groen - to set amount of O2 groen in SharedPreferences
     */
    public void setO2_groen(int o2_groen) {
        editor.putInt("o2_groen", o2_groen);
        editor.apply();
    }

    /**
     * add O2 groen to previous amount
     *
     * @param amount - O2 groen to add
     */
    public void addO2_groen(int amount) {
        editor.putInt("o2_groen", (prefs.getInt("o2_groen", 0) + amount));
        editor.apply();
    }

    /**
     * remove O2 groen from previous amount. Can't be smaller than 0!
     *
     * @param amount - O2 groen to subtract
     */
    public void subtractO2_groen(int amount) {
        int oldDrink = prefs.getInt("o2_groen", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("o2_groen", newDrink);
        editor.apply();

    }

    /**
     * Returns the amount of redbull using SharedPreferences
     *
     * @return amount redbull
     */
    public int getRedbull() {
        return prefs.getInt("redbull", 0);
    }

    /**
     * set amount of redbull using SharedPreferences
     *
     * @param redbull - to set amount of redbull in SharedPreferences
     */
    public void setRedbull(int redbull) {
        editor.putInt("redbull", redbull);
        editor.apply();
    }

    /**
     * add redbull to previous amount
     *
     * @param amount - cola to add
     */
    public void addRedbull(int amount) {
        editor.putInt("redbull", (prefs.getInt("redbull", 0) + amount));
        editor.apply();
    }

    /**
     * remove redbull from previous amount. Can't be smaller than 0!
     *
     * @param amount - redbull to subtract
     */
    public void subtractRedbull(int amount) {
        int oldDrink = prefs.getInt("redbull", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("redbull", newDrink);
        editor.apply();

    }

    /**
     * Returns the amount of fristi using SharedPreferences
     *
     * @return amount fristi
     */
    public int getFristi() {
        return prefs.getInt("fristi", 0);
    }

    /**
     * set amount of fristi using SharedPreferences
     *
     * @param fristi - to set amount of fristi in SharedPreferences
     */
    public void setFrist(int fristi) {
        editor.putInt("fristi", fristi);
        editor.apply();
    }

    /**
     * add fristi to previous amount
     *
     * @param amount - fristi to add
     */
    public void addFristi(int amount) {
        editor.putInt("fristi", (prefs.getInt("fristi", 0) + amount));
        editor.apply();
    }

    /**
     * remove fristi from previous amount. Can't be smaller than 0!
     *
     * @param amount - fristi to subtract
     */
    public void subtractFristi(int amount) {
        int oldDrink = prefs.getInt("fristi", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("fristi", newDrink);
        editor.apply();

    }

    /**
     * Returns the amount of Chocomel using SharedPreferences
     *
     * @return amount Chocomel
     */
    public int getChocomel() {
        return prefs.getInt("chocomel", 0);
    }

    /**
     * set amount of chocomel using SharedPreferences
     *
     * @param chocomel - to set amount of chocomel in SharedPreferences
     */
    public void setChocomel(int chocomel) {
        editor.putInt("chocomel", chocomel);
        editor.apply();
    }

    /**
     * add chocomel to previous amount
     *
     * @param amount - chocomel to add
     */
    public void addChocomel(int amount) {
        editor.putInt("chocomel", (prefs.getInt("chocomel", 0) + amount));
        editor.apply();
    }

    /**
     * remove chocomel from previous amount. Can't be smaller than 0!
     *
     * @param amount - chocomel to subtract
     */
    public void subtractChocomel(int amount) {
        int oldDrink = prefs.getInt("chocomel", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("chocomel", newDrink);
        editor.apply();

    }

    /**
     * Returns the amount of Chocomel using SharedPreferences
     *
     * @return amount Chocomel
     */
    public int getSpa_rood() {
        return prefs.getInt("spa_rood", 0);
    }

    /**
     * set amount of spa rood using SharedPreferences
     *
     * @param spa_rood - to set amount of spa rood in SharedPreferences
     */
    public void setSpa_rood(int spa_rood) {
        editor.putInt("spa_rood", spa_rood);
        editor.apply();
    }

    /**
     * add spa rood to previous amount
     *
     * @param amount - spa rood to add
     */
    public void addSpa_rood(int amount) {
        editor.putInt("spa_rood", (prefs.getInt("spa_rood", 0) + amount));
        editor.apply();
    }

    /**
     * remove spa rood from previous amount. Can't be smaller than 0!
     *
     * @param amount - spa rood to subtract
     */
    public void subtractSpa_rood(int amount) {
        int oldDrink = prefs.getInt("spa_rood", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("spa_rood", newDrink);
        editor.apply();

    }

    /**
     * Function for generation a json file that is complatible with the nodeJS REST api
     *
     * @return JSONArray for the nodeJS REST api
     * @throws JSONException
     */
    public JSONArray getColdDrinksJSON() throws JSONException {
        JSONArray drinks = new JSONArray();
        JSONObject obDrinks = new JSONObject();

        obDrinks.put("cola", getCola());
        obDrinks.put("cola_zero", getCola_zero());
        obDrinks.put("sprite", getSprite());
        obDrinks.put("fuze_green", getFuze_green());
        obDrinks.put("fuze_sparkling", getFuze_sparkling());
        obDrinks.put("fuze_blacktea", getFuze_blacktea());
        obDrinks.put("fanta", getFanta());
        obDrinks.put("cassis", getCassis());
        obDrinks.put("o2_geel", getO2_geel());
        obDrinks.put("o2_rood", getO2_rood());
        obDrinks.put("o2_groen", getO2_groen());
        obDrinks.put("redbull", getRedbull());
        obDrinks.put("fristi", getFristi());
        obDrinks.put("chocomel", getChocomel());
        obDrinks.put("spa_rood", getSpa_rood());

        System.out.println("Cola for json: " + prefs.getInt("cola", 0));


        drinks.put(obDrinks);

        return drinks;
    }

    /**
     * Get the latest inserted amount of drinks in the database
     *
     * @return int 1 on succes and 0
     * @throws JSONException
     */
    public int getLatestDrinks() throws JSONException {
        int succes = 0;

        this.execute(sprefs.getString("ip" , "0.0.0.0") + sprefs.getString("port", "0") +"/getlatestcolddrinks");
        if (this.getStatus() == Status.FINISHED) {
            succes = 1;
        }
        return succes;
    }


    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        BufferedReader reader = null;

        String urlString = "";
        String response = "";

        System.out.println("Started do in background");
        for (String url : params) {
            Log.i(TAG, url);
        }
        try {
            URL url = new URL(params[0]);
            System.out.println(url);

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);

            reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
            response = reader.readLine().toString();

            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }
        System.out.println("response: " + response);
        return response;
    }

    protected void onProgressUpdate(Integer... progress) {
        Log.i(TAG, progress.toString());
    }

    protected void onPostExecute(String response) {
        System.out.println("OnpostExecute started! Response: " + response);
        if (response != null && !response.equals("")) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                System.out.println("Out Response: " + jsonArray);
                System.out.println("Json Lenth: " + jsonArray.length());
                if(jsonArray.length() != 0) {
                    JSONObject drinks = jsonArray.getJSONObject(0);

                    setCola(drinks.getInt("cola"));
                    setCola_zero(drinks.getInt("cola_zero"));
                    setSprite(drinks.getInt("sprite"));
                    setFuze_green(drinks.getInt("fuze_green"));
                    setFuze_sparkling(drinks.getInt("fuze_sparkling"));
                    setFuze_blacktea(drinks.getInt("fuze_blacktea"));
                    setFanta(drinks.getInt("fanta"));
                    setCassis(drinks.getInt("cassis"));
                    setO2_geel(drinks.getInt("o2_geel"));
                    setO2_rood(drinks.getInt("o2_rood"));
                    setO2_groen(drinks.getInt("o2_groen"));
                    setRedbull(drinks.getInt("redbull"));
                    setFrist(drinks.getInt("fristi"));
                    setChocomel(drinks.getInt("chocomel"));
                    setSpa_rood(drinks.getInt("spa_rood"));

                    editor.apply();
                }else{
                    setCola(0);
                    setCola_zero(0);
                    setSprite(0);
                    setFuze_green(0);
                    setFuze_sparkling(0);
                    setFuze_blacktea(0);
                    setFanta(0);
                    setCassis(0);
                    setO2_geel(0);
                    setO2_rood(0);
                    setO2_groen(0);
                    setRedbull(0);
                    setFrist(0);
                    setChocomel(0);
                    setSpa_rood(0);
                    editor.apply();
                }

                done = true;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            setCola(0);
            setCola_zero(0);
            setSprite(0);
            setFuze_green(0);
            setFuze_sparkling(0);
            setFuze_blacktea(0);
            setFanta(0);
            setCassis(0);
            setO2_geel(0);
            setO2_rood(0);
            setO2_groen(0);
            setRedbull(0);
            setFrist(0);
            setChocomel(0);
            setSpa_rood(0);
            editor.apply();
        }
        done = true;
    }
}
