package mikheev.konstantin.huntmap.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import mikheev.konstantin.huntmap.fragments.AllMapsFragment;
import mikheev.konstantin.huntmap.fragments.MyMapsFragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private String fragments[] = {"My maps", "All maps"};

    public MyViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MyMapsFragment();
            case 1:
                return new AllMapsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
//                да, вынести в строковые ресурсы)
                return "Мои карты"; //TODO: getResources().getString(R.string.my_maps);
            case 1:
                return "Все карты";
        }
        return null;
    }
}
