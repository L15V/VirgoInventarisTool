package virgo.larsverhulst.nl.virgoinventaristool.Util;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

import virgo.larsverhulst.nl.virgoinventaristool.R;

public class CustomRangeFilter implements InputFilter {
    private Context context;

    private int minValue;
    private int maxValue;

    public CustomRangeFilter(Context c, int minVal, int maxVal) {
        context = c;

        this.minValue = minVal;
        this.maxValue = maxVal;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd) {
        Toast toast = Toast.makeText(context, context.getResources().getString(R.string.nAllowedValue), Toast.LENGTH_SHORT);
        try {
            // Remove the string out of destination that is to be replaced
            String newVal = dest.toString().substring(0, dStart) + dest.toString().substring(dEnd, dest.toString().length());
            newVal = newVal.substring(0, dStart) + source.toString() + newVal.substring(dStart, newVal.length());
            int input = Integer.parseInt(newVal);

            if (isInRange(minValue, maxValue, input)) {
                return null;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        toast.show();
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}