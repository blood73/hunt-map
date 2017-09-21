package mikheev.konstantin.huntmap.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    }

    public void updateMyMapsItems(List<RegionItem> newRegionItems) {

        for (RegionItem regionItem : newRegionItems) {
            if (regionItem != null && regionItem.getIsBought() && !regionItems.contains(regionItem)) {
                regionItems.add(regionItem);
            }
        }

        adapter.notifyDataSetChanged();
    }
}