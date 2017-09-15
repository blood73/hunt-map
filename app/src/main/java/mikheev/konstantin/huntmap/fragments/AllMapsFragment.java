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
import mikheev.konstantin.huntmap.adapters.RegionsAdapter;
import mikheev.konstantin.huntmap.models.Region;

public class AllMapsFragment extends Fragment {

    private List<Region> regions;
    private RecyclerView rvRegions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_maps, container, false);

        rvRegions = (RecyclerView) rootView.findViewById(R.id.recyclerViewRegions);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvRegions.setLayoutManager(llm);
        initializeAdapter();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initializeAdapter() {
        RegionsAdapter adapter = new RegionsAdapter(regions);
        rvRegions.setAdapter(adapter);
    }

    private void initDataset() {
        regions = new ArrayList<>();

        Region region1 = new Region();
        region1.setRegionName("Нижегородская обл.");
        region1.setRegionPrice(366);

        Region region2 = new Region();
        region2.setRegionName("Чувашская республика");
        region2.setRegionPrice(290);

        Region region3 = new Region();
        region3.setRegionName("Марий Эл");
        region3.setRegionPrice(233);

        Region region4 = new Region();
        region4.setRegionName("Самарская обл.");
        region4.setRegionPrice(655);

        Region region5 = new Region();
        region5.setRegionName("Кировская обл.");
        region5.setRegionPrice(366);

        Region region6 = new Region();
        region6.setRegionName("Оренбурская обл.");
        region6.setRegionPrice(212);

        Region region7 = new Region();
        region7.setRegionName("Кемеровская обл.");
        region7.setRegionPrice(212);

        regions.add(region1);
        regions.add(region2);
        regions.add(region3);
        regions.add(region4);
        regions.add(region5);
        regions.add(region6);
        regions.add(region7);
    }
}