package mikheev.konstantin.huntmap.models;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

public class RegionItemsMap {
    private SparseArray<RegionItem> regionItemSparseArray = new SparseArray<RegionItem>();
    private List<RegionItem> regionItemsList = new ArrayList<>();

    public void addRegionItem(int regionId, RegionItem regionItem) {
        if (regionItem != null && regionItemSparseArray.get(regionId) == null) {
            regionItem.setRegionId(regionId);
            regionItemSparseArray.put(regionId, regionItem);
        }
    }

    public void deleteRegionItem(int regionId) {
        regionItemSparseArray.remove(regionId);
    }

    public List<RegionItem> getRegionItemsList() {
        regionItemsList.clear();

        for(int i = 0; i < regionItemSparseArray.size(); i++) {
            regionItemsList.add(regionItemSparseArray.valueAt(i));
        }

        return regionItemsList;
    }
}