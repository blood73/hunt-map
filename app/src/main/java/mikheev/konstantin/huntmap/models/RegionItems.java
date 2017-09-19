package mikheev.konstantin.huntmap.models;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

public class RegionItems {
    private SparseArray<RegionItem> regionItems = new SparseArray<RegionItem>();

    public void addRegionItem(Integer id, RegionItem regionItem) {
        regionItems.put(id, regionItem);
    }

    public List<RegionItem> getRegionItemsList() {
        List<RegionItem> regionItemsList = new ArrayList<RegionItem>();

        for (int i = 0; i < regionItems.size(); i++) {
            RegionItem regionItem = regionItems.get(i);
            regionItemsList.add(regionItem);
        }

        return regionItemsList;
    }

    public SparseArray<RegionItem> getRegionItems() {
        return regionItems;
    }
}