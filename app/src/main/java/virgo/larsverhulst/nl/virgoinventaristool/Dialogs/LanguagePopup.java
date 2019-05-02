package virgo.larsverhulst.nl.virgoinventaristool.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import virgo.larsverhulst.nl.virgoinventaristool.MainActivity;
import virgo.larsverhulst.nl.virgoinventaristool.R;
import virgo.larsverhulst.nl.virgoinventaristool.Util.LocaleHelper;

public class LanguagePopup {
    private Dialog languagePopup;
    private Activity activity;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private Context context;

    public LanguagePopup(Context c, Activity ac){
        context = c;
        activity = ac;
        editor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        languagePopup = new Dialog(context);
    }

    public void showLanguagePopup() {
        languagePopup.setContentView(R.layout.language_popup);

        final Spinner languageSpinner = languagePopup.findViewById(R.id.languagePopup_spinner);
        Button applyButton = languagePopup.findViewById(R.id.languagePopup_applyButton);

        if(prefs.getString("lang" , "EN").equals("NL")){
            languageSpinner.setSelection(0);
        }else if(prefs.getString("lang" , "EN").equals("EN")){
            languageSpinner.setSelection(1);
        }

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int language = (int)languageSpinner.getSelectedItemId();

                switch(language){
                    case 0:
                        LocaleHelper.setLocale(context, "NL");
                        editor.putString("lang" , "NL");
                        editor.commit();
                        System.out.println("Dutch pushed Lang string: " + prefs.getString("lang" , "def"));
                        activity.recreate();
                        break;
                    case 1:
                        LocaleHelper.setLocale(context,"EN");
                        editor.putString("lang" , "EN");
                        editor.commit();
                        activity.recreate();
                        break;
                }
                languagePopup.dismiss();
            }
        });


        languagePopup.show();
    }
}
