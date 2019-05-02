package virgo.larsverhulst.nl.virgoinventaristool.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import virgo.larsverhulst.nl.virgoinventaristool.Dialogs.DrinkPopup;
import virgo.larsverhulst.nl.virgoinventaristool.MainActivity;
import virgo.larsverhulst.nl.virgoinventaristool.R;


public class AlcoholFragment extends Fragment implements View.OnClickListener {
    private Button hertogJanButton;
    private Button jupilerButton;
    private Button liefmansButton;
    private Button leffeBlondButton;
    private Button palmButton;
    private Button hoegaardeButton;
    private Button witteWijnButton;
    private Button rodeWijnButton;
    private Button bacardiButton;
    private Button bacardiRazzButton;

    private MainActivity activity;

    DrinkPopup drinkPopup;

    private String kindofDrink = "alcohol";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_alcohol, container, false);
        activity = (MainActivity) getActivity();

        drinkPopup = new DrinkPopup(getContext(), activity);

        hertogJanButton = v.findViewById(R.id.alcohol_hertogJan);
        jupilerButton = v.findViewById(R.id.alcohol_jupiler);
        liefmansButton = v.findViewById(R.id.alcohol_liefmans);
        leffeBlondButton = v.findViewById(R.id.alcohol_leffe_blond);
        palmButton = v.findViewById(R.id.alcohol_palm);
        hoegaardeButton = v.findViewById(R.id.alcohol_hoegaarde);
        witteWijnButton = v.findViewById(R.id.alcohol_witte_wijn);
        rodeWijnButton = v.findViewById(R.id.alcohol_rode_wijn);
        bacardiButton = v.findViewById(R.id.alcohol_bacardi);
        bacardiRazzButton = v.findViewById(R.id.alcohol_bacardi_razz);

        hertogJanButton.setOnClickListener(this);
        jupilerButton.setOnClickListener(this);
        liefmansButton.setOnClickListener(this);
        leffeBlondButton.setOnClickListener(this);
        palmButton.setOnClickListener(this);
        hoegaardeButton.setOnClickListener(this);
        witteWijnButton.setOnClickListener(this);
        rodeWijnButton.setOnClickListener(this);
        bacardiButton.setOnClickListener(this);
        bacardiRazzButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alcohol_hertogJan:
                drinkPopup.ShowPopup("hertog_jan" , kindofDrink, "Hertog Jan");
                break;
            case R.id.alcohol_jupiler:
                drinkPopup.ShowPopup("jupiler" , kindofDrink, "Jupiler");
                break;
            case R.id.alcohol_liefmans:
                drinkPopup.ShowPopup("liefmans" , kindofDrink, "Liefmans");
                break;
            case R.id.alcohol_leffe_blond:
                drinkPopup.ShowPopup("leffe_blond" , kindofDrink, "Leffe blond");
                break;
            case R.id.alcohol_palm:
                drinkPopup.ShowPopup("palm" , kindofDrink, "Palm");
                break;
            case R.id.alcohol_hoegaarde:
                drinkPopup.ShowPopup("hoegaarde" , kindofDrink, "Hoegaarde");
                break;
            case R.id.alcohol_witte_wijn:
                drinkPopup.ShowPopup("witte_wijn" , kindofDrink, "Witte wijn");
                break;
            case R.id.alcohol_rode_wijn:
                drinkPopup.ShowPopup("rode_wijn" , kindofDrink, "Rode wijn");
                break;
            case R.id.alcohol_bacardi:
                drinkPopup.ShowPopup("bacardi" , kindofDrink, "Bacardi");
                break;
            case R.id.alcohol_bacardi_razz:
                drinkPopup.ShowPopup("bacardi_razz" , kindofDrink, "Bacardi Razz");
                break;
        }
    }
}
