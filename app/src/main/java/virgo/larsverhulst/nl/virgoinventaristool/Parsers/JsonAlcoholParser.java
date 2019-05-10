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
import virgo.larsverhulst.nl.virgoinventaristool.Util.RequestQueueSingleton;

public class JsonAlcoholParser extends AsyncTask<String, Void, String> {
    private static final String TAG = "JsonAlcohol parser";

    private Context context;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private SharedPreferences sprefs;

    private boolean done = false;

    public JsonAlcoholParser(Context context) {
        this.context = context;
        editor = this.context.getSharedPreferences("alcohol", Context.MODE_PRIVATE).edit();
        prefs = this.context.getSharedPreferences("alcohol", Context.MODE_PRIVATE);
        editor.apply();

        sprefs = this.context.getSharedPreferences("settings" , Context.MODE_PRIVATE);
    }

    public JsonAlcoholParser(Context context, int hertog_jan, int jupiler, int liefmans, int leffe_blond, int palm, int hoegaarde, int witte_wijn, int rode_wijn, int bacardi, int bacardy_razz) {
        editor = this.context.getSharedPreferences("alcohol", Context.MODE_PRIVATE).edit();
        editor.apply();
        prefs = this.context.getSharedPreferences("alcohol", Context.MODE_PRIVATE);

        this.context = context;

        editor.putInt("hertog_jan", hertog_jan);
        editor.putInt("jupiler", jupiler);
        editor.putInt("liefmans", liefmans);
        editor.putInt("leffe_blond", leffe_blond);
        editor.putInt("palm", palm);
        editor.putInt("hoegaarde", hoegaarde);
        editor.putInt("witte_wijn", witte_wijn);
        editor.putInt("rode_wijn", rode_wijn);
        editor.putInt("bacardi", bacardi);
        editor.putInt("bacardi_razz", bacardi);

        editor.apply();
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns the amount of hertog jan using SharedPreferences
     *
     * @return amount hertog jan
     */
    public int getHertog_jan() {
        return prefs.getInt("hertog_jan", 0);
    }

    /**
     * set amount of hertog jan using SharedPreferences
     *
     * @param hertog_jan - to set amount of hertog_jan in SharedPreferences
     */
    public void setHertog_jan(int hertog_jan) {
        editor.putInt("hertog_jan", hertog_jan);
        editor.apply();
    }

    /**
     * add hertog jan to previous amount
     *
     * @param amount - hertog jan to add
     */
    public void addHertog_jan(int amount) {
        editor.putInt("hertog_jan", (prefs.getInt("hertog_jan", 0) + amount));
        editor.apply();
    }

    /**
     * remove hertog jan from previous amount. Can't be smaller than 0!
     *
     * @param amount - hertog jan to subtract
     */
    public void subtractHertog_jan(int amount) {
        int oldDrink = prefs.getInt("hertog_jan", 0);
        int newDrink = oldDrink - amount;
        if (newDrink >= 0) {
            editor.putInt("hertog_jan", newDrink);
            editor.apply();
        }
    }

    /**
     * Returns the amount of jupiler using SharedPreferences
     *
     * @return amount jupiler
     */
    public int getJupiler() {
        return prefs.getInt("jupiler", 0);
    }

    /**
     * set amount of jupiler using SharedPreferences
     *
     * @param jupiler - to set amount of jupiler in SharedPreferences
     */
    public void setJupiler(int jupiler) {
        editor.putInt("jupiler", jupiler);
        editor.apply();
    }

    /**
     * add jupiler to previous amount
     *
     * @param amount - jupiler to add
     */
    public void addJupiler(int amount) {
        editor.putInt("jupiler", (prefs.getInt("jupiler", 0) + amount));
        editor.apply();
    }

    /**
     * subtract Jupiler from previous amount. Can,t be smaller than 0!
     *
     * @param amount - cola Jupiler to subtract
     */
    public void subtractJupiler(int amount) {
        int oldDrink = prefs.getInt("jupiler", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("jupiler", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of liefmans using SharedPreferences
     *
     * @return amount liefmans
     */
    public int getLiefmans() {
        return prefs.getInt("liefmans", 0);
    }

    /**
     * set amount of liefmans using SharedPreferences
     *
     * @param liefmans - to set amount of liefmans in SharedPreferences
     */
    public void setLiefmans(int liefmans) {
        editor.putInt("liefmans", liefmans);
        editor.apply();
    }

    /**
     * add liefmans to previous amount
     *
     * @param amount - liefmans to add
     */
    public void addLiefmans(int amount) {
        editor.putInt("liefmans", (prefs.getInt("liefmans", 0) + amount));
        editor.apply();
    }

    /**
     * remove liefmans from previous amount. Can't be smaller than 0!
     *
     * @param amount - liefmans to subtract
     */
    public void subtractLiefmans(int amount) {
        int oldDrink = prefs.getInt("liefmans", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("liefmans", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of leffe blond green using SharedPreferences
     *
     * @return amount leffe blond
     */
    public int getLeffe_blond() {
        return prefs.getInt("leffe_blond", 0);
    }

    /**
     * set amount of leffe blond using SharedPreferences
     *
     * @param leffe_blond - to set amount of leffe blond in SharedPreferences
     */
    public void setLeffe_blond(int leffe_blond) {
        editor.putInt("leffe_blond", leffe_blond);
        editor.apply();
    }

    /**
     * add leffe blond green to previous amount
     *
     * @param amount - leffe blond green to add
     */
    public void addLeffe_blond(int amount) {
        editor.putInt("leffe_blond", (prefs.getInt("leffe_blond", 0) + amount));
        editor.apply();
    }

    /**
     * remove leffe_blond green from previous amount. Can't be smaller than 0!
     *
     * @param amount - leffe_blond green to subtract
     */
    public void subtractLeffe_blond(int amount) {
        int oldDrink = prefs.getInt("leffe_blond", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("leffe_blond", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of Palm using SharedPreferences
     *
     * @return amount Palm
     */
    public int getPalm() {
        return prefs.getInt("palm", 0);
    }

    /**
     * set amount of Palm using SharedPreferences
     *
     * @param palm - to set amount of palm in SharedPreferences
     */
    public void setPalm(int palm) {
        editor.putInt("palm", palm);
        editor.apply();
    }

    /**
     * add Palm to previous amount
     *
     * @param amount - Palm to add
     */
    public void addPalm(int amount) {
        editor.putInt("palm", (prefs.getInt("palm", 0) + amount));
        editor.apply();
    }

    /**
     * remove palm from previous amount. Can't be smaller than 0!
     *
     * @param amount - palm to subtract
     */
    public void subtractPalm(int amount) {
        int oldDrink = prefs.getInt("palm", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("palm", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of hoegaarde using SharedPreferences
     *
     * @return amount Hoegaarde
     */
    public int getHoegaarde() {
        return prefs.getInt("hoegaarde", 0);
    }

    /**
     * set amount of hoegaarde using SharedPreferences
     *
     * @param hoegaarde - to set amount of hoegaarde in SharedPreferences
     */
    public void setHoegaarde(int hoegaarde) {
        editor.putInt("hoegaarde", hoegaarde);
        editor.apply();
    }

    /**
     * add hoegaarde to previous amount
     *
     * @param amount - hoegaarde to add
     */
    public void addHoegaarde(int amount) {
        editor.putInt("hoegaarde", (prefs.getInt("hoegaarde", 0) + amount));
        editor.apply();
    }

    /**
     * remove hoegaarde from previous amount. Can't be smaller than 0!
     *
     * @param amount - hoegaarde to subtract
     */
    public void subtractHoegaarde(int amount) {
        int oldDrink = prefs.getInt("hoegaarde", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("hoegaarde", newDrink);
        editor.apply();

    }

    /**
     * Returns the amount of witte wijn using SharedPreferences
     *
     * @return amount witte wijn
     */
    public int getWitte_wijn() {
        return prefs.getInt("witte_wijn", 0);
    }

    /**
     * set amount of witte wijn using SharedPreferences
     *
     * @param witte_wijn - to set amount of witte wijn in SharedPreferences
     */
    public void setWitte_wijn(int witte_wijn) {
        editor.putInt("witte_wijn", witte_wijn);
        editor.apply();
    }

    /**
     * add witte wijn to previous amount
     *
     * @param amount - witte wijn to add
     */
    public void addWitte_wijn(int amount) {
        editor.putInt("witte_wijn", (prefs.getInt("witte_wijn", 0) + amount));
        editor.apply();
    }

    /**
     * remove Witte wijn from previous amount. Can't be smaller than 0!
     *
     * @param amount - witte wijn to subtract
     */
    public void subtractWitte_wijn(int amount) {
        int oldDrink = prefs.getInt("witte_wijn", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("witte_wijn", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of rode wijn using SharedPreferences
     *
     * @return amount rode wijn
     */
    public int getRode_wijn() {
        return prefs.getInt("rode_wijn", 0);
    }

    /**
     * set amount of Rode wijn using SharedPreferences
     *
     * @param rode_wijn - to set amount of rode Wijn in SharedPreferences
     */
    public void setRode_wijn(int rode_wijn) {
        editor.putInt("rode_wijn", rode_wijn);
        editor.apply();
    }

    /**
     * add rode wijn to previous amount
     *
     * @param amount - rode wijn to add
     */
    public void addRode_wijn(int amount) {
        editor.putInt("rode_wijn", (prefs.getInt("rode_wijn", 0) + amount));
        editor.apply();
    }

    /**
     * remove rode wijn from previous amount. Can't be smaller than 0!
     *
     * @param amount - rode wijn to subtract
     */
    public void subtractRode_wijn(int amount) {
        int oldDrink = prefs.getInt("rode_wijn", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("rode_wijn", newDrink);
        editor.apply();
    }

    /**
     * Returns the amount of bacardi using SharedPreferences
     *
     * @return amount bacardi
     */
    public int getBacardi() {
        return prefs.getInt("bacardi", 0);
    }

    /**
     * set amount of bacardi using SharedPreferences
     *
     * @param bacardi - to set amount of bacardi in SharedPreferences
     */
    public void setBacardi(int bacardi) {
        editor.putInt("bacardi", bacardi);
        editor.apply();
    }

    /**
     * add bacardi to previous amount
     *
     * @param amount - bacardi to add
     */
    public void addBacardi(int amount) {
        editor.putInt("bacardi", (prefs.getInt("bacardi", 0) + amount));
        editor.apply();
    }

    /**
     * remove bacardi from previous amount. Can't be smaller than 0!
     *
     * @param amount - bacardi to subtract
     */
    public void subtractBacardi(int amount) {
        int oldDrink = prefs.getInt("bacardi", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("bacardi", newDrink);
        editor.apply();

    }

    /**
     * Returns the amount of bacardi razz using SharedPreferences
     *
     * @return amount bacardi razz
     */
    public int getBacardi_razz() {
        return prefs.getInt("bacardi_razz", 0);
    }

    /**
     * set amount of bacardi razz using SharedPreferences
     *
     * @param bacardi_razz - to set amount of O2 Rood in SharedPreferences
     */
    public void setBacardi_razz(int bacardi_razz) {
        editor.putInt("bacardi_razz", bacardi_razz);
        editor.apply();
    }

    /**
     * add bacardi razz to previous amount
     *
     * @param amount - bacardi razz to add
     */
    public void addBacardi_razz(int amount) {
        editor.putInt("bacardi_razz", (prefs.getInt("bacardi_razz", 0) + amount));
        editor.apply();
    }

    /**
     * remove Bacardi razz from previous amount. Can't be smaller than 0!
     *
     * @param amount - Bacardi razz to subtract
     */
    public void subtractBacardi_razz(int amount) {
        int oldDrink = prefs.getInt("bacardi_razz", 0);
        int newDrink = oldDrink - amount;
        if (newDrink < 0) {
            newDrink = 0;
        }
        editor.putInt("bacardi_razz", newDrink);
        editor.apply();
    }



    /**
     * Function for generation a json file that is complatible with the nodeJS REST api
     *
     * @return JSONArray for the nodeJS REST api
     * @throws JSONException
     */
    public JSONArray getAlcoholJSON() throws JSONException {
        JSONArray drinks = new JSONArray();
        JSONObject obDrinks = new JSONObject();

        obDrinks.put("hertog_jan", getHertog_jan());
        obDrinks.put("jupiler", getJupiler());
        obDrinks.put("liefmans", getLiefmans());
        obDrinks.put("leffe_blond", getLeffe_blond());
        obDrinks.put("palm", getPalm());
        obDrinks.put("hoegaarde", getHoegaarde());
        obDrinks.put("witte_wijn", getWitte_wijn());
        obDrinks.put("rode_wijn", getRode_wijn());
        obDrinks.put("bacardi", getBacardi());
        obDrinks.put("bacardi_razz", getBacardi_razz());

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

        this.execute(sprefs.getString("ip" , "0.0.0.0")+ sprefs.getString("port" , "0")+"/getlatestalcoholdrinks");
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
        return response;
    }

    protected void onProgressUpdate(Integer... progress) {
        Log.i(TAG, progress.toString());
    }

    protected void onPostExecute(String response) {
        System.out.println("OnpostExecute started!" + response);
        if (response != null && !response.equals("")) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                System.out.println("Out Response: " + jsonArray);
                System.out.println("AlcoholJsonLength: " + jsonArray.length());

                if(jsonArray.length() != 0) {
                    JSONObject drinks = jsonArray.getJSONObject(0);

                    setHertog_jan(drinks.getInt("hertog_jan"));
                    setJupiler(drinks.getInt("jupiler"));
                    setLiefmans(drinks.getInt("liefmans"));
                    setLeffe_blond(drinks.getInt("leffe_blond"));
                    setPalm(drinks.getInt("palm"));
                    setHoegaarde(drinks.getInt("hoegaarde"));
                    setWitte_wijn(drinks.getInt("witte_wijn"));
                    setRode_wijn(drinks.getInt("rode_wijn"));
                    setBacardi(drinks.getInt("bacardi"));
                    setBacardi_razz(drinks.getInt("bacardi_razz"));
                    editor.apply();

                }else{
                    setHertog_jan(0);
                    setJupiler(0);
                    setLiefmans(0);
                    setLeffe_blond(0);
                    setPalm(0);
                    setHoegaarde(0);
                    setWitte_wijn(0);
                    setRode_wijn(0);
                    setBacardi(0);
                    setBacardi_razz(0);
                    editor.apply();
                }

                done = true;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            setHertog_jan(0);
            setJupiler(0);
            setLiefmans(0);
            setLeffe_blond(0);
            setPalm(0);
            setHoegaarde(0);
            setWitte_wijn(0);
            setRode_wijn(0);
            setBacardi(0);
            setBacardi_razz(0);
            editor.apply();
        }
        done = true;
    }
}
