package virgo.larsverhulst.nl.virgoinventaristool.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import virgo.larsverhulst.nl.virgoinventaristool.MainActivity;
import virgo.larsverhulst.nl.virgoinventaristool.R;
import virgo.larsverhulst.nl.virgoinventaristool.Util.InvItem;

public class EditRyclerPopup {
    private Dialog editPopup;

    private Context context;

    private MainActivity mainActivity;

    private int bottlesToAdd;
    private int cratesToAdd;

    public EditRyclerPopup(Context c, MainActivity ma){
        context = c;
        mainActivity = ma;

        bottlesToAdd = 0;
        cratesToAdd = 0;

        editPopup = new Dialog(context);
    }
    public void ShowPopup(final int Position){
        final TextView titleTextView;

        final TextView amountCrates;
        final TextView amountBottles;

        final InvItem item = mainActivity.getItem(Position);
        cratesToAdd = item.getCrates();
        bottlesToAdd = item.getBottles();

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

        Button changeButton;
        Button removeButton;

        editPopup.setContentView(R.layout.edit_recycler_popup);

        titleTextView = editPopup.findViewById(R.id.editRecylerPopup_Drinkname);
        titleTextView.setText(item.getNameOfDrink());

        amountCrates = editPopup.findViewById(R.id.editRecylerPopup_amountOfCrates);
        amountCrates.setText(Integer.toString(cratesToAdd));
        amountBottles = editPopup.findViewById(R.id.editRecylerPopup_amountOfBottles);
        amountBottles.setText(Integer.toString(bottlesToAdd));

        crate1 = editPopup.findViewById(R.id.editRecylerPopup_cr1);
        crateMin1 = editPopup.findViewById(R.id.editRecylerPopup_crM1);
        crate5 = editPopup.findViewById(R.id.editRecylerPopup_cr5);
        crateMin5 = editPopup.findViewById(R.id.editRecylerPopup_crM5);
        crate10 = editPopup.findViewById(R.id.editRecylerPopup_cr10);
        crateMin10 = editPopup.findViewById(R.id.editRecylerPopup_crM10);
        crate20 = editPopup.findViewById(R.id.editRecylerPopup_cr20);
        crateMin20 = editPopup.findViewById(R.id.editRecylerPopup_crM20);

        bottle1 = editPopup.findViewById(R.id.editRecylerPopup_bo1);
        bottleMin1 = editPopup.findViewById(R.id.editRecylerPopup_boM1);
        bottle5 = editPopup.findViewById(R.id.editRecylerPopup_bo5);
        bottleMin5 = editPopup.findViewById(R.id.editRecylerPopup_boM5);
        bottle10 = editPopup.findViewById(R.id.editRecylerPopup_bo10);
        bottleMin10 = editPopup.findViewById(R.id.editRecylerPopup_boM10);
        bottle20 = editPopup.findViewById(R.id.editRecylerPopup_bo20);
        bottleMin20 = editPopup.findViewById(R.id.editRecylerPopup_boM20);

        changeButton = editPopup.findViewById(R.id.editRecylerPopup_changeButton);
        removeButton = editPopup.findViewById(R.id.editRecylerPopup_removeButton);

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

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottlesToAdd == 0 && cratesToAdd == 0){
                    editPopup.dismiss();
                }else{
                    mainActivity.editListItem(Position, cratesToAdd, bottlesToAdd);
                    editPopup.dismiss();
                }


            }
        });
        removeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mainActivity.removeItem(Position);
                editPopup.dismiss();
            }
        });

        editPopup.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bottlesToAdd = 0;
                cratesToAdd = 0;
            }
        });

        editPopup.show();
    }
}
