package mikheev.konstantin.huntmap;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;
import mikheev.konstantin.huntmap.adapters.MyViewPagerAdapter;
import mikheev.konstantin.huntmap.fragments.AllMapsFragment;
import mikheev.konstantin.huntmap.fragments.MyMapsFragment;
import mikheev.konstantin.huntmap.models.RegionItem;
import mikheev.konstantin.huntmap.models.RegionItemsMap;

public class MapsActivity extends AppCompatActivity
        implements AllMapsFragment.AllMapsInterface, MyMapsFragment.MyMapsInterface {

    private MyViewPagerAdapter myViewPagerAdapter;
    private ViewPager mViewPager;
    private RegionItemsMap allRegionItemsMap;
    private RegionItemsMap myRegionItemsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDatasetAllMaps();
        initDatasetMyMaps();

        setContentView(R.layout.activity_maps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(myViewPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public List<RegionItem> getAllMaps() {
        return allRegionItemsMap.getRegionItemsList();
    }

    public List<RegionItem> getMyMaps() {
        return myRegionItemsMap.getRegionItemsList();
    }

    public void addToMyMaps(List<RegionItem> regionItemListFromFragment) {
        for (RegionItem regionItem : regionItemListFromFragment) {
            if (regionItem != null && regionItem.getIsBought() && !regionItemListFromFragment.contains(regionItem)) {
                myRegionItemsMap.addRegionItem(regionItem.getRegionId(), regionItem);
            }
        }
        Fragment fragmentMyMaps = myViewPagerAdapter.getFragmentByPosition(0);
        ((MyMapsFragment) fragmentMyMaps).updateMyMapsItems(myRegionItemsMap.getRegionItemsList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initDatasetAllMaps() {
        RegionItem regionItem1 = new RegionItem();
        regionItem1.setRegionName("Нижегородская обл.");
        regionItem1.setRegionPrice(366);
        regionItem1.setIsBought(false);

        RegionItem regionItem2 = new RegionItem();
        regionItem2.setRegionName("Чувашская республика");
        regionItem2.setRegionPrice(290);
        regionItem2.setIsBought(false);

        RegionItem regionItem3 = new RegionItem();
        regionItem3.setRegionName("Марий Эл");
        regionItem3.setRegionPrice(233);
        regionItem3.setIsBought(false);

        RegionItem regionItem4 = new RegionItem();
        regionItem4.setRegionName("Самарская обл.");
        regionItem4.setRegionPrice(655);
        regionItem4.setIsBought(false);

        RegionItem regionItem5 = new RegionItem();
        regionItem5.setRegionName("Кировская обл.");
        regionItem5.setRegionPrice(366);
        regionItem5.setIsBought(false);

        RegionItem regionItem6 = new RegionItem();
        regionItem6.setRegionName("Оренбурская обл.");
        regionItem6.setRegionPrice(212);
        regionItem6.setIsBought(false);

        RegionItem regionItem7 = new RegionItem();
        regionItem7.setRegionName("Кемеровская обл.");
        regionItem7.setRegionPrice(212);
        regionItem7.setIsBought(false);

        RegionItem regionItem8 = new RegionItem();
        regionItem8.setRegionName("Московская обл.");
        regionItem8.setRegionPrice(666);
        regionItem8.setIsBought(false);

        RegionItem regionItem9 = new RegionItem();
        regionItem9.setRegionName("Ленинградская обл.");
        regionItem9.setRegionPrice(735);
        regionItem9.setIsBought(false);

        RegionItem regionItem10 = new RegionItem();
        regionItem10.setRegionName("Ульяновская обл.");
        regionItem10.setRegionPrice(227);
        regionItem10.setIsBought(false);

        RegionItem regionItem11 = new RegionItem();
        regionItem11.setRegionName("Саранская обл.");
        regionItem11.setRegionPrice(237);
        regionItem11.setIsBought(false);

        RegionItem regionItem12 = new RegionItem();
        regionItem12.setRegionName("Пензенская обл.");
        regionItem12.setRegionPrice(374);
        regionItem12.setIsBought(false);

        allRegionItemsMap = new RegionItemsMap();
        allRegionItemsMap.addRegionItem(1, regionItem1);
        allRegionItemsMap.addRegionItem(2, regionItem2);
        allRegionItemsMap.addRegionItem(3, regionItem3);
        allRegionItemsMap.addRegionItem(4, regionItem4);
        allRegionItemsMap.addRegionItem(5, regionItem5);
        allRegionItemsMap.addRegionItem(6, regionItem6);
        allRegionItemsMap.addRegionItem(7, regionItem7);
        allRegionItemsMap.addRegionItem(8, regionItem8);
        allRegionItemsMap.addRegionItem(9, regionItem9);
        allRegionItemsMap.addRegionItem(10, regionItem10);
        allRegionItemsMap.addRegionItem(11, regionItem11);
        allRegionItemsMap.addRegionItem(12, regionItem12);
    }

    private void initDatasetMyMaps() {
        myRegionItemsMap = new RegionItemsMap();
    }
}
