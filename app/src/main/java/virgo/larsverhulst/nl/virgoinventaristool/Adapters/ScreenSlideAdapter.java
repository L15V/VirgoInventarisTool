package virgo.larsverhulst.nl.virgoinventaristool.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import virgo.larsverhulst.nl.virgoinventaristool.Fragments.AlcoholFragment;
import virgo.larsverhulst.nl.virgoinventaristool.Fragments.ColdDrinkFragment;

public class ScreenSlideAdapter extends FragmentPagerAdapter {
    private final int TOTAL_ITEMS_IN_PAGER = 2;

    public ScreenSlideAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int index){
        Fragment fragment = null;
        switch (index) {
            case 0:
                fragment = new ColdDrinkFragment();
                break;
            case 1:
                fragment = new AlcoholFragment();
                break;
        }
            return fragment;
    }

    @Override
    public int getCount(){
        return TOTAL_ITEMS_IN_PAGER;
    }
}
