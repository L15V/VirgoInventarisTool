package virgo.larsverhulst.nl.virgoinventaristool.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import virgo.larsverhulst.nl.virgoinventaristool.Dialogs.DrinkPopup;
import virgo.larsverhulst.nl.virgoinventaristool.MainActivity;
import virgo.larsverhulst.nl.virgoinventaristool.R;


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

    DrinkPopup drinkPopup;

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

        drinkPopup = new DrinkPopup(getContext(), activity);

        colaButton = v.findViewById(R.id.alcohol_hertogJan);
        colaZeroButton = v.findViewById(R.id.alcohol_bacardi);
        spriteButton = v.findViewById(R.id.alcohol_jupiler);
        fantaButton = v.findViewById(R.id.alcohol_liefmans);
        cassisButton = v.findViewById(R.id.alcohol_rode_wijn);
        redbullButon = v.findViewById(R.id.alcohol_leffe_blond);
        fuzeGreenButton = v.findViewById(R.id.alcohol_palm);
        fuzeSparklingButton = v.findViewById(R.id.alcohol_bacardi_razz);
        fuzeBlackteaButton = v.findViewById(R.id.alcohol_hoegaarde);
        o2GeelButton = v.findViewById(R.id.alcohol_witte_wijn);
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

            case R.id.alcohol_hertogJan:
                drinkPopup.ShowPopup("cola" , kindofDrink, "Cola");
                break;
            case R.id.alcohol_bacardi:
                drinkPopup.ShowPopup("cola_zero" , kindofDrink, "Cola Zero");
                break;
            case R.id.alcohol_jupiler:
                drinkPopup.ShowPopup("sprite" , kindofDrink, "Sprite");
                break;
            case R.id.alcohol_liefmans:
                drinkPopup.ShowPopup("fanta" , kindofDrink, "Famta");
                break;
            case R.id.alcohol_rode_wijn:
                drinkPopup.ShowPopup("cassis", kindofDrink, "Cassis");
                break;
            case R.id.alcohol_leffe_blond:
                drinkPopup.ShowPopup("redbull", kindofDrink, "Redbull");
                break;
            case R.id.alcohol_palm:
                drinkPopup.ShowPopup("fuze_green", kindofDrink, "Fuzetea green");
                break;
            case R.id.alcohol_bacardi_razz:
                drinkPopup.ShowPopup("fuze_sparkling", kindofDrink, "Fuzetea sparkling");
                break;
            case R.id.alcohol_hoegaarde:
                drinkPopup.ShowPopup("fuze_blacktea", kindofDrink, "Fuzetea blacktea");
                break;
            case R.id.alcohol_witte_wijn:
                drinkPopup.ShowPopup("o2_geel", kindofDrink, "O2 geel");
                break;
            case R.id.cold_drinks_o2Rood:
                drinkPopup.ShowPopup("o2_rood" , kindofDrink, "O2 rood");
                break;
            case R.id.cold_drinks_o2Groen:
                drinkPopup.ShowPopup("o2_groen", kindofDrink, "O2 groen");
                break;
            case R.id.cold_drinks_fristi:
                drinkPopup.ShowPopup("fristi", kindofDrink, "Fristi");
                break;
            case R.id.cold_drinks_chocomel:
                drinkPopup.ShowPopup("chocomel", kindofDrink, "Chocomel");
                break;
            case R.id.cold_drinks_spaRood:
                drinkPopup.ShowPopup("spa_rood", kindofDrink, "Spa rood");
                break;
        }
    }
}
