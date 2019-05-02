package virgo.larsverhulst.nl.virgoinventaristool.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import virgo.larsverhulst.nl.virgoinventaristool.MainActivity;
import virgo.larsverhulst.nl.virgoinventaristool.R;
import virgo.larsverhulst.nl.virgoinventaristool.Util.CustomRangeFilter;
import virgo.larsverhulst.nl.virgoinventaristool.Util.LocaleHelper;

public class SettingsPopup{
    private Dialog settingsPopup;

    private Context context;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private EditText ip;
    private EditText port;

    private String kindOfIp;

    public SettingsPopup(Context c){
        context = c;
        editor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        settingsPopup = new Dialog(context);
    }

    public void showSettingsPopup() {
        settingsPopup.setContentView(R.layout.settings_popup);

        kindOfIp = prefs.getString("kindofip" , "ip");
        ip = settingsPopup.findViewById(R.id.settings_ip);
        port = settingsPopup.findViewById(R.id.settings_port);
        final TextView ipTitle = settingsPopup.findViewById(R.id.settings_iptitle);
        final TextView currentIp = settingsPopup.findViewById(R.id.settings_currentIp);
        final TextView currentPort = settingsPopup.findViewById(R.id.settings_currentPort);

        Button applyButton = settingsPopup.findViewById(R.id.settings_apply);
        Button closeButton = settingsPopup.findViewById(R.id.settings_closeButton);

        final RadioButton ipRadio = settingsPopup.findViewById(R.id.settings_radioIp);
        final RadioButton urlRadio = settingsPopup.findViewById(R.id.settings_radioURL);
        RadioGroup rg = settingsPopup.findViewById(R.id.settings_radioGroup);

        ip.setText("");
        port.setText("");

        if(prefs.getString("kindofip" , "ip").equals("ip")){
            ipRadio.setChecked(true);
            ipTitle.setText(context.getResources().getString(R.string.ipAdres));
            ip.setHint("0.0.0.0");
            ip.setInputType(InputType.TYPE_CLASS_PHONE);

            ip.setFilters(getIpFilter());
        }else{
            urlRadio.setChecked(true);
            ipTitle.setText("URL");
            ip.setHint("http://adres.com");
            ip.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
            ip.setFilters(new InputFilter[] {});
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = group.findViewById(checkedId);
                int index = group.indexOfChild(radioButton);

                switch (index) {
                    case 0:
                        kindOfIp = "ip";
                        editor.putString("kindofip" , "ip");
                        editor.commit();
                        ipRadio.setChecked(true);
                        ipTitle.setText(context.getResources().getString(R.string.ipAdres));
                        ip.setHint("0.0.0.0");
                        ip.setInputType(InputType.TYPE_CLASS_PHONE);
                        ip.setText("");
                        break;
                    case 1:
                        kindOfIp = "url";
                        editor.putString("kindofip" , "url");
                        editor.commit();
                        urlRadio.setChecked(true);
                        ipTitle.setText("URL");
                        ip.setHint("http://adres.com");
                        ip.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
                        ip.setText("");
                }
                System.out.println("kind of ip: " + prefs.getString("kindofip" , "ip"));

                if(prefs.getString("kindofip" , "ip").equals("ip")){
                    ip.setFilters(getIpFilter());
                }else{
                    ip.setFilters(new InputFilter[] {});
                }
            }
        });

        port.setFilters(new InputFilter[]{new CustomRangeFilter(context, 0, 65535)});

        currentIp.setText(prefs.getString("ip" , "0.0.0.0:"));
        currentPort.setText(prefs.getString("port", "0"));

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sIp = ip.getText().toString();
                String sPort = port.getText().toString();

                if(prefs.getString("kindofip" , "ip").equals("url")){
                    if(URLUtil.isValidUrl(sIp)){
                        editor.putString("ip" , sIp + ":");
                        editor.commit();
                        currentIp.setText(prefs.getString("ip" , "0.0.0.0:"));
                    }else{
                        Toast.makeText(context, context.getResources().getString(R.string.nAllowedValue) , Toast.LENGTH_SHORT).show();
                    }
                }
                else if(!sIp.equals("")){
                    editor.putString("ip" ,"http://" +  sIp + ":");
                    editor.commit();
                    currentIp.setText(prefs.getString("ip" , "0.0.0.0:"));
                }

                if(!sPort.equals("")){
                    editor.putString("port" , sPort);
                    editor.commit();
                    currentPort.setText(prefs.getString("port", "0"));
                }
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsPopup.dismiss();
            }
        });

        settingsPopup.show();
    }

    public InputFilter[] getIpFilter(){
        InputFilter[] ipFilters = new InputFilter[1];
        ipFilters[0] = new InputFilter() {
            Toast toast = Toast.makeText(context, context.getResources().getString(R.string.wrongArgument), Toast.LENGTH_SHORT);
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       android.text.Spanned dest, int dstart, int dend) {
                if (end > start) {
                    String destTxt = dest.toString();
                    String resultingTxt = destTxt.substring(0, dstart)
                            + source.subSequence(start, end)
                            + destTxt.substring(dend);
                    if (!resultingTxt
                            .matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                        toast.show();
                        return "";
                    } else {
                        String[] splits = resultingTxt.split("\\.");
                        for (int i = 0; i < splits.length; i++) {
                            if (Integer.valueOf(splits[i]) > 255) {
                                toast.show();
                                return "";
                            }
                        }
                    }
                }
                return null;
            }

        };

        return ipFilters;
    }

}
