package mikheev.konstantin.huntmap.models;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

public class RegionItemsMap extends SparseArray<RegionItem> {

    public void addRegionItem(int regionId, RegionItem regionItem) {
        if (regionItem != null && this.get(regionId) == null) {
            regionItem.setRegionId(regionId);
            this.put(regionId, regionItem);
        }
    }

    public void deleteRegionItem(int regionId) {
        this.remove(regionId);
    }

    public List<RegionItem> getMapsList() {
        List<RegionItem> regionItemsList = new ArrayList<>();

        for(int i = 0; i < this.size(); i++) {
            regionItemsList.add(this.valueAt(i));
        }

        return regionItemsList;
    }

    public List<RegionItem> getBoughtMapsList() {
        List<RegionItem> regionItemsList = new ArrayList<>();

        for(int i = 0; i < this.size(); i++) {
            if (this.valueAt(i).getIsBought()) {
                regionItemsList.add(this.valueAt(i));
            }
        }

        return regionItemsList;
    }
}