package mikheev.konstantin.huntmap.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.WeakHashMap;
import mikheev.konstantin.huntmap.fragments.AllMapsFragment;
import mikheev.konstantin.huntmap.fragments.MyMapsFragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private String fragments[] = {"My maps", "All maps"};
    private WeakHashMap<Integer, Fragment> fragmentsMap = new WeakHashMap<>();

    public MyViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        fragmentsMap.put(0, new MyMapsFragment());
        fragmentsMap.put(1, new AllMapsFragment());
    }

    public Fragment getFragmentByPosition(int position) {
        return fragmentsMap.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragmentsMap.get(0);
            case 1:
                return fragmentsMap.get(1);
            default:
                return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragmentsMap.remove(position);
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Мои карты"; //TODO: getResources().getString(R.string.my_maps);
            case 1:
                return "Все карты";
        }
        return null;
    }
}
