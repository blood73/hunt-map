package mikheev.konstantin.huntmap.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import mikheev.konstantin.huntmap.R;
import mikheev.konstantin.huntmap.adapters.MyRegionsAdapter;
import mikheev.konstantin.huntmap.models.RegionItem;

public class MyMapsFragment extends Fragment implements MyRegionsAdapter.MyRegionViewHolder.ClickListener {

    private List<RegionItem> regionItems;
    private RecyclerView rvRegions;
    private MyRegionsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_maps, container, false);

        rvRegions = (RecyclerView) rootView.findViewById(R.id.rv_my_maps);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvRegions.setLayoutManager(llm);
        initializeAdapter();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClicked(int position) {
        //TODO: click logic
    }

     private void initializeAdapter() {
        adapter = new MyRegionsAdapter(regionItems, this);
        rvRegions.setAdapter(adapter);
    }

    private void initDataset() {
        regionItems = new ArrayList<>();

        RegionItem regionItem1 = new RegionItem();
        regionItem1.setRegionName("Нижегородская обл.");
        regionItem1.setRegionPrice(366);
        regionItem1.setIsBought(true);

        RegionItem regionItem2 = new RegionItem();
        regionItem2.setRegionName("Чувашская республика");
        regionItem2.setRegionPrice(290);
        regionItem2.setIsBought(true);

        RegionItem regionItem3 = new RegionItem();
        regionItem3.setRegionName("Марий Эл");
        regionItem3.setRegionPrice(233);
        regionItem3.setIsBought(true);

        RegionItem regionItem4 = new RegionItem();
        regionItem4.setRegionName("Самарская обл.");
        regionItem4.setRegionPrice(655);
        regionItem4.setIsBought(true);

        RegionItem regionItem5 = new RegionItem();
        regionItem5.setRegionName("Кировская обл.");
        regionItem5.setRegionPrice(366);
        regionItem5.setIsBought(true);

        RegionItem regionItem6 = new RegionItem();
        regionItem6.setRegionName("Оренбурская обл.");
        regionItem6.setRegionPrice(212);
        regionItem6.setIsBought(true);

        RegionItem regionItem7 = new RegionItem();
        regionItem7.setRegionName("Кемеровская обл.");
        regionItem7.setRegionPrice(212);
        regionItem7.setIsBought(true);

        RegionItem regionItem8 = new RegionItem();
        regionItem8.setRegionName("Кемеровская обл.");
        regionItem8.setRegionPrice(212);
        regionItem8.setIsBought(true);

        RegionItem regionItem9 = new RegionItem();
        regionItem9.setRegionName("Кемеровская обл.");
        regionItem9.setRegionPrice(212);
        regionItem9.setIsBought(true);

        RegionItem regionItem10 = new RegionItem();
        regionItem10.setRegionName("Кемеровская обл.");
        regionItem10.setRegionPrice(212);
        regionItem10.setIsBought(true);

        RegionItem regionItem11 = new RegionItem();
        regionItem11.setRegionName("Кемеровская обл.");
        regionItem11.setRegionPrice(212);
        regionItem11.setIsBought(true);

        RegionItem regionItem12 = new RegionItem();
        regionItem12.setRegionName("Кемеровская обл.");
        regionItem12.setRegionPrice(212);
        regionItem12.setIsBought(true);

        regionItems.add(regionItem1);
        regionItems.add(regionItem2);
        regionItems.add(regionItem3);
        regionItems.add(regionItem4);
        regionItems.add(regionItem5);
        regionItems.add(regionItem6);
        regionItems.add(regionItem7);
        regionItems.add(regionItem8);
        regionItems.add(regionItem9);
        regionItems.add(regionItem10);
        regionItems.add(regionItem11);
        regionItems.add(regionItem12);
    }
}