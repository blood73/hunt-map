package mikheev.konstantin.huntmap.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import mikheev.konstantin.huntmap.R;
import mikheev.konstantin.huntmap.adapters.RegionsAdapter;
import mikheev.konstantin.huntmap.models.RegionItem;

public class AllMapsFragment extends Fragment implements RegionsAdapter.RegionViewHolder.ClickListener {

    private List<RegionItem> regionItems;
    private RecyclerView rvRegions;
    private RegionsAdapter adapter;
    private Button buyButton;
    private int totalPrice = 0;
    OnBuyButtonClickedListener buButtonListener;

    public interface OnBuyButtonClickedListener {
        public void onBuyButtonClicked(List<RegionItem> regionItemList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_maps, container, false);

        rvRegions = (RecyclerView) rootView.findViewById(R.id.rv_all_maps);
        buyButton = (Button) rootView.findViewById(R.id.buy_button);

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

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            buButtonListener = (OnBuyButtonClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnBuyButtonClickedListener");
        }
    }

    private void clickBuyButton() {
        Resources res = getResources();

        String textBuyResult = String.format(res.getString(R.string.buy_button_pressed), totalPrice);
        Toast toast = Toast.makeText(getActivity(), textBuyResult, Toast.LENGTH_SHORT);
        toast.show();

        buButtonListener.onBuyButtonClicked(adapter.getRegionItems());

        adapter.setIsBoughtRegions();
        adapter.clearSelection();

        totalPrice = 0;
        buyButton.setVisibility(View.GONE);
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

    private void initDataset() {
        regionItems = new ArrayList<>();

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