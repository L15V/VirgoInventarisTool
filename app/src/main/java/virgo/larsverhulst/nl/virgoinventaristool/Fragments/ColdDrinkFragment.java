package virgo.larsverhulst.nl.virgoinventaristool.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import virgo.larsverhulst.nl.virgoinventaristool.MainActivity;
import virgo.larsverhulst.nl.virgoinventaristool.Parsers.JsonAlcoholParser;
import virgo.larsverhulst.nl.virgoinventaristool.Parsers.JsonColdDrinksParser;
import virgo.larsverhulst.nl.virgoinventaristool.R;
import virgo.larsverhulst.nl.virgoinventaristool.Util.InvItem;


public class ColdDrinkFragment extends Fragment implements View.OnClickListener{
    private Button colaButton;
    private Button colaZeroButton;
    private Button spriteButton;
    private Button fantaButton;
    private Button cassisButton;
    private Button redbullButon;
    private Button fuzeGreenButton;
    private Button fuzeSparklingButton;
    private Button fuzeBlackteaButton;
    private Button o2GeelButton;
    private Button o2RoodButton;
    private Button o2GroenButton;
    private Button fristiButton;
    private Button chocomelButton;
    private Button spaRoodButton;

    private Context context;
    private MainActivity activity;

    Dialog amountPopup;


    private int cratesToAdd = 0;
    private int bottlesToAdd = 0;

    private String kindofDrink = "cold_drink";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cold_drink, container, false);
        activity = (MainActivity) getActivity();

        amountPopup = new Dialog(v.getContext());

        colaButton = v.findViewById(R.id.cold_drinks_cola);
        colaZeroButton = v.findViewById(R.id.cold_drinks_colaZero);
        spriteButton = v.findViewById(R.id.cold_drinks_sprite);
        fantaButton = v.findViewById(R.id.cold_drinks_fanta);
        cassisButton = v.findViewById(R.id.cold_drinks_cassis);
        redbullButon = v.findViewById(R.id.cold_drinks_redbull);
        fuzeGreenButton = v.findViewById(R.id.cold_drinks_fuze_green);
        fuzeSparklingButton = v.findViewById(R.id.cold_drinks_fuze_sparkling);
        fuzeBlackteaButton = v.findViewById(R.id.cold_drinks_fuze_blacktea);
        o2GeelButton = v.findViewById(R.id.cold_drinks_o2Geel);
        o2RoodButton = v.findViewById(R.id.cold_drinks_o2Rood);
        o2GroenButton = v.findViewById(R.id.cold_drinks_o2Groen);
        fristiButton = v.findViewById(R.id.cold_drinks_fristi);
        chocomelButton = v.findViewById(R.id.cold_drinks_chocomel);
        spaRoodButton = v.findViewById(R.id.cold_drinks_spaRood);

        colaButton.setOnClickListener(this);
        colaZeroButton.setOnClickListener(this);
        spriteButton.setOnClickListener(this);
        fantaButton.setOnClickListener(this);
        cassisButton.setOnClickListener(this);
        redbullButon.setOnClickListener(this);
        fuzeGreenButton.setOnClickListener(this);
        fuzeSparklingButton.setOnClickListener(this);
        fuzeBlackteaButton.setOnClickListener(this);
        o2GeelButton.setOnClickListener(this);
        o2RoodButton.setOnClickListener(this);
        o2GroenButton.setOnClickListener(this);
        fristiButton.setOnClickListener(this);
        chocomelButton.setOnClickListener(this);
        spaRoodButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.cold_drinks_cola:
                ShowPopup("cola" , kindofDrink, "Cola");
                break;
            case R.id.cold_drinks_colaZero:
                ShowPopup("cola_zero" , kindofDrink, "Cola Zero");
                break;
            case R.id.cold_drinks_sprite:
                ShowPopup("sprite" , kindofDrink, "Sprite");
                break;
            case R.id.cold_drinks_fanta:
                ShowPopup("fanta" , kindofDrink, "Famta");
                break;
            case R.id.cold_drinks_cassis:
                ShowPopup("cassis", kindofDrink, "Cassis");
                break;
            case R.id.cold_drinks_redbull:
                ShowPopup("redbull", kindofDrink, "Redbull");
                break;
            case R.id.cold_drinks_fuze_green:
                ShowPopup("fuze_green", kindofDrink, "Fuzetea green");
                break;
            case R.id.cold_drinks_fuze_sparkling:
                ShowPopup("fuze_sparkling", kindofDrink, "Fuzetea sparkling");
                break;
            case R.id.cold_drinks_fuze_blacktea:
                ShowPopup("fuze_blacktea", kindofDrink, "Fuzetea blacktea");
                break;
            case R.id.cold_drinks_o2Geel:
                ShowPopup("o2_geel", kindofDrink, "O2 geel");
                break;
            case R.id.cold_drinks_o2Rood:
                ShowPopup("o2_rood" , kindofDrink, "O2 rood");
                break;
            case R.id.cold_drinks_o2Groen:
                ShowPopup("o2_groen", kindofDrink, "O2 groen");
                break;
            case R.id.cold_drinks_fristi:
                ShowPopup("fristi", kindofDrink, "Fristi");
                break;
            case R.id.cold_drinks_chocomel:
                ShowPopup("chocomel", kindofDrink, "Chocomel");
                break;
            case R.id.cold_drinks_spaRood:
                ShowPopup("spa_rood", kindofDrink, "Spa rood");
                break;
        }
    }



    public void ShowPopup(final String drinkName, final String kindOfDrink, String title){
        final TextView titleTextView;

        final TextView amountCrates;
        final TextView amountBottles;

        Button crate1;
        Button crateMin1;
        Button crate5;
        Button crateMin5;
        Button crate10;
        Button crateMin10;
        Button crate20;
        Button crateMin20;

        Button bottle1;
        Button bottleMin1;
        Button bottle5;
        Button bottleMin5;
        Button bottle10;
        Button bottleMin10;
        Button bottle20;
        Button bottleMin20;

        Button addButton;

        amountPopup.setContentView(R.layout.custom_popup);

        titleTextView = amountPopup.findViewById(R.id.customPopup_Drinkname);
        titleTextView.setText(title);

        amountCrates = amountPopup.findViewById(R.id.customPopup_amountOfCrates);
        amountCrates.setText(Integer.toString(cratesToAdd));
        amountBottles = amountPopup.findViewById(R.id.customPopup_amountOfBottles);
        amountBottles.setText(Integer.toString(bottlesToAdd));

        crate1 = amountPopup.findViewById(R.id.customPopup_cr1);
        crateMin1 = amountPopup.findViewById(R.id.customPopup_crM1);
        crate5 = amountPopup.findViewById(R.id.customPopup_cr5);
        crateMin5 = amountPopup.findViewById(R.id.customPopup_crM5);
        crate10 = amountPopup.findViewById(R.id.customPopup_cr10);
        crateMin10 = amountPopup.findViewById(R.id.customPopup_crM10);
        crate20 = amountPopup.findViewById(R.id.customPopup_cr20);
        crateMin20 = amountPopup.findViewById(R.id.customPopup_crM20);

        bottle1 = amountPopup.findViewById(R.id.customPopup_bo1);
        bottleMin1 = amountPopup.findViewById(R.id.customPopup_boM1);
        bottle5 = amountPopup.findViewById(R.id.customPopup_bo5);
        bottleMin5 = amountPopup.findViewById(R.id.customPopup_boM5);
        bottle10 = amountPopup.findViewById(R.id.customPopup_bo10);
        bottleMin10 = amountPopup.findViewById(R.id.customPopup_boM10);
        bottle20 = amountPopup.findViewById(R.id.customPopup_bo20);
        bottleMin20 = amountPopup.findViewById(R.id.customPopup_boM20);

        addButton = amountPopup.findViewById(R.id.customPopup_addButton);

        crate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd += 1;
                amountCrates.setText(Integer.toString(cratesToAdd));
            }
        });
        crateMin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd -= 1;
                if(cratesToAdd < 0){
                    cratesToAdd = 0;
                    amountCrates.setText(Integer.toString(cratesToAdd));
                }
            }
        });
        crate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd += 5;
                amountCrates.setText(Integer.toString(cratesToAdd));
            }
        });
        crateMin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd -= 5;
                if(cratesToAdd < 0){
                    cratesToAdd = 0;
                    amountCrates.setText(Integer.toString(cratesToAdd));
                }
            }
        });
        crate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd += 10;
                amountCrates.setText(Integer.toString(cratesToAdd));
            }
        });
        crateMin10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd -= 10;
                if(cratesToAdd < 0){
                    cratesToAdd = 0;
                    amountCrates.setText(Integer.toString(cratesToAdd));
                }
            }
        });
        crate20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd += 20;
                amountCrates.setText(Integer.toString(cratesToAdd));
            }
        });
        crateMin20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd -= 20;
                if(cratesToAdd < 0){
                    cratesToAdd = 0;
                    amountCrates.setText(Integer.toString(cratesToAdd));
                }
            }
        });

        bottle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd += 1;
                amountBottles.setText(Integer.toString(bottlesToAdd));
            }
        });
        bottleMin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd -= 1;
                if(bottlesToAdd < 0){
                    bottlesToAdd = 0;
                    amountBottles.setText(Integer.toString(bottlesToAdd));
                }
            }
        });
        bottle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd += 5;
                amountBottles.setText(Integer.toString(bottlesToAdd));
            }
        });
        bottleMin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd -= 5;
                if(bottlesToAdd < 0){
                    bottlesToAdd = 0;
                    amountBottles.setText(Integer.toString(bottlesToAdd));
                }
            }
        });
        bottle10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd += 10;
                amountBottles.setText(Integer.toString(bottlesToAdd));
            }
        });
        bottleMin10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd -= 10;
                if(bottlesToAdd < 0){
                    bottlesToAdd = 0;
                    amountBottles.setText(Integer.toString(bottlesToAdd));
                }
            }
        });
        bottle20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd += 20;
                amountBottles.setText(Integer.toString(bottlesToAdd));
            }
        });
        bottleMin20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd -= 20;
                if(bottlesToAdd < 0){
                    bottlesToAdd = 0;
                    amountBottles.setText(Integer.toString(bottlesToAdd));
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvItem item = new InvItem(drinkName, kindOfDrink , cratesToAdd , bottlesToAdd);
                activity.addItemtoList(item);
                bottlesToAdd = 0;
                cratesToAdd = 0;
                amountPopup.dismiss();

            }
        });

        amountPopup.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bottlesToAdd = 0;
                cratesToAdd = 0;
            }
        });

        amountPopup.show();
    }

}
