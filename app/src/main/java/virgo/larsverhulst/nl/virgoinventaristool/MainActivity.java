package virgo.larsverhulst.nl.virgoinventaristool;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import virgo.larsverhulst.nl.virgoinventaristool.Adapters.InvItemRViewAdapter;
import virgo.larsverhulst.nl.virgoinventaristool.Adapters.ScreenSlideAdapter;
import virgo.larsverhulst.nl.virgoinventaristool.Parsers.JsonAlcoholParser;
import virgo.larsverhulst.nl.virgoinventaristool.Parsers.JsonColdDrinksParser;
import virgo.larsverhulst.nl.virgoinventaristool.Util.InvItem;
import virgo.larsverhulst.nl.virgoinventaristool.Util.RequestQueueSingleton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RequestQueue requestQueue;

    private final String REQ_TAG = "VACTIVITY";

    private List<InvItem> items;

    private boolean isDone = false;

    private int cratesToAdd = 0;
    private int bottlesToAdd = 0;

    private TextView amountCrates;
    private TextView amountBottles;

    private Button crate1;
    private Button crate5;
    private Button crate10;
    private Button crate20;

    private Button bottle1;
    private Button bottle5;
    private Button bottle10;
    private Button bottle20;

    private Button addButton;


    /**
     * attributes for the navigation buttons on the main screen
     */
    private Button coldDrinkButton;
    private Button alcoholButton;
    private Button foodButton;
    private Button merchButton;
    private Button clothesButton;
    private Button otherButton;

    /**
     * attribte for the buttons to add or remove stuff
     */

    private Button inButton;
    private Button outButton;

    /**
     * attributes for a list with the current added stuff
     */

    private RecyclerView currentItemsView;
    private InvItemRViewAdapter invAdapter;
    private RecyclerView.LayoutManager invLayoutManager;

    /**
     * viewpager for holding the fragments
     */

    private ViewPager viewPager;

    private ArrayList<Button> navigationButtons;

    Dialog amountPopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE);

        requestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        items = new ArrayList<>();
        navigationButtons = new ArrayList<>();

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            setContentView(R.layout.activity_main);
        }else{
            setContentView(R.layout.activity_main_old_android);
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen


        amountPopup = new Dialog(this);


        /**
         * Assignments to the layout items
         */

        coldDrinkButton = findViewById(R.id.mainScreen_coldDrinkButton);
        alcoholButton = findViewById(R.id.mainScreen_alcoholButton);
        foodButton = findViewById(R.id.mainScreen_foodButton);
        merchButton = findViewById(R.id.mainScreen_merchButton);
        clothesButton = findViewById(R.id.mainScreen_clothesButton);
        otherButton = findViewById(R.id.mainScreen_otherButton);

        navigationButtons.add(coldDrinkButton);
        navigationButtons.add(alcoholButton);
        navigationButtons.add(foodButton);
        navigationButtons.add(merchButton);
        navigationButtons.add(clothesButton);
        navigationButtons.add(otherButton);

        for(int i = 0; i < navigationButtons.size() ; i++){
            navigationButtons.get(i).setOnClickListener(this);
        }

        invAdapter = new InvItemRViewAdapter(getApplication());
        invLayoutManager = new LinearLayoutManager(getApplicationContext());
        currentItemsView = findViewById(R.id.mainScreen_currentItems);

        currentItemsView.setHasFixedSize(false);
        currentItemsView.setLayoutManager(invLayoutManager);
        currentItemsView.setAdapter(invAdapter);

        viewPager = findViewById(R.id.mainScreen_viewPager);
        viewPager.setAdapter(new ScreenSlideAdapter(getSupportFragmentManager()));
        setButtonEnableColor(navigationButtons.get(viewPager.getCurrentItem()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int index) {
                switch(index){
                    case 0:
                        setButtonEnableColor(coldDrinkButton);
                        break;
                    case 1:
                        setButtonEnableColor(alcoholButton);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.mainScreen_coldDrinkButton:
                ShowPopup(v);
                break;
            case R.id.mainScreen_alcoholButton:
                items.add(new InvItem("Cola" , "cold_drink", 5 ,15));
                items.add(new InvItem("Sprite" ,"cold_drink", 5 ,15));
                items.add(new InvItem("Fanta" ,"cold_drink", 5 ,15));
                items.add(new InvItem("Hertog" ,"cold_drink", 5 ,15));
                items.add(new InvItem("Maes" ,"cold_drink", 5 ,15));
                items.add(new InvItem("Fuze green" ,"cold_drink", 5 ,15));
                items.add(new InvItem("Fuze spark" ,"cold_drink", 5 ,15));
                items.add(new InvItem("Cola" , "cold_drink",5 ,15));

                invAdapter.update(items);
                invAdapter.notifyDataSetChanged();
                break;
            case R.id.mainScreen_clothesButton:
                final JsonColdDrinksParser cd = new JsonColdDrinksParser(this);
                final JsonAlcoholParser ad = new JsonAlcoholParser(this);
                try {
                    cd.getLatestDrinks();
                    ad.getLatestDrinks();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(cd.isDone() || ad.isDone()){
                                cd.addCola(1);
                                cd.addCola_zero(2);
                                cd.addSprite(3);
                                cd.addFuze_green(4);
                                cd.addFuze_sparkling(5);
                                cd.addFuze_blacktea(6);
                                cd.addFanta(7);
                                cd.addCassis(8);
                                cd.addO2_geel(9);
                                cd.addO2_rood(10);
                                cd.addO2_groen(11);
                                cd.addRedbull(12);
                                cd.addFristi(13);
                                cd.addChocomel(14);
                                cd.addSpa_rood(15);

                                ad.addHertog_jan(1);
                                ad.addJupiler(2);
                                ad.addLiefmans(3);
                                ad.addLeffe_blond(4);
                                ad.addPalm(5);
                                ad.addHoegaarde(6);
                                ad.addWitte_wijn(7);
                                ad.addRode_wijn(8);
                                ad.addBacardi(9);
                                ad.addBacardi_razz(10);
                            }else{
                                while(!cd.isDone() || ad.isDone()){
                                    if(cd.isDone() && ad.isDone()){

                                        cd.addCola(1);
                                        cd.addCola_zero(2);
                                        cd.addSprite(3);
                                        cd.addFuze_green(4);
                                        cd.addFuze_sparkling(5);
                                        cd.addFuze_blacktea(6);
                                        cd.addFanta(7);
                                        cd.addCassis(8);
                                        cd.addO2_geel(9);
                                        cd.addO2_rood(10);
                                        cd.addO2_groen(11);
                                        cd.addRedbull(12);
                                        cd.addFristi(13);
                                        cd.addChocomel(14);
                                        cd.addSpa_rood(15);

                                        ad.addHertog_jan(1);
                                        ad.addJupiler(2);
                                        ad.addLiefmans(3);
                                        ad.addLeffe_blond(4);
                                        ad.addPalm(5);
                                        ad.addHoegaarde(6);
                                        ad.addWitte_wijn(7);
                                        ad.addRode_wijn(8);
                                        ad.addBacardi(9);
                                        ad.addBacardi_razz(10);

                                        isDone = true;
                                    }
                                }
                                if(!isDone){
                                    cd.addCola(1);
                                    cd.addCola_zero(2);
                                    cd.addSprite(3);
                                    cd.addFuze_green(4);
                                    cd.addFuze_sparkling(5);
                                    cd.addFuze_blacktea(6);
                                    cd.addFanta(7);
                                    cd.addCassis(8);
                                    cd.addO2_geel(9);
                                    cd.addO2_rood(10);
                                    cd.addO2_groen(11);
                                    cd.addRedbull(12);
                                    cd.addFristi(13);
                                    cd.addChocomel(14);
                                    cd.addSpa_rood(15);

                                    ad.addHertog_jan(1);
                                    ad.addJupiler(2);
                                    ad.addLiefmans(3);
                                    ad.addLeffe_blond(4);
                                    ad.addPalm(5);
                                    ad.addHoegaarde(6);
                                    ad.addWitte_wijn(7);
                                    ad.addRode_wijn(8);
                                    ad.addBacardi(9);
                                    ad.addBacardi_razz(10);
                                }
                            }
                            getJsonResponsePost(cd.getColdDrinksJSON(), "http://192.168.178.26:8080/insertcolddrinks/");
                            getJsonResponsePost(ad.getAlcoholJSON(), "http://192.168.178.26:8080/insertalcohol");
                            isDone = false;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Method to change the enable collor from a selected button.
     * @param b  button to change in color
     */

    public void setButtonEnableColor(Button b){
        allButtonDefaultColor();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            b.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.DarkBlue)));
        }else{
            b.setBackgroundColor(getResources().getColor(R.color.DarkBlue));
        }
        b.setTextColor(getResources().getColor(R.color.white));

    }

    /**
     * Method to set all the buttons to a light blue background
     */
    public void allButtonDefaultColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            coldDrinkButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            alcoholButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            foodButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            merchButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            clothesButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            otherButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
        }else{
            coldDrinkButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            alcoholButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            foodButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            merchButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            clothesButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            otherButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
        }
    }

    public void ShowPopup(View v){

        final TextView amountCrates;
        final TextView amountBottles;

        Button crate1;
        Button crate5;
        Button crate10;
        Button crate20;

        Button bottle1;
        Button bottle5;
        Button bottle10;
        Button bottle20;

        Button addButton;

        amountPopup.setContentView(R.layout.custom_popup);



        amountCrates = amountPopup.findViewById(R.id.customPopup_amountOfCrates);
        amountCrates.setText(Integer.toString(cratesToAdd));
        amountBottles = amountPopup.findViewById(R.id.customPopup_amountOfBottles);
        amountBottles.setText(Integer.toString(bottlesToAdd));

        crate1 = amountPopup.findViewById(R.id.customPopup_cr1);
        crate5 = amountPopup.findViewById(R.id.customPopup_cr5);
        crate10 = amountPopup.findViewById(R.id.customPopup_cr10);
        crate20 = amountPopup.findViewById(R.id.customPopup_cr20);

        bottle1 = amountPopup.findViewById(R.id.customPopup_bo1);
        bottle5 = amountPopup.findViewById(R.id.customPopup_bo5);
        bottle10 = amountPopup.findViewById(R.id.customPopup_bo10);
        bottle20 = amountPopup.findViewById(R.id.customPopup_bo20);

        addButton = findViewById(R.id.customPopup_addButton);

        crate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd += 1;
                amountCrates.setText(Integer.toString(cratesToAdd));
            }
        });
        crate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd += 5;
                amountCrates.setText(Integer.toString(cratesToAdd));
            }
        });
        crate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd += 10;
                amountCrates.setText(Integer.toString(cratesToAdd));
            }
        });
        crate20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd += 20;
                amountCrates.setText(Integer.toString(cratesToAdd));
            }
        });

        bottle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd += 1;
                amountBottles.setText(Integer.toString(bottlesToAdd));
            }
        });
        bottle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd += 5;
                amountBottles.setText(Integer.toString(bottlesToAdd));
            }
        });
        bottle10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd += 10;
                amountBottles.setText(Integer.toString(bottlesToAdd));
            }
        });
        bottle20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd += 20;
                amountBottles.setText(Integer.toString(bottlesToAdd));
            }
        });

        amountPopup.show();
    }

    protected void onStop(){
        super.onStop();
        if(requestQueue != null){
            requestQueue.cancelAll(REQ_TAG);
        }
    }

    public void getJsonResponsePost(JSONArray array, String url){
        url = url;

        JSONArray json;
        json = array;
        System.out.println("JSON Array: " + json);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.POST, url, json, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(REQ_TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(REQ_TAG, "ERROR GETTING RESPONSE");
            }
        }){

            @Override
            public Map<String , String> getHeaders() throws AuthFailureError{
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", "My useragent");
                return headers;
            }
        };
        jsonObjectRequest.setTag(REQ_TAG);
        requestQueue.add(jsonObjectRequest);
    }
}
