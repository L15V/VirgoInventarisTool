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
import android.widget.Switch;
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
    private JsonAlcoholParser alcoholParser;
    private JsonColdDrinksParser coldDrinksParser;

    private RequestQueue requestQueue;

    private final String REQ_TAG = "VACTIVITY";

    private List<InvItem> items;

    private boolean isDone = false;

    private int cratesToAdd = 0;
    private int bottlesToAdd = 0;


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

        this.alcoholParser = new JsonAlcoholParser(this);
        this.coldDrinksParser = new JsonColdDrinksParser(this);

        requestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        items = new ArrayList<>();
        navigationButtons = new ArrayList<>();

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_old_android);
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen


        amountPopup = new Dialog(this);


        /**
         * Assignments to the layout items
         */
        inButton = findViewById(R.id.mainScreen_inButton);
        inButton.setOnClickListener(this);

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

        for (int i = 0; i < navigationButtons.size(); i++) {
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
                switch (index) {
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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mainScreen_coldDrinkButton:
                viewPager.setCurrentItem(0);
                setButtonEnableColor(coldDrinkButton);
                break;
            case R.id.mainScreen_alcoholButton:
                viewPager.setCurrentItem(1);
                setButtonEnableColor(alcoholButton);
                break;
            case R.id.mainScreen_clothesButton:

                break;
            case R.id.mainScreen_inButton:
                addItemsInDb();
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
     *
     * @param b button to change in color
     */

    public void setButtonEnableColor(Button b) {
        allButtonDefaultColor();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            b.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.DarkBlue)));
        } else {
            b.setBackgroundColor(getResources().getColor(R.color.DarkBlue));
        }
        b.setTextColor(getResources().getColor(R.color.white));

    }

    /**
     * Method to set all the buttons to a light blue background
     */
    public void allButtonDefaultColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            coldDrinkButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            alcoholButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            foodButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            merchButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            clothesButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
            otherButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.LightBlue)));
        } else {
            coldDrinkButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            alcoholButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            foodButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            merchButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            clothesButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
            otherButton.setBackgroundColor(getResources().getColor(R.color.LightBlue));
        }
    }

    public void ShowPopup(View v) {

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

    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(REQ_TAG);
        }
    }

    public void getJsonResponsePost(JSONArray array, String url) {
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
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", "My useragent");
                return headers;
            }
        };
        jsonObjectRequest.setTag(REQ_TAG);
        requestQueue.add(jsonObjectRequest);
    }

    public List<InvItem> getItems() {
        return items;
    }

    public void setItems(List<InvItem> items) {
        this.items = items;
    }

    public void addItemtoList(InvItem item) {
        items.add(item);
        invAdapter.update(items);
        invAdapter.notifyDataSetChanged();
    }

    /**
     * Edit a item from the list
     *
     * @param index   - the index of the item you want to edit.
     * @param crates  - new crates value.
     * @param bottles - new bottles value.
     */
    public void editListItem(int index, int crates, int bottles) {

        items.get(index).setCrates(crates);
        items.get(index).setBottles(bottles);
    }

    public void addItemsInDb() {
        try {
            coldDrinksParser.getLatestDrinks();
            alcoholParser.getLatestDrinks();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (InvItem i : items) {
            if (i.getKindOfDrink().equals("cold_drink")) {
                switch (i.getNameOfDrink()) {
                    case "cola":
                        coldDrinksParser.addCola(i.getTotalToAdd());
                        break;
                    case "cola_zero":
                        coldDrinksParser.addCola_zero(i.getTotalToAdd());
                        break;
                    case "sprite":
                        coldDrinksParser.addSprite(i.getTotalToAdd());
                        break;
                    case "fanta":
                        coldDrinksParser.addFanta(i.getTotalToAdd());
                        break;
                    case "cassis":
                        coldDrinksParser.addCassis(i.getTotalToAdd());
                        break;
                    case "redbull":
                        coldDrinksParser.addRedbull(i.getTotalToAdd());
                        break;
                    case "fuze_green" :
                        coldDrinksParser.addFuze_green(i.getTotalToAdd());
                        break;
                    case "fuze_sparkling":
                        coldDrinksParser.addFuze_sparkling(i.getTotalToAdd());
                        break;
                    case "fuze_blacktea":
                        coldDrinksParser.addFuze_blacktea(i.getTotalToAdd());
                        break;
                    case "o2_geel":
                        coldDrinksParser.addO2_geel(i.getTotalToAdd());
                        break;
                    case "o2_rood":
                        coldDrinksParser.addO2_rood(i.getTotalToAdd());
                        break;
                    case "o2_groen":
                        coldDrinksParser.addO2_groen(i.getTotalToAdd());
                        break;
                    case "fristi":
                        coldDrinksParser.addFristi(i.getTotalToAdd());
                        break;
                    case "chocomel":
                        coldDrinksParser.addChocomel(i.getTotalToAdd());
                        break;
                    case "spa_rood":
                        coldDrinksParser.addSpa_rood(i.getTotalToAdd());
                        break;
                }
            }
        }

        try {
            getJsonResponsePost(coldDrinksParser.getColdDrinksJSON(), "http://192.168.0.19:8080/insertcolddrinks/");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
