package mikheev.konstantin.huntmap.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import mikheev.konstantin.huntmap.R;
import mikheev.konstantin.huntmap.adapters.RegionsAdapter;
import mikheev.konstantin.huntmap.models.RegionItem;

public class AllMapsFragment extends Fragment implements RegionsAdapter.RegionViewHolder.ClickListener {

    private List<RegionItem> regionItems = new ArrayList<>();
    private RecyclerView rvRegions;
    private RegionsAdapter adapter;
    private Button buyButton;
    private int totalPrice = 0;
    private TextView emptyTextView;
    private AllMapsInterface allMapsInterface;
    private MyMapsFragment.MyMapsInterface myMapsInterface;

    public interface AllMapsInterface {
        List<RegionItem> getAllMaps();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_maps, container, false);

        rvRegions = (RecyclerView) rootView.findViewById(R.id.rv_all_maps);
        buyButton = (Button) rootView.findViewById(R.id.buy_button);
        emptyTextView = (TextView) rootView.findViewById(R.id.empty_view);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvRegions.setLayoutManager(llm);
        initializeAdapter();

        Button buyButton = (Button) rootView.findViewById(R.id.buy_button);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBuyButton();
            }
        });

        updateEmptyViewState();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            allMapsInterface = (AllMapsInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement AllMapsInterface");
        }

        try {
            myMapsInterface = (MyMapsFragment.MyMapsInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement MyMapsInterface");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        regionItems = allMapsInterface.getAllMaps();
        adapter.setRegionItems(regionItems);
        updateEmptyViewState();
    }

    private void clickBuyButton() {
        Resources res = getResources();

        String textBuyResult = String.format(res.getString(R.string.buy_button_pressed), totalPrice);
        Toast toast = Toast.makeText(getActivity(), textBuyResult, Toast.LENGTH_SHORT);
        toast.show();

        adapter.setIsBoughtRegions();
        adapter.clearSelection();

        totalPrice = 0;
        buyButton.setVisibility(View.GONE);

        myMapsInterface.addToMyMaps(adapter.getRegionItems());
    }

    private void initializeAdapter() {
        adapter = new RegionsAdapter(regionItems, this);
        rvRegions.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {

        boolean isBought = regionItems.get(position).getIsBought();

        if (!isBought) {
            adapter.toggleSelection(position);

            int selectedItemsCount = adapter.getSelectedItemCount();
            int clickedRegionPrice = regionItems.get(position).getRegionPrice();

            if (adapter.isSelected(position)) {
                totalPrice += clickedRegionPrice;
            } else {
                totalPrice -= clickedRegionPrice;
            }

            if (selectedItemsCount != 0) {
                buyButton.setVisibility(View.VISIBLE);
            } else {
                buyButton.setVisibility(View.GONE);
            }

            Resources res = getResources();
            String textBuyButton = String.format(res.getString(R.string.buy_button), totalPrice);
            buyButton.setText(textBuyButton);
        }
    }

    private void updateEmptyViewState() {
        if (regionItems.isEmpty()) {
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            emptyTextView.setVisibility(View.GONE);
        }
    }
}