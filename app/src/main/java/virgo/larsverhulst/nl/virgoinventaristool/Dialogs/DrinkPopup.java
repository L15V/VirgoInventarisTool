package virgo.larsverhulst.nl.virgoinventaristool.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import virgo.larsverhulst.nl.virgoinventaristool.MainActivity;
import virgo.larsverhulst.nl.virgoinventaristool.R;
import virgo.larsverhulst.nl.virgoinventaristool.Util.InvItem;

public class DrinkPopup {
    private Dialog drinkPopup;

    private Context context;

    private MainActivity mainActivity;

    private int bottlesToAdd;
    private int cratesToAdd;

    public DrinkPopup(Context c, MainActivity ma){
        context = c;
        mainActivity = ma;

        bottlesToAdd = 0;
        cratesToAdd = 0;

        drinkPopup = new Dialog(context);
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

        drinkPopup.setContentView(R.layout.custom_popup);

        titleTextView = drinkPopup.findViewById(R.id.customPopup_Drinkname);
        titleTextView.setText(title);

        amountCrates = drinkPopup.findViewById(R.id.customPopup_amountOfCrates);
        amountCrates.setText(Integer.toString(cratesToAdd));
        amountBottles = drinkPopup.findViewById(R.id.customPopup_amountOfBottles);
        amountBottles.setText(Integer.toString(bottlesToAdd));

        crate1 = drinkPopup.findViewById(R.id.customPopup_cr1);
        crateMin1 = drinkPopup.findViewById(R.id.customPopup_crM1);
        crate5 = drinkPopup.findViewById(R.id.customPopup_cr5);
        crateMin5 = drinkPopup.findViewById(R.id.customPopup_crM5);
        crate10 = drinkPopup.findViewById(R.id.customPopup_cr10);
        crateMin10 = drinkPopup.findViewById(R.id.customPopup_crM10);
        crate20 = drinkPopup.findViewById(R.id.customPopup_cr20);
        crateMin20 = drinkPopup.findViewById(R.id.customPopup_crM20);

        bottle1 = drinkPopup.findViewById(R.id.customPopup_bo1);
        bottleMin1 = drinkPopup.findViewById(R.id.customPopup_boM1);
        bottle5 = drinkPopup.findViewById(R.id.customPopup_bo5);
        bottleMin5 = drinkPopup.findViewById(R.id.customPopup_boM5);
        bottle10 = drinkPopup.findViewById(R.id.customPopup_bo10);
        bottleMin10 = drinkPopup.findViewById(R.id.customPopup_boM10);
        bottle20 = drinkPopup.findViewById(R.id.customPopup_bo20);
        bottleMin20 = drinkPopup.findViewById(R.id.customPopup_boM20);

        addButton = drinkPopup.findViewById(R.id.customPopup_addButton);

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
                }
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
        crateMin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd -= 5;
                if(cratesToAdd < 0){
                    cratesToAdd = 0;
                }
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
        crateMin10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd -= 10;
                if(cratesToAdd < 0){
                    cratesToAdd = 0;
                }
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
        crateMin20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cratesToAdd -= 20;
                if(cratesToAdd < 0){
                    cratesToAdd = 0;
                }
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
        bottleMin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd -= 1;
                if(bottlesToAdd < 0){
                    bottlesToAdd = 0;
                }
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
        bottleMin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd -= 5;
                if(bottlesToAdd < 0){
                    bottlesToAdd = 0;
                }
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
        bottleMin10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd -= 10;
                if(bottlesToAdd < 0){
                    bottlesToAdd = 0;
                }
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
        bottleMin20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottlesToAdd -= 20;
                if(bottlesToAdd < 0){
                    bottlesToAdd = 0;
                }
                amountBottles.setText(Integer.toString(bottlesToAdd));
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottlesToAdd == 0 && cratesToAdd == 0){
                    drinkPopup.dismiss();
                }else{
                    InvItem item = new InvItem(drinkName, kindOfDrink , cratesToAdd , bottlesToAdd);
                    mainActivity.addItemtoList(item);
                    bottlesToAdd = 0;
                    cratesToAdd = 0;
                    drinkPopup.dismiss();
                }


            }
        });

        drinkPopup.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bottlesToAdd = 0;
                cratesToAdd = 0;
            }
        });

        drinkPopup.show();
    }
}
