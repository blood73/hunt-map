package mikheev.konstantin.huntmap.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import mikheev.konstantin.huntmap.R;
import mikheev.konstantin.huntmap.adapters.MyRegionsAdapter;
import mikheev.konstantin.huntmap.models.RegionItem;
import mikheev.konstantin.huntmap.utils.Utils;

public class MyMapsFragment extends Fragment implements MyRegionsAdapter.MyRegionViewHolder.ClickListener,
        MyRegionsAdapter.MyRegionViewHolder.OnDeleteButtonItemClickListener, MyRegionsAdapter.MyRegionViewHolder.OnProlongateButtonItemClickListener {

    private List<RegionItem> regionItems;
    private RecyclerView rvRegions;
    private MyRegionsAdapter adapter;
    private TextView emptyTextView;
    private MyMapsInterface myMapsInterface;

    public interface MyMapsInterface {
        List<RegionItem> getMyMaps();
        void addToMyMaps(List<RegionItem> regionItemList);
        void deleteFromMyMaps(int regionId);
        void prolongateMap(int regionId, long timestampEnd);
    }

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
        emptyTextView = (TextView) rootView.findViewById(R.id.empty_view);

        updateEmptyViewState();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvRegions.setLayoutManager(linearLayoutManager);
        initializeAdapter();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        regionItems = myMapsInterface.getMyMaps();
        adapter.setRegionItems(regionItems);
        updateEmptyViewState();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            myMapsInterface = (MyMapsInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement MapsInterface");
        }
    }

    @Override
    public void onItemClicked(int regionId) {
        for (RegionItem item : regionItems) {
            if (item.getRegionId() == regionId) {
                if (item.getTimestampEnd() > Utils.getCurrentTimestamp()) {
                    Toast toast = Toast.makeText(getActivity(), item.getRegionName(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }
    }

    @Override
    public void onDeleteClicked(int regionId) {
        myMapsInterface.deleteFromMyMaps(regionId);
    }

    @Override
    public void onProlongateClicked(int regionId, long timestampEnd) {
        myMapsInterface.prolongateMap(regionId, timestampEnd);
    }

     private void initializeAdapter() {
         adapter = new MyRegionsAdapter(regionItems, this, this, this);
         rvRegions.setAdapter(adapter);
     }

    private void initDataset() {
        regionItems = new ArrayList<>();
    }

    public void updateMyMapsItems(List<RegionItem> newRegionItems) {

        regionItems = myMapsInterface.getMyMaps();
        for (RegionItem regionItem : newRegionItems) {
            if (regionItem != null && regionItem.getIsBought() && !regionItems.contains(regionItem)) {
                regionItems.add(regionItem);
            }
        }

        adapter.setRegionItems(regionItems);
        adapter.notifyDataSetChanged();
        updateEmptyViewState();
    }

    private void updateEmptyViewState() {
        if (regionItems.isEmpty()) {
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            emptyTextView.setVisibility(View.GONE);
        }
    }
}